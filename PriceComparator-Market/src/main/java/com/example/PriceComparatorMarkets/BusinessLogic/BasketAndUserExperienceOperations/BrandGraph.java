package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.GraphProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class BrandGraph {
    public List<Graph> computeGraph(String productName, String brand)
    {
        List<GraphProduct>products= CSVFileHelpers.readAllGraphProduct().stream()
                .filter(
                        pName-> StringHelper.normalize(pName.getProductName()).contains(productName)
                ).filter(
                        product->StringHelper.normalize(product.getBrand()).toLowerCase().equals(brand.toLowerCase())
                ).toList();
        List<Graph>graphs=new ArrayList<Graph>();
        for(GraphProduct graphProduct: products)
        {
            Graph graph=new Graph();
            graph.setDate(graphProduct.getDate());
            graph.setStore(graphProduct.getStore());//for different color in frontend
            graph.setPrice(graphProduct.getPrice());
            graphs.add(graph);
        }
        return graphs;
    }
}

