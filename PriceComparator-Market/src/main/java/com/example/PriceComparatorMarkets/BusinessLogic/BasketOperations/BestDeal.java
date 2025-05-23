package com.example.PriceComparatorMarkets.BusinessLogic.BasketOperations;

import com.example.PriceComparatorMarkets.BusinessLogic.PriceOperations;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestDeal {
    public static Map<String, RegularProduct> highlightBestDealAcrossAllMarkets(List<RegularProduct> allProducts)
    {
        Map<String,RegularProduct>bestDeals=allProducts.stream().
                collect(Collectors.toMap(
                        RegularProduct::getProductName,
                        product -> product,
                        (oldDeal,newDeal)->{
                            float oldPricePerUnit= PriceOperations.pricePerUnit(oldDeal);
                            float newPricePerUnit=PriceOperations.pricePerUnit(newDeal);
                            return (oldPricePerUnit<newPricePerUnit)?oldDeal:newDeal;
                        }
                ));
        Map<String,RegularProduct>topDeal=bestDeals.entrySet().stream()
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return topDeal;

    }
}
