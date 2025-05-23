package com.example.PriceComparatorMarkets.BusinessLogic.BasketOperations;

import com.example.PriceComparatorMarkets.BusinessLogic.PriceOperations;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
private ListOfShopping shopping;
public Basket()
{

    shopping=new ListOfShopping();
}
    public  List<RegularProduct> splitIntoOptimizeShoppingList(String[] productsName, List<RegularProduct> allProducts) {
        List<RegularProduct> optimizedShoppingList=shopping.optimizedList(productsName, allProducts);
        return optimizedShoppingList;
    }

}


