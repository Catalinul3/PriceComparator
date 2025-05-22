package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;


import com.example.PriceComparatorMarkets.DAO.GraphProduct;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVParserCustom;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;
import com.opencsv.CSVReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreGraph {
    public List<Graph>computeGraph(String productName,String store)
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
