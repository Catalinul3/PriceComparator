package com.example.PriceComparatorMarkets.BusinessLogic.BasketOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.List;

public class CheapProduct {
    public static RegularProduct findCheapestProduct(List<RegularProduct> allProduct, String product) {
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
}
