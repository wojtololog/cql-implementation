package model;

public class SensorData {
    private static long classIntanceNumber = 0;
    private long timestamp;
    private String name;
    private double temperature;

    public SensorData(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
        timestamp = ++classIntanceNumber;
    }

    public String getName() {
        return name;
    }

    public double getTemperature() {
        return temperature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "timestamp: " + timestamp + " " + name + ", temperature: " + temperature;
    }

    public boolean filterWithName(String name) {
        return this.getName().equals(name);
    }
}
