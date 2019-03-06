package reading;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface Reading extends Serializable {

    Object getValue();
    Enum getUnits();
}
