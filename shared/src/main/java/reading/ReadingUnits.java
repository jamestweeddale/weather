package reading;

public enum ReadingUnits {
    INCHES("in"),
    CENTIMETERS("cm"),
    MILLIMETERS ("mm"),
    METERS ("meters"),
    MILES("miles"),
    RELATIVE_HUMIDITY ("rh"),
    MILES_PER_HOUR("mph"),
    KILOMETERS_PER_HOUR("kph"),
    METERS_PER_SECOND("mps"),
    FEET_PER_SECOND("fps"),
    FARENHEIT ("f"),
    CELCIUS ("c"),
    NOT_APPLICABLE("n/a");

    String name;

    ReadingUnits(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return name;
    }

    public ReadingUnits from(String unitString){

        if(unitString == null) return NOT_APPLICABLE;

        switch(unitString.toLowerCase()){
            case "in" : return INCHES;
            case "cm" : return CENTIMETERS;
            case "mm" : return MILLIMETERS;
            case "meters" : return METERS;
            case "miles" : return MILES;
            case "rh" : return RELATIVE_HUMIDITY;
            case "mph" : return MILES_PER_HOUR;
            case "kph" : return KILOMETERS_PER_HOUR;
            case "mps" : return METERS_PER_SECOND;
            case "fps" : return FEET_PER_SECOND;
            case "f" : return FARENHEIT;
            case "c" : return CELCIUS;

            default: return NOT_APPLICABLE;
        }
    }

}
