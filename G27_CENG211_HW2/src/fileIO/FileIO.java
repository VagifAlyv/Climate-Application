package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import locations.City;
import locations.Country;

public class FileIO {
	public static ArrayList<Country> readCountryCSVFile(String filePath) {
		ArrayList<Country> countries = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > 0) {
					// The first element in each row is the country name
					String countryName = parts[0].trim();
					Country country = new Country(countryName);
					countries.add(country);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return countries;
	}

	public static ArrayList<City> readCityCSVFile(String filePath) {
		ArrayList<City> cities = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				for (int i = 1; i < parts.length; i++) {
					String cityName = parts[i].trim();
					City city = new City(cityName);
					cities.add(city);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cities;
	}
}
