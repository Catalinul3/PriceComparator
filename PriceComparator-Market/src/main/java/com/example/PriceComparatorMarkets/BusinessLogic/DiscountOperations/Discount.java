package com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;

import java.time.LocalDate;
import java.util.List;

public class Discount {
     List<ProductDiscount> allDiscountProducts;
     LocalDate today;
     public Discount()
     {
         allDiscountProducts=CSVFileHelpers.readAllDiscountProducts();
         today=LocalDate.now();
     }
}
