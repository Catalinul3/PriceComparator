package com.example.PriceComparatorMarkets.Helpers;

import com.example.PriceComparatorMarkets.DAO.Product;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVFileHelpers {
    public static List<Product> readAllCSVFile() {
        List<Product> products = new ArrayList<Product>();
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();

        final File folder = new File(absolutePath.toString());
        for (final File fileEntry : folder.listFiles()) {
            List<Product> currentStore = new ArrayList<Product>();
            CSVParser currentFile = new CSVParser();
            if (fileEntry.getName().contains(".csv")) {
                Path filePath = absolutePath.resolve(fileEntry.getName());
                String title = StringHelper.spliter(fileEntry.getName());//obtain store name
                currentStore = currentFile.loadProducts(filePath.toString(), title);
                products.addAll(currentStore);
            }
        }
        return products;

    }
}