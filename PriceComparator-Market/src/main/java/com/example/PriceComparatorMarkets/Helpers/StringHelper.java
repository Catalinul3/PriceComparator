package com.example.PriceComparatorMarkets.Helpers;

import javax.print.DocFlavor;
import java.text.Normalizer;

public class StringHelper {
    public static String spliter(String title) {
        String[] splitString = title.split("_");
        return splitString[0];
    }
    public static String normalize(String name)
    {
        return Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]","");
    }
    public static String firstString(String list)
    {
        String firstString=list.split(" ")[0];
        return firstString;
    }
}
