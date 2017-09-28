package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Станислав on 16.09.2017.
 */
public class DirectorTablet {
    public void printAdvertisementProfit(){
        TreeMap<Date, Long> advertisementRevenueAgregatedByDay = StatisticManager.getInstance().getAdvertisementRevenueAgregatedByDay();
        if (advertisementRevenueAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = advertisementRevenueAgregatedByDay.descendingKeySet();
        Long totalAmmout = Long.valueOf(0);
        for (Date date : datesRow) {
            Long dayAmount = advertisementRevenueAgregatedByDay.get(date);
            totalAmmout += dayAmount;
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f",
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date),
                    0.01d * dayAmount)
            );
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", 0.01d * totalAmmout));
    }
    public void printCookWorkloading() {
        TreeMap<Date, HashMap<String, Integer>> cookWorkloadingAgregatedByDay = StatisticManager.getInstance().getCookWorkloadingAgregatedByDay();
        if (cookWorkloadingAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = cookWorkloadingAgregatedByDay.descendingKeySet();
        for (Date date: datesRow) {
            ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date));
            List<Map.Entry<String, Integer>> cooksNameWorkDuration = new ArrayList(cookWorkloadingAgregatedByDay.get(date).entrySet());
            Collections.sort(cooksNameWorkDuration, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });
            for (Map.Entry<String, Integer> cookNameWorkDuration: cooksNameWorkDuration) {
                ConsoleHelper.writeMessage(String.format("%s - %d min",
                        cookNameWorkDuration.getKey(),
                        (int)Math.ceil(cookNameWorkDuration.getValue() / 60.0))
                );
            }
        }
    }

    public void printActiveVideoSet() {
       Map<String, Integer> videoSet = StatisticAdvertisementManager.getInstance().getAdvertisements();
        for (Map.Entry<String, Integer> entry : videoSet.entrySet()) {
            if (entry.getValue() > 0)
                ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void printArchivedVideoSet() {
        Map<String, Integer> videoSet = StatisticAdvertisementManager.getInstance().getAdvertisements();
        for (Map.Entry<String, Integer> entry : videoSet.entrySet()) {
            if (entry.getValue() == 0)
                ConsoleHelper.writeMessage(entry.getKey());
        }
    }
}
