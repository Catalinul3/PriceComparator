package com.example.PriceComparatorMarkets.DAO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ProductDiscount extends Product{
private LocalDate fromDate;
private LocalDate toDate;
private int percentageOfDiscount;
}
