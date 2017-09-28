package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by Станислав on 30.07.2017.
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(HumanFactoryType type) {
        if (type.equals(HumanFactoryType.MALE))
            return new MaleFactory();
        else
            return new FemaleFactory();
    }
    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }
}
