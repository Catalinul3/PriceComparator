package com.example.PriceComparatorMarkets.Helpers.CSVHelpers;

import com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations.Graph;
import com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations.UserAlert;
import com.example.PriceComparatorMarkets.DAO.GraphProduct;
import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;


import javax.print.DocFlavor;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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


    public static List<ProductDiscount> readAllDiscountProducts() {
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

  public static List<GraphProduct>readAllGraphProduct()
  {
      List<GraphProduct> products = new ArrayList<GraphProduct>();
      Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
      Path absolutePath = relativePath.toAbsolutePath();

      final File folder = new File(absolutePath.toString());
      for (final File fileEntry : folder.listFiles()) {
          List<GraphProduct> currentStore = new ArrayList<GraphProduct>();
          CSVParserCustom currentFile = new CSVParserCustom();
          if (fileEntry.getName().contains(".csv")) {
              Path filePath = absolutePath.resolve(fileEntry.getName());
              String title = StringHelper.spliter(fileEntry.getName());//obtain store name
              LocalDate data=StringHelper.getData(fileEntry.getName());
              currentStore = currentFile.loadGraph(filePath.toString(), title,data);
              products.addAll(currentStore);
          }
      }
      return products;
  }


    private static List<String> newestMarketCatalog() {
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();

        final File folder = new File(absolutePath.toString());

        Map<String, LocalDate> newMarkets = new HashMap<String, LocalDate>();
        //store all markets in map structure for setting the recent market catalog
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().contains(".csv")) {
                Path filePath = absolutePath.resolve(fileEntry.getName());
                String title = StringHelper.spliter(fileEntry.getName());
                LocalDate date = StringHelper.getData(fileEntry.getName());
                newMarkets.put(title, date);
            }
        }
        List<String> newestMarkets = new ArrayList<String>();
        for (String key : newMarkets.keySet()) {
            newestMarkets.add(key + "_" + newMarkets.get(key).toString() + ".csv");
        }
        return newestMarkets;
    }

    public static List<RegularProduct> findProductOnActualDate() {
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> newestMarket = newestMarketCatalog();
        List<RegularProduct> products = new ArrayList<RegularProduct>();
        for (String csvFile : newestMarket) {
            List<RegularProduct> store = new ArrayList<RegularProduct>();
            CSVParserCustom currentFile = new CSVParserCustom();
            if (csvFile.contains(".csv")) {
                Path filePath = absolutePath.resolve(csvFile);
                String title = StringHelper.spliter(csvFile);//obtain store name
                store = currentFile.loadProducts(filePath.toString(), title);
                products.addAll(store);
            }
        }
        return products;
    }




}
