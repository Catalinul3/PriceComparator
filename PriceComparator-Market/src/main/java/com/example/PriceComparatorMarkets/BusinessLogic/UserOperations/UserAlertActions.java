package com.example.PriceComparatorMarkets.BusinessLogic.UserOperations;

import com.example.PriceComparatorMarkets.DAO.RegularProduct;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVFileHelpers;
import com.example.PriceComparatorMarkets.Helpers.CSVHelpers.CSVParserCustom;
import com.example.PriceComparatorMarkets.Helpers.StringHelper;
import com.opencsv.CSVWriter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserAlertActions {
    private static final String ALERT_FILE = "alerts.csv";
    private static final String ALERT_PATH = "src/main/java/com/example/PriceComparatorMarkets/DataAlert";
    private static Path path;
    private List<UserAlert> alerts;
    private CSVParserCustom parser;

    public static boolean isDroped(RegularProduct product, float target) {
        return product.getPrice() < target;
    }

    public UserAlertActions() {
        String pathFile = ALERT_PATH + "/" + ALERT_FILE;
        Path relativePaths = Paths.get(pathFile);
        path = relativePaths.toAbsolutePath();
        alerts = new ArrayList<UserAlert>();
        parser = new CSVParserCustom();

    }

    @Scheduled(cron = "0 * 18 * * ? ")
    public void notifyUser() {
        List<RegularProduct> currentStore = CSVFileHelpers.findProductOnActualDate();
        boolean droped = false;
        alerts = parser.loadAlert(path.toString());
        if (alerts.size() != 0) {
            for (UserAlert alert : alerts) {
                for (RegularProduct product : currentStore) {
                    String englishAlphabeticalProduct = StringHelper.normalize(product.getProductName());
                    if (englishAlphabeticalProduct.contains(alert.getProductName())) {
                        if (isDroped(product, alert.getTarget())) {
                            droped = true;
                            System.out.println(alert.getProductName() + " is dropped at" + product.getStore());
                            parser.removeAlert(path.toString(), alert);


                        }
                    }
                }
            }
        }
        if (droped) {
            System.out.println("Hurry up");

        } else {
            System.out.println("Price of product is not below target");
        }
    }

    public static void CreateAlert(String userName, int target) {

        try {
            FileWriter file = new FileWriter(path.toString(), true);
            CSVWriter writer = new CSVWriter(file, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            String[] alerts = {userName, Integer.toString(target)};
            writer.writeNext(alerts);
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
