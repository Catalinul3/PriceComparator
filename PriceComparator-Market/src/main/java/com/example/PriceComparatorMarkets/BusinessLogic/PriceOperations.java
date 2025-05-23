package com.example.PriceComparatorMarkets.BusinessLogic;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;

public class PriceOperations {
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
}
