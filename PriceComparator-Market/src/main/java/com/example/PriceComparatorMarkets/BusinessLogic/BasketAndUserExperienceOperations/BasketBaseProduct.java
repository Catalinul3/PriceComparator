package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class BasketBaseProduct extends Basket implements IBasket{
    @Override
    public List<RegularProduct> computeBasket(String[] products, List<RegularProduct> allProducts) {
        List<RegularProduct> basket = allProducts.stream().filter(
                product -> Arrays.stream(products).anyMatch(
                        name -> StringHelper.firstString(StringHelper.normalize(product.getProductName())).equals(name))).toList();
        return basket;
    }

    @Override
    public Map<String, RegularProduct> bestDeal(String[] productsName, List<RegularProduct> allProducts) {
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
