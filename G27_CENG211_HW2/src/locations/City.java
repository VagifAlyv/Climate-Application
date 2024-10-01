package locations;

import java.util.ArrayList;
import Climate.ClimateMeasurement;
import Climate.Temperature;
import Climate.Humidity;
import Climate.RadiationAbsorbtion;
import Climate.WindSpeed;

public class City {

	private String name;
	private ArrayList<ClimateMeasurement> measurements;
	private ArrayList<Temperature> temperatureMeasurements;
	private ArrayList<Humidity> humidityMeasurements;
	private ArrayList<WindSpeed> windSpeedMeasurements;
	private ArrayList<RadiationAbsorbtion> unitAbsorbtionValue;
	private ArrayList<RadiationAbsorbtion> radiationIntensity;

	public City(String name) {
		this.name = name;
		this.measurements = new ArrayList<>();
		this.temperatureMeasurements = generateCityTemperature();
		this.humidityMeasurements = generateHumidity();
		this.windSpeedMeasurements = generateWindSpeed();
		this.unitAbsorbtionValue = generateUnitValue();
	}

	public ArrayList<ClimateMeasurement> getMeasurements() {
		return measurements;
	}

	public String getName() {
		return name;
	}

	public ArrayList<RadiationAbsorbtion> getUnitAbsorbtionValue() {
		return unitAbsorbtionValue;
	}

	public ArrayList<Temperature> getTemperature() {
		return temperatureMeasurements;
	}

	public ArrayList<Humidity> getHumidity() {
		return humidityMeasurements;
	}

	public ArrayList<WindSpeed> getWindSpeed() {
		return windSpeedMeasurements;
	}

	public ArrayList<RadiationAbsorbtion> getRadiationIntensity() {
		return radiationIntensity;
	}

	public void addMeasurement(ClimateMeasurement measurement) {
		measurements.add(measurement);
	}

	public void addTemperatureMeasurement(Temperature measurement) {
		temperatureMeasurements.add(measurement);
	}

	// i - year j - month
	private ArrayList<Temperature> generateCityTemperature() {
		ArrayList<Temperature> temp = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < 12; j++) {
				temp.add(new Temperature(i + 2019, j + 1));
			}
		}
		return temp;
	}

	private ArrayList<WindSpeed> generateWindSpeed() {
		ArrayList<WindSpeed> speed = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 12; j++) {
				WindSpeed windspeed = new WindSpeed(i + 2019, j + 1);
				speed.add(windspeed);
			}
		}
		return speed;
	}

	private ArrayList<Humidity> generateHumidity() {
		ArrayList<Humidity> humidity = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < 12; j++) {
				humidity.add(new Humidity(i + 2019, j + 1));
			}
		}
		return humidity;
	}

	private ArrayList<RadiationAbsorbtion> generateUnitValue() {
		ArrayList<RadiationAbsorbtion> radiation = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < 12; j++) {
				radiation.add(new RadiationAbsorbtion(i + 2019, j + 1));
			}
		}
		return radiation;
	}

}
