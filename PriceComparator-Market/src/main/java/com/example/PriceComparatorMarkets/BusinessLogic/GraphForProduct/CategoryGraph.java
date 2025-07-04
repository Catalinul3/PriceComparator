package com.example.PriceComparatorMarkets.BusinessLogic.GraphForProduct;

import com.example.PriceComparatorMarkets.DAO.Graph;
import com.example.PriceComparatorMarkets.DAO.GraphProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryGraph implements IGraph {
    @Override
    public List<Graph> computeGraph(String productName, String category)
    {
        List<GraphProduct>products= CSVFileHelpers.readAllGraphProduct().stream()
                .filter(
                        pName-> StringHelper.normalize(pName.getProductName()).contains(productName)
                ).filter(
                product->StringHelper.normalize(product.getProductCategory()).equals(category.toLowerCase())
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
