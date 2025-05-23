package com.example.PriceComparatorMarkets.BusinessLogic.BasketOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.util.ArrayList;
import java.util.List;

public class ListOfShopping {
    public  List<RegularProduct> optimizedList(String[] productsName, List<RegularProduct> allProducts) {
        List<RegularProduct> optimizedShoppingList = new ArrayList<RegularProduct>();
        for (String product : productsName) {
            RegularProduct cheap = CheapProduct.findCheapestProduct(allProducts, product);
            optimizedShoppingList.add(cheap);
        }
        return optimizedShoppingList;
    }
}
