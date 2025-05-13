package com.example.PriceComparatorMarkets.Controllers;

import com.example.PriceComparatorMarkets.DAO.Product;
import com.example.PriceComparatorMarkets.Service.MarketService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/markets")
public class MarketController {
    @GetMapping("/getProductsByCSVFile")
    public List<Product> getProductFromMarket(String file) { //log.info("getMarketProducts has started ...");

        MarketService service = new MarketService();
        List<Product> marketProducts = service.getProducts(file);
        return marketProducts;
    }

    @GetMapping("/getAllProduct")
    public List<Product> getAllProductFromAllMarkets() {
        List<Product> allProducts = MarketService.readAllCSVTest();
        return allProducts;
    }
}
