package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.*;
import java.util.stream.Collectors;


public class BasketProduct extends Basket implements IBasket{
@Override
    public  List<RegularProduct> computeBasket(String[] productsName, List<RegularProduct> allProductMarket) {
        List<RegularProduct> basket = allProductMarket.stream().filter(
                product -> Arrays.stream(productsName).anyMatch(
                        name -> StringHelper.normalize(product.getProductName()).contains(name))).toList();


        return basket;
    }

    public  Map<String,RegularProduct> bestDeal(String[] productsName, List<RegularProduct> allProducts) {
        List<RegularProduct> basket = computeBasket(productsName, allProducts);

        /*keep the product with cheapest pricePerUnit*/

        Map<String,RegularProduct> bestDeal = basket.stream().collect(Collectors.toMap(
                RegularProduct::getProductName,
                product -> product,
                (oldDeal, newDeal) -> {
                    float oldPrice=pricePerUnit(oldDeal);
                    float newPrice=pricePerUnit(newDeal);
                    return (oldPrice<newPrice)?oldDeal:newDeal;
                }
        ));
        Map<String,RegularProduct>topDeal=bestDeal.entrySet().stream()
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return topDeal;

    }




}
