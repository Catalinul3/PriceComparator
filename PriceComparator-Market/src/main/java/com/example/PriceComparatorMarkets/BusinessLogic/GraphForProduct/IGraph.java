package com.example.PriceComparatorMarkets.BusinessLogic.GraphForProduct;

import com.example.PriceComparatorMarkets.DAO.Graph;

import java.util.List;

public interface IGraph {
     List<Graph> computeGraph(String product, String filter);
}
