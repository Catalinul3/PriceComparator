package com.example.PriceComparatorMarkets.BusinessLogic;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.Helpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.PercentageComparator;

import java.time.LocalDate;
import java.util.*;

public class DiscountsOperations {
    public static List<ProductDiscount> activeDiscounts() {
        List<ProductDiscount> allDiscountProducts = CSVFileHelpers.readAllDiscountProducts();//We read all discounts for compare data and time when the item has been reduced
        List<ProductDiscount> currentDiscounts = new ArrayList<ProductDiscount>();
        LocalDate today = LocalDate.now();
        for (ProductDiscount product : allDiscountProducts) {
//check if today is between reduced dates
            if (today.isAfter(product.getFromDate()) && (today.isEqual(product.getToDate()) || today.isBefore(product.getToDate()))) {
                currentDiscounts.add(product);
            }
        }
        currentDiscounts.sort(new PercentageComparator());
        return currentDiscounts;
    }

    public static List<ProductDiscount> currentBestActiveDiscount() {
        List<ProductDiscount> allDiscountProducts = CSVFileHelpers.readAllDiscountProducts();
        List<ProductDiscount> highestDiscounts = new ArrayList<ProductDiscount>();
        LocalDate today = LocalDate.now();
        int max = Integer.MIN_VALUE;
        for (ProductDiscount product : allDiscountProducts) {
            if (!today.isAfter(product.getToDate()) && !today.isBefore(product.getFromDate())) {
                //find max percentageDiscount, if exist a new max discount remove old highest Discount items and start to add with nex max
                if (max < product.getPercentageOfDiscount()) {
                    max = product.getPercentageOfDiscount();
                    highestDiscounts.clear();
                    highestDiscounts.add(product);
                } else {
                    if (max == product.getPercentageOfDiscount()) {
                        highestDiscounts.add(product);
                    }
                }
            }
        }
        return highestDiscounts;
    }
}
