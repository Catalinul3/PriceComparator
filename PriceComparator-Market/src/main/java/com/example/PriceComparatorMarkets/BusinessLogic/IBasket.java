package com.example.PriceComparatorMarkets.BusinessLogic;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.util.List;

public interface IBasket {
     List<RegularProduct> computeBasket(String[]product,List<RegularProduct>allProducts);
    List<RegularProduct>bestDealUserBasket(String[]product,List<RegularProduct>allProducts);
}
