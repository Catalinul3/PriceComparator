package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    public static float pricePerUnit(RegularProduct product) {
        float pricePerUnit = 0f;
        switch (product.getUnit()) {
            case "ml":
                pricePerUnit = product.getPrice() / (product.getPackageQuantity() / 1000);
                break;
            case "g":
                pricePerUnit = product.getPrice() / (product.getPackageQuantity() / 1000);
                break;
            case "kg", "l", "buc":
                pricePerUnit = product.getPrice() / product.getPackageQuantity();
                break;
        }
        return pricePerUnit;
    }

    public static List<RegularProduct> optimizedList(String[] productsName, List<RegularProduct> allProducts) {
        List<RegularProduct> optimizedShoppingList = new ArrayList<RegularProduct>();
        for (String product : productsName) {
            RegularProduct cheap = findCheapestProduct(allProducts, product);
            optimizedShoppingList.add(cheap);
        }
        return optimizedShoppingList;
    }

    private static RegularProduct findCheapestProduct(List<RegularProduct> allProduct, String product) {
        RegularProduct cheapest = new RegularProduct();
        float min = Float.MAX_VALUE;
        for (RegularProduct cheap : allProduct) {
            if (product.toLowerCase().equals(StringHelper.firstString(StringHelper.normalize(cheap.getProductName())))) {
                if (cheap.getPrice() < min) {
                    cheapest = cheap;
                    min = cheap.getPrice();
                }
            }
        }
        return cheapest;
    }
    public static Map<String,RegularProduct> highlightBestDealAcrossAllMarkets(List<RegularProduct>allProducts)
    {
        Map<String,RegularProduct>bestDeals=allProducts.stream().
                collect(Collectors.toMap(
                        RegularProduct::getProductName,
                        product -> product,
                        (oldDeal,newDeal)->{
                            float oldPricePerUnit=pricePerUnit(oldDeal);
                            float newPricePerUnit=pricePerUnit(newDeal);
                            return (oldPricePerUnit<newPricePerUnit)?oldDeal:newDeal;
                        }
                ));
        Map<String,RegularProduct>topDeal=bestDeals.entrySet().stream()
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return topDeal;

    }
}


