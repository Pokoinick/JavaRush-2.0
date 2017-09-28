package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Станислав on 07.08.2017.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (Long l : ids) {
            strings.add(shortener.getString(l));
        }
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }


        Long time1 = getTimeForGettingIds(shortener1, origStrings, new HashSet<>());
        Long time2 = getTimeForGettingIds(shortener2, origStrings, new HashSet<>());

        Assert.assertTrue(time1 > time2);

        Long time3 = getTimeForGettingStrings(shortener1, new HashSet<>(), new HashSet<>());
        Long time4 = getTimeForGettingStrings(shortener2, new HashSet<>(), new HashSet<>());

        Assert.assertEquals(time3, time4, 30);

    }
}
