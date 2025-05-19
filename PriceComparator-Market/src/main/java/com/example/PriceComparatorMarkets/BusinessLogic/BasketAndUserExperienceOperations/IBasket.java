package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.util.List;
import java.util.Map;

public interface IBasket {
     List<RegularProduct> computeBasket(String[]product,List<RegularProduct>allProducts);
     Map<String,RegularProduct>bestDeal(String[] productsName, List<RegularProduct> allProducts);
}
