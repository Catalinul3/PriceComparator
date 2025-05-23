package com.example.PriceComparatorMarkets.DAO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Graph {
    private float price;
    private LocalDate date;
    private String store;

}
