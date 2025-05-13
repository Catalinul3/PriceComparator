package com.example.PriceComparatorMarkets.Service;

import com.example.PriceComparatorMarkets.DAO.Product;
import com.example.PriceComparatorMarkets.Helpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.CSVParser;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class MarketService {
    public List<Product> getProducts(String file) {
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();
        Path filePath = absolutePath.resolve(file);
        String title = StringHelper.spliter(file);
        CSVParser csv = new CSVParser();
        List<Product> marketProduct = csv.loadProducts(filePath.toString(), title);
        return marketProduct;
    }

    public static List<Product> readAllCSVTest() {
        List<Product> allProducts = CSVFileHelpers.readAllCSVFile();
        return allProducts;
    }
}
