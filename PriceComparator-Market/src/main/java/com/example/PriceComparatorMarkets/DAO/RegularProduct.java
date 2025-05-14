package com.example.PriceComparatorMarkets.DAO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularProduct extends Product{
    private float price;
    private String currency;
}
