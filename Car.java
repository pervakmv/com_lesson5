package com.lesson5;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "model",
        "color",
        "weight"
})
public class Car implements Serializable {
    //Сделан для проверки некоторых моментов
    String model;
    String color;
    int weight;

    public Car() {
    }

    public Car(String model, String color, int weight) {
        this.model = model;
        this.color = color;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }{}

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
