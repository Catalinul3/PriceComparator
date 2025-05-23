package com.example.PriceComparatorMarkets.BusinessLogic.GraphForProduct;


import com.example.PriceComparatorMarkets.DAO.Graph;
import com.example.PriceComparatorMarkets.DAO.GraphProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class StoreGraph implements IGraph {
    @Override
    public List<Graph>computeGraph(String productName, String store)
    {
        List<GraphProduct>products=CSVFileHelpers.readAllGraphProduct().stream()
                .filter(
                        pName-> StringHelper.normalize(pName.getProductName()).contains(productName)
                ).toList();
        List<GraphProduct>productsFromStore=products.stream().filter(
                product->product.getStore().toLowerCase().equals(store.toLowerCase())
        ).toList();
        List<Graph>graphs=new ArrayList<Graph>();
        for(GraphProduct graphProduct: productsFromStore)
        {
            Graph graph=new Graph();
            graph.setDate(graphProduct.getDate());
            graph.setStore(graphProduct.getStore());
            graph.setPrice(graphProduct.getPrice());
            graphs.add(graph);
        }
        return graphs;
    }
}
