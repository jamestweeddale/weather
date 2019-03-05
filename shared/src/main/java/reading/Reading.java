package reading;

import java.io.Serializable;

public interface Reading extends Serializable {

    Object getValue();
    Enum getUnits();
}
