package reading;

import reading.units.SpeedUnits;

public class WindSpeed implements Reading {

    private Double value = null;
    private SpeedUnits units = SpeedUnits.MPH;

    public WindSpeed() {
    }

    public WindSpeed(Double value, SpeedUnits units) {
        this.value = value;
        this.units = units;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public SpeedUnits getUnits() {
        return units;
    }

}
