package com.example.PriceComparatorMarkets.BusinessLogic;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
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
            if (product.equals(StringHelper.firstString(StringHelper.normalize(cheap.getProductName())))) {
                if (cheap.getPrice() < min) {
                    cheapest = cheap;
                    min = cheap.getPrice();
                }
            }
        }
        return cheapest;
    }

    public static List<RegularProduct> bestDeal(String[] productsName, List<RegularProduct> allProducts) {
        List<RegularProduct> basket = computeBasketWithBasedProduct(productsName, allProducts);
        Map<String, Float> bestDeal = basket.stream().collect(Collectors.toMap(
                RegularProduct::getProductName,
                product -> pricePerUnit(product.getPackageQuantity(), product.getUnit(), product.getPrice()),
                (oldDeal, newDeal) -> Math.min(oldDeal, newDeal))
        );
        return basket;

    }


    public static float pricePerUnit(float quantity, String unit, float price) {
        float pricePerUnit = 0f;
        switch (unit) {
            case "ml":
                pricePerUnit = price / (quantity / 1000);
                break;
            case "g":
                pricePerUnit = price / (quantity / 1000);
                break;
            case "kg", "l", "buc":
                pricePerUnit = price / quantity;
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
