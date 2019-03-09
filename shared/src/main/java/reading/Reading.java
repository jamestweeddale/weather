package reading;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.ZonedDateTime;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface Reading {

    Object getValue();
    Enum getUnits();
    ZonedDateTime getTime();
}
