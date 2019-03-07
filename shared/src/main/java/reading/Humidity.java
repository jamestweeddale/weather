package reading;

import reading.units.HumidityUnits;

public class Humidity implements Reading {

    private Double value = null;
    private HumidityUnits units = HumidityUnits.RH;

    public Humidity() {
    }

    public Humidity(Double value, HumidityUnits units) {
        this.value = value;
        this.units = units;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public HumidityUnits getUnits() {
        return units;
    }

}
