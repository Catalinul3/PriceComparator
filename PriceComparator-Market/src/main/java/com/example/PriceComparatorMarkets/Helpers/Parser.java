package com.example.PriceComparatorMarkets.Helpers;

import com.example.PriceComparatorMarkets.DAO.Product;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.util.List;

public interface Parser {
    List<RegularProduct>loadProducts(String file, String title);
}
