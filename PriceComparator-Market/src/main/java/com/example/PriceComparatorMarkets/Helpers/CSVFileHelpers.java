package com.example.PriceComparatorMarkets.Helpers;

import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileHelpers {
    public static List<RegularProduct> readAllRegularProducts() {
        List<RegularProduct> products = new ArrayList<RegularProduct>();
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();

        final File folder = new File(absolutePath.toString());
        for (final File fileEntry : folder.listFiles()) {
            List<RegularProduct> currentStore = new ArrayList<RegularProduct>();
            CSVParserCustom currentFile = new CSVParserCustom();
            if (fileEntry.getName().contains(".csv")) {
                Path filePath = absolutePath.resolve(fileEntry.getName());
                String title = StringHelper.spliter(fileEntry.getName());//obtain store name
                currentStore = currentFile.loadProducts(filePath.toString(), title);
                products.addAll(currentStore);
            }
        }
        return products;

    }
    public static List<ProductDiscount>  readAllDiscountProducts()
    {
        List<ProductDiscount> products = new ArrayList<ProductDiscount>();
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataDiscountStores");
        Path absolutePath = relativePath.toAbsolutePath();

        final File folder = new File(absolutePath.toString());
        for (final File fileEntry : folder.listFiles()) {
            List<ProductDiscount> currentStore = new ArrayList<ProductDiscount>();
            CSVParserCustom currentFile = new CSVParserCustom();
            if (fileEntry.getName().contains(".csv")) {
                Path filePath = absolutePath.resolve(fileEntry.getName());
                String title = StringHelper.spliter(fileEntry.getName());//obtain store name
                currentStore = currentFile.loadDiscount(filePath.toString(), title);
                products.addAll(currentStore);
            }
        }
        return products;
    }
}