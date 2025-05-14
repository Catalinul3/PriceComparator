package com.example.PriceComparatorMarkets.Service;

import com.example.PriceComparatorMarkets.BusinessLogic.DiscountsOperations;
import com.example.PriceComparatorMarkets.DAO.Product;
import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.CSVParserCustom;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class MarketService {
    private List<RegularProduct> products;
    private List<ProductDiscount>discounts;

    public MarketService() {
        products = CSVFileHelpers.readAllRegularProducts();
        discounts=CSVFileHelpers.readAllDiscountProducts();
    }

    public List<RegularProduct> getProducts(String file) {
        Path relativePath = Paths.get("src/main/java/com/example/PriceComparatorMarkets/DataStores");
        Path absolutePath = relativePath.toAbsolutePath();
        Path filePath = absolutePath.resolve(file);
        String title = StringHelper.spliter(file);
        CSVParserCustom csv = new CSVParserCustom();
        List<RegularProduct> marketProduct = csv.loadProducts(filePath.toString(), title);
        return marketProduct;
    }
public List<String> getFromDataDay()
{List<String>strings=new LinkedList<String>();
    for(ProductDiscount p: discounts)
    {
        strings.add(p.getFromDate().getDayOfWeek().toString());

    }
    return strings;
}
public List<ProductDiscount>getActiveDiscounts()
{
    List<ProductDiscount>activeDiscounts=DiscountsOperations.activeDiscounts();
    return activeDiscounts;
}
public List<ProductDiscount>getBestActiveDiscounts()
{
    List<ProductDiscount>bestActivDiscount= DiscountsOperations.currentBestActiveDiscount();
    return bestActivDiscount;
}
    public static List<RegularProduct> readAllRegularProducts() {
        List<RegularProduct> allProducts = CSVFileHelpers.readAllRegularProducts();
        return allProducts;
    }
    public static List<ProductDiscount>readAllDiscountProducts(){
        List<ProductDiscount>allDiscounts=CSVFileHelpers.readAllDiscountProducts();
        return allDiscounts;
    }

    public List<Product> getProductByName(String name) {
        List<Product> searchProducts = new ArrayList<Product>();
        if (name.isBlank()) {
            return null;
        } else {
            for (Product product : products) {
                if (product.getProductName().contains(name)) {
                    searchProducts.add(product);
                }
            }
        }
        return searchProducts;
    }
}
