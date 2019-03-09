package reading;

import com.fasterxml.jackson.annotation.JsonFormat;
import reading.units.SpeedUnits;

import java.time.ZonedDateTime;

public class WindSpeed implements Reading {

    private Double value = null;
    private SpeedUnits units = SpeedUnits.MPH;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime time;

    public WindSpeed() {
    }

    public WindSpeed(Double value, SpeedUnits units, ZonedDateTime time) {
        this.value = value;
        this.units = units;
        this.time = time;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public SpeedUnits getUnits() {
        return units;
    }

    @Override
    public ZonedDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "WindSpeed{" +
                "value=" + value +
                ", units=" + units +
                ", time=" + time +
                '}';
    }
}
