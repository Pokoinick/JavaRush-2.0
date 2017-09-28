package com.javarush.task.task27.task2712.ad;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Станислав on 16.09.2017.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public synchronized static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public Map<String, Integer> getAdvertisements() {
        Map<String, Integer> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement advertisement : storage.list()) {
            result.put(advertisement.getName(), advertisement.getHits());
        }
        return result;
    }

}
