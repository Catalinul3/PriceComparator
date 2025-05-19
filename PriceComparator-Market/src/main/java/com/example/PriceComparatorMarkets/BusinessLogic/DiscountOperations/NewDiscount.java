package com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;

import java.util.ArrayList;
import java.util.List;

public class NewDiscount extends Discount implements IDiscounts {
    @Override
    public List<ProductDiscount> typeDiscount() {
        List<ProductDiscount> newAddedDiscounts = new ArrayList<ProductDiscount>();
        for (ProductDiscount newDiscount : allDiscountProducts) {
            if (today.getYear() == newDiscount.getFromDate().getYear()) {
                if (today.getMonth() == newDiscount.getFromDate().getMonth()) {
                    int day = today.getDayOfMonth() - newDiscount.getFromDate().getDayOfMonth();
                    if (day <= 1) {
                        newAddedDiscounts.add(newDiscount);
                    }
                }
            }
        }
        return newAddedDiscounts;
    }
}

