package com.example.PriceComparatorMarkets.Helpers;

import java.text.Normalizer;
import java.time.LocalDate;

public class StringHelper {
    public static String spliter(String title) {
        String[] splitString = title.split("_");
        return splitString[0];
    }
    public static String splitAlert(String alert) {
        String[] splitString = alert.split(";");
        return splitString[0];
    }
    public static LocalDate getData(String title)
    {
        String[] splitString=title.split("_");

        LocalDate date=LocalDate.MIN;
        if(splitString.length==2)//it means store csv files
        {String[] splitCSV=splitString[1].split(".csv");
            date=LocalDate.parse(splitCSV[0]);
        }
        else //discount csv files
        {String[] splitCSV=splitString[2].split(".csv");
            date= LocalDate.parse(splitCSV[0]);
        }
        return date;
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
