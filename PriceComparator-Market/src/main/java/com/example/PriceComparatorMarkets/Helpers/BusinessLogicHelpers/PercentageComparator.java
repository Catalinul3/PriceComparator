package com.example.PriceComparatorMarkets.Helpers.BusinessLogicHelpers;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;

import java.util.Comparator;

public class PercentageComparator implements Comparator<ProductDiscount> {
    @Override
    public int compare(ProductDiscount o1, ProductDiscount o2) {
        return Integer.compare(o2.getPercentageOfDiscount(),o1.getPercentageOfDiscount());
    }
}
