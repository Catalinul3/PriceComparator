package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.util.List;

public interface IGraph {
     List<Graph> computeGraph(String filter, String product);
}
