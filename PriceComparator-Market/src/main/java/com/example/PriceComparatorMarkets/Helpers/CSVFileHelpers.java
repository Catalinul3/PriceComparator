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
    public static List<Product> readAllCSVFile()
    {   List<Product>products=new ArrayList<Product>();
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();

        final File folder=new File(absolutePath.toString());
        for(final File fileEntry:folder.listFiles())
        {List<Product>currentStore=new ArrayList<Product>();
            if(fileEntry.getName().contains(".csv"))
            {Path filePath = absolutePath.resolve(fileEntry.getName());
                String title=spliter(fileEntry.getName());//obtain store name
                currentStore=loadProduct(filePath.toString(),title);
                products.addAll(currentStore);
            }
        }
        return products;

    }
    public static String spliter(String title)
    {
        String[] splitString=title.split("_");
        return splitString[0];
    }
    public static List<Product> loadProduct(String file,String title) {
 List<Product>marketProduct=new ArrayList<Product>();
        try {
            FileReader fileReader=new FileReader(file);
            CSVReader csvReader=new CSVReader(fileReader);
            csvReader.readNext();
            String[] next;

            while((next=csvReader.readNext())!=null)
            {
                for(String cell:next)
                {
                    String[] splitCell=cell.split(";");
                    Product product=new Product();
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
