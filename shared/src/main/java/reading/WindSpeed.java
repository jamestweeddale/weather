package reading;

import reading.units.SpeedUnits;

public class WindSpeed implements Reading {

    private Double value = null;
    private SpeedUnits units = SpeedUnits.MPH;

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public SpeedUnits getUnits() {
        return units;
    }

    public void setUnits(SpeedUnits units) {
        this.units = units;
    }

}
