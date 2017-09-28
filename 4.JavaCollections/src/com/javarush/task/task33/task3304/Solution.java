package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter buffer = new StringWriter();
        mapper.writeValue(buffer, one);
        String result = buffer.toString();
        String original = one.getClass().getSimpleName().toLowerCase();
        String export = resultClassObject.getSimpleName().toLowerCase();
        result = buffer.toString().replaceFirst(original, export);
        return mapper.readValue(result, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        @JsonProperty
        public int i = 5;
        @JsonProperty
        public String name = "first";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        @JsonProperty
        public int i = 10;
        @JsonProperty
        public String name = "second";
    }
}
