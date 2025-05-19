package com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.Helpers.BusinessLogicHelpers.PercentageComparator;

import java.util.ArrayList;
import java.util.List;

public class ActiveDiscount extends Discount implements IDiscounts {

    @Override
    public List<ProductDiscount> typeDiscount() {
        List<ProductDiscount> currentDiscounts = new ArrayList<ProductDiscount>();

        for (ProductDiscount product : allDiscountProducts) {
//check if today is between reduced dates
            if (today.isAfter(product.getFromDate()) && (today.isEqual(product.getToDate()) || today.isBefore(product.getToDate()))) {
                currentDiscounts.add(product);
            }
        }
        currentDiscounts.sort(new PercentageComparator());
        return currentDiscounts;
    }

    public  List<ProductDiscount> currentBestActiveDiscount() {

        List<ProductDiscount> highestDiscounts = new ArrayList<ProductDiscount>();
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

