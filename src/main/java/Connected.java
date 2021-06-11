import com.ank.fencis.beans.City;
import com.ank.fencis.service.CityLinkBuilder;
import com.ank.fencis.service.CityLinkFinder;
import com.ank.fencis.service.CustomFileLoader;
import com.ank.fencis.util.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.util.concurrent.ConcurrentHashMap;

public class Connected {
    protected static boolean validateArgs(final String args[]) {
        if (args.length == 3) {
            for (String arg : args) {
                if (StringUtils.isEmpty(arg)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    protected static void doProcess(String[] args) {
        final String dataFile = args[0];
        final String startCity = args[1].trim().toLowerCase();
        final String endCity = args[2].trim().toLowerCase();

        if (startCity.equals(endCity)) {
            Response.yes();
        }
        // Loading data file
        CustomFileLoader fileLoader = new CustomFileLoader();
        BufferedReader fileBufferReader = fileLoader.loadFile(dataFile);

        if (fileBufferReader != null) {
            // Building cities connections
            CityLinkBuilder connectionBuilder = new CityLinkBuilder(
                    fileBufferReader, startCity, endCity);
            ConcurrentHashMap<String, City> dataMap = connectionBuilder.createLinkBuilder();
            System.out.println(dataMap);
            // Search for the desired connection
            CityLinkFinder connectionFinder = new CityLinkFinder(startCity,
                    endCity, dataMap);
            connectionFinder.checkConnection();
        }
    }


    public static void main(String[] args) {
        if (!validateArgs(args)) {
            Response.getResponse(
                    "Input Error: Usage java Connected <filename> <cityname1> <cityname2>",
                    1);
        }
        doProcess(args);

    }
}



