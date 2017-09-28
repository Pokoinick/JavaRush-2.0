package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    boolean isFound = false;
    public boolean rollback(int rollbackVersion) {
        Map<Integer, String> copy = new LinkedHashMap<>();
        copy.putAll(versionHistoryMap);
        for (Map.Entry<Integer, String> map : copy.entrySet()) {
            if (map.getKey() == rollbackVersion) {
                isFound = true;
                currentVersion = rollbackVersion;
            }
            if (map.getKey() > rollbackVersion && isFound) {
                versionHistoryMap.remove(map.getKey());
            }
        }
        if (isFound)
            return true;
        return false;
    }
}
