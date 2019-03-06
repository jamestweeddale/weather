package station.io.convert;

public class MCP3008OutputConverter implements AnalogToDigitalValueConverter {

    public static final double VOLTAGE_CONVERSION_CONSTANT = (5.0 / 1024.0); //maps the value provided from the analog read function, which ranges from 0 to 1023, to actual voltage, which ranges from 0V to 5V

    private double sensorVoltageMin;   //min output voltage from sensor spec
    private double sensorVoltageMax;   //max output voltage from sensor spec
    private double maxValue;           //maximum value reported by the sensor (ex: 100mph)

    public MCP3008OutputConverter(double sensorVoltageMin, double sensorVoltageMax, double maxValue) {
        this.sensorVoltageMin = sensorVoltageMin;
        this.sensorVoltageMax = sensorVoltageMax;
        this.maxValue = maxValue;
    }

    @Override
    public double convert(double mcpOutputValue) {

        double sensorVoltage = mcpOutputValue * VOLTAGE_CONVERSION_CONSTANT; //Convert sensor value to actual voltage

        double windSpeed;
        if (sensorVoltage <= sensorVoltageMin) {
            windSpeed = 0.0; //Check if voltage is below minimum value. If so, set to zero.
        } else {
            windSpeed = (sensorVoltage - sensorVoltageMin) * maxValue / (sensorVoltageMax - sensorVoltageMin); //For voltages above minimum value, use the linear relationship to calculate value represented
        }

        return Math.round(windSpeed * 100.0) / 100.0;  //two decimal places of precision
    }

}
