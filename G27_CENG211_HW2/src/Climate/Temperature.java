package Climate;

import java.util.Random;

public class Temperature extends ClimateMeasurement {

	private double celsiusMeasurement;
	private double fahrenheitMeasurement;
	private double kelvinMeasurement;
	Random random = new Random();

	public Temperature(int year, int month) {
		super(year, month);
		this.celsiusMeasurement = random.nextDouble(-40, 50);
		this.fahrenheitMeasurement = celsiusToFahrenheit(celsiusMeasurement);
		this.kelvinMeasurement = celsiusToKelvin(celsiusMeasurement);

	}

	private double celsiusToFahrenheit(double celsius) {
		return (celsius * 9 / 5) + 32;
	}

	private double celsiusToKelvin(double celsius) {
		return celsius + 273;
	}

	public double getCelsiusMeasurement() {
		return celsiusMeasurement;
	}

	public double getFahrenheitMeasurement() {
		return fahrenheitMeasurement;
	}

	public double getKelvinMeasurement() {
		return kelvinMeasurement;
	}

}
