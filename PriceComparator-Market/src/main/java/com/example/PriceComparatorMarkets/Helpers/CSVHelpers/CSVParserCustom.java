package com.example.PriceComparatorMarkets.Helpers.CSVHelpers;

import com.example.PriceComparatorMarkets.BusinessLogic.UserOperations.UserAlert;
import com.example.PriceComparatorMarkets.DAO.GraphProduct;
import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.Parser;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class CSVParserCustom implements Parser {
    @Override
    public List<RegularProduct> loadProducts(String file, String title) {
        List<RegularProduct> marketProduct = new ArrayList<RegularProduct>();
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext();
            String[] next;

            while ((next = csvReader.readNext()) != null) {
                for (String cell : next) {
                    String[] splitCell = cell.split(";");
                    RegularProduct product = new RegularProduct();
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

    public List<GraphProduct> loadGraph(String file, String title, LocalDate date) {
        List<GraphProduct> marketProduct = new ArrayList<GraphProduct>();
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext();
            String[] next;

            while ((next = csvReader.readNext()) != null) {
                for (String cell : next) {
                    String[] splitCell = cell.split(";");
                    GraphProduct product = new GraphProduct();
                    product.setId(splitCell[0]);
                    product.setProductName(splitCell[1]);
                    product.setProductCategory(splitCell[2]);
                    product.setBrand(splitCell[3]);
                    product.setPackageQuantity(Float.parseFloat(splitCell[4]));
                    product.setUnit(splitCell[5]);
                    product.setPrice(Float.parseFloat(splitCell[6]));
                    product.setCurrency(splitCell[7]);
                    product.setStore(title);
                    product.setDate(date);
                    marketProduct.add(product);
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return marketProduct;
    }

    public List<UserAlert> loadAlert(String file) {
        List<UserAlert> alerts = new ArrayList<UserAlert>();
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] next;

            while ((next = csvReader.readNext()) != null) {
                for (String cell : next) {
                    String[] splitCell = cell.split(";");
                    UserAlert alert = new UserAlert();
                    alert.setProductName(splitCell[0]);
                    alert.setTarget(Integer.parseInt(splitCell[1]));
                    alerts.add(alert);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alerts;
    }

    public List<ProductDiscount> loadDiscount(String file, String title) {
        List<ProductDiscount> dicountProducts = new ArrayList<ProductDiscount>();
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext();
            String[] next;

            while ((next = csvReader.readNext()) != null) {
                for (String cell : next) {
                    String[] splitCell = cell.split(";");
                    ProductDiscount product = new ProductDiscount();
                    product.setId(splitCell[0]);
                    product.setProductName(splitCell[1]);
                    product.setBrand(splitCell[2]);
                    product.setPackageQuantity(Float.parseFloat(splitCell[3]));
                    product.setUnit(splitCell[4]);
                    product.setProductCategory(splitCell[5]);
                    product.setFromDate(LocalDate.parse(splitCell[6]));
                    product.setToDate(LocalDate.parse(splitCell[7]));
                    product.setPercentageOfDiscount(Integer.parseInt(splitCell[8]));
                    product.setStore(title);
                    dicountProducts.add(product);
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dicountProducts;
    }

    public void removeAlert(String file, UserAlert alert) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]>allAlerts=csvReader.readAll();
            List<String[]>newAlertFile=allAlerts.stream().filter(
                    noRepetitive->!alert.getProductName().equals(StringHelper.splitAlert(noRepetitive[0]))
            ).toList();
            FileWriter sw=new FileWriter(file);
            CSVWriter writer=new CSVWriter(sw,';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(newAlertFile);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


