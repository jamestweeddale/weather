package reading;

import reading.units.TemperatureUnits;

public class Temperature implements Reading {

    private Double value = null;
    private TemperatureUnits units = TemperatureUnits.FARENHEIT;

    public Temperature() {
    }

    public Temperature(Double value, TemperatureUnits units) {
        this.value = value;
        this.units = units;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public TemperatureUnits getUnits() {
        return units;
    }

}
