package com.ank.fenics.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class City {
    private String name;
    private boolean visited;
    private Map<String, City> link;

    public City(final String name) {
        this.name = name;
        this.visited = false;
        link = new ConcurrentHashMap<String, City>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visited() {
        this.visited = true;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Map<String, City> getLink() {
        return link;
    }

    public boolean hasLinkWith(final String cityName) {
        if (link != null && !link.isEmpty()) {
            return link.containsKey(cityName);
        }
        return false;
    }

    public void addLink(final String cityName, final City cityLink) {
        this.link.put(cityName, cityLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getName().equals(city.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "City{" + name + "}";

    }
}
