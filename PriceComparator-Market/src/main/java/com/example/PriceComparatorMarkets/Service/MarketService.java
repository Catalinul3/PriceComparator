package com.example.PriceComparatorMarkets.Service;

import com.example.PriceComparatorMarkets.BusinessLogic.ActiveDiscount;
import com.example.PriceComparatorMarkets.BusinessLogic.DailyBaskets;
import com.example.PriceComparatorMarkets.BusinessLogic.NewDiscount;
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
    private List<ProductDiscount> discounts;

    public MarketService() {
        products = CSVFileHelpers.readAllRegularProducts();
        discounts = CSVFileHelpers.readAllDiscountProducts();
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

    public List<ProductDiscount> getActiveDiscounts() {
        ActiveDiscount active = new ActiveDiscount();
        List<ProductDiscount> activeDiscounts = active.typeDiscount();
        return activeDiscounts;
    }

    public List<ProductDiscount> getNewlyDiscounts() {
        NewDiscount news = new NewDiscount();
        List<ProductDiscount> newDiscounts = news.typeDiscount();
        return newDiscounts;
    }

    public List<ProductDiscount> getBestActiveDiscounts() {
        ActiveDiscount active = new ActiveDiscount();
        List<ProductDiscount> bestActivDiscount = active.currentBestActiveDiscount();
        return bestActivDiscount;
    }

    public static List<RegularProduct> readAllRegularProducts() {
        List<RegularProduct> allProducts = CSVFileHelpers.readAllRegularProducts();
        return allProducts;
    }

    public static List<ProductDiscount> readAllDiscountProducts() {
        List<ProductDiscount> allDiscounts = CSVFileHelpers.readAllDiscountProducts();
        return allDiscounts;
    }

    public Map<String,RegularProduct> findBestDealPerUnit(String[] productsName) {
         Map<String,RegularProduct>bestDeal = DailyBaskets.bestDeal(productsName, products);
        return bestDeal;
    }

    public List<RegularProduct> computeUserBasketWithBasedProduct(String[] productsName) {
        List<RegularProduct> basket = DailyBaskets.computeBasketWithBasedProduct(productsName, products);
        return basket;
    }
    public List<RegularProduct>optimizeShoppingList(String[]productsName)
    {
        List<RegularProduct>optimizedList=DailyBaskets.optimizedList(productsName,products);
        return optimizedList;
    }
    public Map<String,RegularProduct>highlightBestDealProductService() {
        Map<String, RegularProduct> bestDeal = DailyBaskets.highlightBestDealAcrossAllMarkets(products);
    return bestDeal;
    }


}
