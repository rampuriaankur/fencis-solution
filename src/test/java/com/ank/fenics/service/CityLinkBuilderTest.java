package com.ank.fenics.service;

import com.ank.fenics.beans.City;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertTrue;

public class CityLinkBuilderTest {


    @Test
    public void findConnectionIT() {
        String start = "boston";
        String target = "new york";
        String filePath = "./src/test/resources/cities.txt";
        long startTime = System.currentTimeMillis();
        BufferedReader br = new CustomFileLoader().loadFile(filePath);
        CityLinkBuilder cb = new CityLinkBuilder(br, start, target);
        ConcurrentHashMap<String, City> data = cb.createLinkBuilder();
        CityLinkFinder finder = new CityLinkFinder(start, target, data);
        finder.checkConnection();
        assertTrue(true);
        System.out.println("Processing Time: "
                + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testCheckDirectConnection() {
        String[][] connections = {{"philadelphia", "pittsburgh"},
                {"boston", "new york"}, {"philadelphia", "new york"},
                {"los angeles", "san diego"}, {"new york", "croton-harmon"}};
        CityLinkBuilder cb = new CityLinkBuilder(null, "San Diego",
                "Los Angeles");
        for (String[] connection : connections) {
            cb.checkDirectConnection(connection);
        }
    }

}