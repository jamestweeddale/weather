package reading;

import com.fasterxml.jackson.annotation.JsonFormat;
import reading.units.TemperatureUnits;

import java.time.ZonedDateTime;

public class Temperature implements Reading {

    private Double value = null;
    private TemperatureUnits units = TemperatureUnits.FARENHEIT;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime time;

    public Temperature() {
    }

    public Temperature(Double value, TemperatureUnits units, ZonedDateTime time) {
        this.value = value;
        this.units = units;
        this.time = time;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public TemperatureUnits getUnits() {
        return units;
    }

    @Override
    public ZonedDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                ", units=" + units +
                ", time=" + time +
                '}';
    }
}
