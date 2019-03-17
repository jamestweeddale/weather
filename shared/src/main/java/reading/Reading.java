package reading;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class Reading {

    private String name;

    private Double value = null;

    private ReadingUnits units;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime time;

    public Reading() {
    }

    public Reading(String name, Double value, ReadingUnits units, ZonedDateTime time) {
        this.name = name;
        this.value = value;
        this.units = units;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public ReadingUnits getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", time=" + time +
                ", units=" + units +
                '}';
    }

}
