package com.example.PriceComparatorMarkets.Service;

import com.example.PriceComparatorMarkets.BusinessLogic.BasketOperations.*;
import com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations.ActiveDiscount;
import com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations.Discount;
import com.example.PriceComparatorMarkets.BusinessLogic.DiscountOperations.NewDiscount;
import com.example.PriceComparatorMarkets.BusinessLogic.GraphForProduct.BrandGraph;
import com.example.PriceComparatorMarkets.BusinessLogic.GraphForProduct.CategoryGraph;
import com.example.PriceComparatorMarkets.BusinessLogic.GraphForProduct.StoreGraph;
import com.example.PriceComparatorMarkets.BusinessLogic.UserOperations.UserAlertActions;
import com.example.PriceComparatorMarkets.DAO.Graph;
import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVParserCustom;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class MarketService {
    private List<RegularProduct> products;
    private List<RegularProduct>actualProducts;
    private Discount discount;
    private Basket basket;
    private BasketBaseProduct basketBaseProduct;
    private BasketProduct basketProducts;

    public MarketService() {
        products = CSVFileHelpers.readAllRegularProducts();
        actualProducts=CSVFileHelpers.findProductOnActualDate();
        discount=new Discount();
        basket = new Basket();
        basketBaseProduct = new BasketBaseProduct();
        basketProducts=new BasketProduct();
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

    public static List<RegularProduct> getCurrentCatalog() {
        List<RegularProduct> currentProduct = CSVFileHelpers.findProductOnActualDate();
        return currentProduct;
    }

    public static List<RegularProduct> readAllRegularProducts() {
        List<RegularProduct> allProducts = CSVFileHelpers.readAllRegularProducts();
        return allProducts;
    }

    public static List<ProductDiscount> readAllDiscountProducts() {
        List<ProductDiscount> allDiscounts = CSVFileHelpers.readAllDiscountProducts();
        return allDiscounts;
    }

    public Map<String, RegularProduct> findBestDealPerUnit(String[] productsName) {

        Map<String, RegularProduct> bestDeal = basketProducts.bestDeal(productsName, actualProducts);
        return bestDeal;
    }

    public List<RegularProduct> computeUserBasketWithBasedProduct(String[] productsName) {

        List<RegularProduct> basedProduct = basketBaseProduct.computeBasket(productsName, actualProducts);
        return basedProduct;
    }

    public List<RegularProduct> optimizeShoppingList(String[] productsName) {
        List<RegularProduct> optimizedList = basket.splitIntoOptimizeShoppingList(productsName, actualProducts);
        return optimizedList;
    }

    public Map<String, RegularProduct> highlightBestDealProductService() {
        Map<String, RegularProduct> bestDeal = BestDeal.highlightBestDealAcrossAllMarkets(actualProducts);
        return bestDeal;
    }

    public void CreateAlert(String productName, int target) {
        UserAlertActions.CreateAlert(productName, target);
    }
   public List<Graph> filterByStore(String name, String store)
   {
       StoreGraph storeGraph=new StoreGraph();
       return storeGraph.computeGraph(name,store);
   }
    public List<Graph> filterByCategory(String name,String category)
    {
        CategoryGraph storeGraph=new CategoryGraph();
        return storeGraph.computeGraph(name,category);
    }
    public List<Graph> filterByBrand(String name,String brand)
    {
        BrandGraph storeGraph=new BrandGraph();
        return storeGraph.computeGraph(name,brand);
    }
}
