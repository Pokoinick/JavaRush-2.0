package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by Станислав on 30.07.2017.
 */
public class Model {

    public List<String> getStringDataList() {
        return new Service().getData();
    }
}
