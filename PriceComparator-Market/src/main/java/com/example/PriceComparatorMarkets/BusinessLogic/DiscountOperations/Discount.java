package com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;

import java.time.LocalDate;
import java.util.List;

public class Discount {
    final static List<ProductDiscount> allDiscountProducts= CSVFileHelpers.readAllDiscountProducts();
    final static LocalDate today = LocalDate.now();
}
