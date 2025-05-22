package com.example.PriceComparatorMarkets.DAO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GraphProduct extends RegularProduct{
    private LocalDate date;
}
