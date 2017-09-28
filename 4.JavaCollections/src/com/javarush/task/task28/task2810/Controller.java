package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;

/**
 * Created by Станислав on 22.08.2017.
 */
public class Controller {

    private Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

}
