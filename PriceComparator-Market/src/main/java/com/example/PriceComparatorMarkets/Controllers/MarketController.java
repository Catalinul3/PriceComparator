package com.example.PriceComparatorMarkets.Controllers;

import com.example.PriceComparatorMarkets.DAO.Product;
import com.example.PriceComparatorMarkets.DAO.ProductDiscount;
import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Service.MarketService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Log4j2
@RestController
@RequestMapping("/markets")
public class MarketController {
    MarketService service;
    public MarketController()
    {
        service=new MarketService();
    }
    @GetMapping("/getProductsByCSVFile")
    public List<RegularProduct> getProductFromMarket(String file) { //log.info("getMarketProducts has started ...");


        List<RegularProduct> marketProducts = service.getProducts(file);
        return marketProducts;
    }
@GetMapping("/geActivetDiscount")
public List<ProductDiscount> getActiveDiscount()
{
    List<ProductDiscount> discounts=service.getActiveDiscounts();
    return discounts;
}
    @GetMapping("/getAllProduct")
    public List<RegularProduct> getAllProductFromAllMarkets() {
        List<RegularProduct> allProducts = MarketService.readAllRegularProducts();
        return allProducts;
    }

    @GetMapping("/searchItemsByName")
    public List<Product>searchItemByName(String name){

        List<Product> searchItems = service.getProductByName(name);
        return searchItems;
    }
    @GetMapping("/getAllDiscounts")
    public List<ProductDiscount>getAllDiscounts()
    {
        List<ProductDiscount>allDiscounts=MarketService.readAllDiscountProducts();
        return  allDiscounts;
    }
    @GetMapping("/bestCurrentActiveDiscount")

        public List<ProductDiscount>getBestCurrentActiveDiscount()
        {
            List<ProductDiscount> discounts=service.getBestActiveDiscounts();
            return discounts;
        }
    }


