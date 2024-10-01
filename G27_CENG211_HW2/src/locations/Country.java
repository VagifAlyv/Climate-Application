package locations;

import Climate.Temperature;
import java.util.ArrayList;
import java.util.List;

public class Country {
	private String name;
	private ArrayList<Temperature> temperatureMeasurements;

	public Country(String name) {
		this.temperatureMeasurements = generateCountryTemperature();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Temperature> getTemperature() {
		return temperatureMeasurements;
	}

	private ArrayList<Temperature> generateCountryTemperature() {
		ArrayList<Temperature> temp = new ArrayList<>();
		// i - years j - months
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < 12; j++) {
				temp.add(new Temperature(i + 2019, j + 1));
			}
		}
		return temp;
	}

}
