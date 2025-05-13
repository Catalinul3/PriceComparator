package com.example.PriceComparatorMarkets.Helpers;

import com.example.PriceComparatorMarkets.DAO.Product;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser {
    @Override
    public List<Product> loadProducts(String file, String title) {
        List<Product> marketProduct = new ArrayList<Product>();
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext();
            String[] next;

            while ((next = csvReader.readNext()) != null) {
                for (String cell : next) {
                    String[] splitCell = cell.split(";");
                    Product product = new Product();
                    product.setId(splitCell[0]);
                    product.setProductName(splitCell[1]);
                    product.setProductCategory(splitCell[2]);
                    product.setBrand(splitCell[3]);
                    product.setPackageQuantity(Float.parseFloat(splitCell[4]));
                    product.setUnit(splitCell[5]);
                    product.setPrice(Float.parseFloat(splitCell[6]));
                    product.setCurrency(splitCell[7]);
                    product.setStore(title);
                    marketProduct.add(product);
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return marketProduct;
    }
}

