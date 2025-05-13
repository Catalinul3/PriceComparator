package com.example.PriceComparatorMarkets.Helpers;

public class StringHelper {
    public static String spliter(String title) {
        String[] splitString = title.split("_");
        return splitString[0];
    }
}
