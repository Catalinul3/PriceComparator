package com.example.PriceComparatorMarkets.Helpers;

import com.example.PriceComparatorMarkets.DAO.Product;

import java.util.List;

public interface Parser {
    List<Product>loadProducts(String file,String title);
}
