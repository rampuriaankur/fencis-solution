package com.ank.fenics.service;

import com.ank.fenics.beans.City;
import com.ank.fenics.util.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ank.fenics.util.Constants.DELIMITER;

public class CityLinkBuilder {


    /* Buffered reader used to read through loaded data file. */
    private BufferedReader bReader;
    /* Right hand side city in the connection test. */
    private String fromCity;
    /* Left hand side city in the connection test. */
    private String toCity;


    public CityLinkBuilder(BufferedReader bReader, String fromCity, String toCity) {
        this.bReader = bReader;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    protected final ConcurrentHashMap<String, City> processLinkingBetweenCities() {
        String line;
        final ConcurrentHashMap<String, City> dataMap = new ConcurrentHashMap<String, City>();
        try {
            while ((line = bReader.readLine()) != null) {
                // Check if direct relation can be found
                String connectedCities[] = parseLine(line);
                createLink(dataMap, connectedCities);
            }
        } catch (IOException e) {
            Response.getResponse(e.getMessage(), 1);
        }
        checkNodesExist(dataMap);
        return dataMap;
    }

    public ConcurrentHashMap<String, City> createLinkBuilder() {
        return processLinkingBetweenCities();
    }

    protected void createLink(final Map<String, City> dataMap,
                              String[] connectedCities) {
        // This is to confirm that we has two cities, Just in case file
        // contained one city.
        if (connectedCities.length == 2) {
            ArrayList<City> cities = new ArrayList<City>(2);
            City city;
            for (String cityName : connectedCities) {
                if ((city = dataMap.get(cityName)) == null) {
                    city = new City(cityName);
                    dataMap.put(cityName, city);
                }
                cities.add(city);
            }
            if (!cities.get(0).hasLinkWith(cities.get(1).getName())) {
                cities.get(0).addLink(cities.get(1).getName(),
                        cities.get(1));
            }
            if (!cities.get(1).hasLinkWith(cities.get(0).getName())) {
                cities.get(1).addLink(cities.get(0).getName(),
                        cities.get(0));
            }
        }
    }

    protected final String[] parseLine(final String line) {
        String connectedCities[] = getLinkedCitiesWithTrimedValue(line);
        checkDirectConnection(connectedCities);
        return connectedCities;
    }

    /**
     * Check if there is a direct relation between requested cities and the
     * current connection.
     */
    protected void checkDirectConnection(String[] currentConnection) {
        String[] targetConnections = {this.fromCity, this.toCity};
        Arrays.sort(currentConnection);
        Arrays.sort(targetConnections);
        if (currentConnection[0].equals(targetConnections[0])
                && currentConnection[1].equals(targetConnections[1])) {
            Response.yes();
        }
    }

    /**
     * @return array of strings representing connected cities.
     */
    protected String[] getLinkedCities(String line) {
        return line.replace(DELIMITER + " ", DELIMITER)
                .split(DELIMITER);
    }

    protected final void checkNodesExist(Map<String, City> data) {
        if (data == null || data.isEmpty() || !data.containsKey(this.fromCity)
                || !data.containsKey(toCity)) {
            Response.no();
        }
    }

    protected String[] getLinkedCitiesWithTrimedValue(String line) {
        return line.trim().toLowerCase().replace(DELIMITER + " ", DELIMITER)
                .split(DELIMITER);
    }
}
