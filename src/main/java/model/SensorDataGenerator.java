package model;

import java.util.Random;

public class SensorDataGenerator {
   private int sensorsAmount;
   private double minTemperature;
   private double maxTemperature;

   private Random randomGenerator;

    public SensorDataGenerator(int sensorsAmount, double minTemperature, double maxTemperature) {
        this.sensorsAmount = sensorsAmount;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public SensorData generate() {
      return new SensorData(randomName(), randomTemperature());
    }

    private double randomTemperature() {
        randomGenerator = new Random();
        return randomGenerator.doubles(1, minTemperature, maxTemperature + 1).findFirst().getAsDouble();
    }

    private String randomName() {
        randomGenerator = new Random();
        int nameID = randomGenerator.ints(1, 1, sensorsAmount + 1).findFirst().getAsInt();
        String sensorName = "sensor" + nameID;
        return sensorName;
    }
}
