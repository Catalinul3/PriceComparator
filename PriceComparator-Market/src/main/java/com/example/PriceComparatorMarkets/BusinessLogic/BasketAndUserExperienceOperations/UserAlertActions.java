package com.example.PriceComparatorMarkets.BusinessLogic.BasketAndUserExperienceOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserAlertActions {
    private static final String ALERT_FILE = "alerts.csv";
    private static final String ALERT_PATH="src/main/java/com/example/PriceComparatorMarkets/DataAlert";
    private static Path path;
    public static boolean isDroped(RegularProduct product, int target) {
        return product.getPrice() > target;
    }
    public UserAlertActions()
    {
        String pathFile=ALERT_PATH+"/"+ALERT_FILE;
        Path relativePaths= Paths.get(pathFile);
        path=relativePaths.toAbsolutePath();

    }
    @Scheduled(cron = "0 0 0 * * *")
    public static void createAlert(String productName, int target) {

    }

    public static void CreateAlert(String userName,int target) {

        try {
            FileWriter file=new FileWriter(path.toString(),true);
            CSVWriter writer=new CSVWriter(file, ';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            String[] alerts={userName,Integer.toString(target)};
            writer.writeNext(alerts);
            writer.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
