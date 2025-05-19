package com.example.PriceComparatorMarkets.BusinessLogic;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.Parser;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import org.apache.commons.collections.map.HashedMap;

import java.util.*;
import java.util.stream.Collectors;


public class DailyBaskets {

    public static List<RegularProduct> computeBasket(String[] productsName, List<RegularProduct> allProductMarket) {
        List<RegularProduct> basket = allProductMarket.stream().filter(
                product -> Arrays.stream(productsName).anyMatch(
                        name -> StringHelper.normalize(product.getProductName()).contains(name))).toList();


        return basket;
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
public static Map<String,RegularProduct>highlightBestDealAcrossAllMarkets(List<RegularProduct>allProducts)
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
    return bestDeals;
}
    public static Map<String,RegularProduct> bestDeal(String[] productsName, List<RegularProduct> allProducts) {
        List<RegularProduct> basket = computeBasketWithBasedProduct(productsName, allProducts);

        /*keep the product with cheapest pricePerUnit*/

        Map<String,RegularProduct> bestDeal = basket.stream().collect(Collectors.toMap(
                RegularProduct::getProductName,
                product -> product,
                (oldDeal, newDeal) -> {
                    float oldPrice=pricePerUnit(oldDeal);
                    float newPrice=pricePerUnit(newDeal);
                    return (oldPrice<newPrice)?oldDeal:newDeal;
                }
        ));
        return bestDeal;

    }


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

    public static List<RegularProduct> computeBasketWithBasedProduct(String[] productsName, List<RegularProduct> allProductMarket) {
        List<RegularProduct> basket = allProductMarket.stream().filter(
                product -> Arrays.stream(productsName).anyMatch(
                        name -> StringHelper.firstString(StringHelper.normalize(product.getProductName())).equals(name))).toList();
        return basket;

    }
}
