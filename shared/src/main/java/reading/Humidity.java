package reading;

import com.fasterxml.jackson.annotation.JsonFormat;
import reading.units.HumidityUnits;

import java.time.ZonedDateTime;

public class Humidity implements Reading {

    private Double value = null;
    private HumidityUnits units = HumidityUnits.RH;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime time;

    public Humidity() {
    }

    public Humidity(Double value, HumidityUnits units, ZonedDateTime time) {
        this.value = value;
        this.units = units;
        this.time = time;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public HumidityUnits getUnits() {
        return units;
    }

    @Override
    public ZonedDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "value=" + value +
                ", units=" + units +
                ", time=" + time +
                '}';
    }
}
