package reading;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface Reading {

    Object getValue();
    Enum getUnits();
}
