package Climate;

import java.util.ArrayList;
import java.util.Scanner;
import Climate.RadiationAbsorbtion.RadiationIntensity;
import fileIO.FileIO;
import locations.City;
import locations.Country;
import java.util.Random;

public class ClimateRecord {
	Scanner scanner;
	Random random = new Random();
	private ArrayList<Country> countries;
	private ArrayList<City> cities;

	public ClimateRecord() {
		this.scanner = new Scanner(System.in);
		this.countries = FileIO.readCountryCSVFile("countries_and_cities.csv");
		this.cities = FileIO.readCityCSVFile("countries_and_cities.csv");
	}

	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	public void run() {
		String option;
		do {
			printMenu();
			scanner = new Scanner(System.in);
			option = scanner.nextLine();
			switch (option) {
			case "1":
				calculateAverageTemperatureForCountry();
				break;
			case "2":
				calculateAverageTemperatureForCity();
				break;
			case "3":
				calculateAverageWindSpeedForCity();
				break;
			case "4":
				calculateAverageHumidityOfCity();
				break;
			case "5":
				calculateRadiationIntensityOccurrences();
				break;
			case "6":
				calculateFeltTemperature();
				break;
			case "7":
				System.out.println("Exiting the application...");
				break;
			default:
				System.out.println("Incorrect option input! Please reenter another option input.");
				System.out.println();
			}
		} while (!option.equals("7"));
	}

	// gives menu
	private void printMenu() {
		System.out.println("[1] Calculate average temperature for a country according to temperature unit and year.");
		System.out.println("[2] Calculate average temperature for a city according to temperature unit and year.");
		System.out.println("[3] Calculate average wind speed for a city according to speed unit and year.");
		System.out.println("[4] Calculate average humidity of a city for every year.");
		System.out.println("[5] Count how many times a year a specific radiation intensity value appears for a city.");
		System.out.println("[6] Calculate the 'felt temperature' value of a city for a specific month and year.");
		System.out.println("[7] Exit the application.");
		System.out.print("Please select an option: ");
	}

	// Helper method to get user input for the selected year
	private int getYearChoice() {
		scanner = new Scanner(System.in);
		int year;
		while (true) {
			System.out.println("[1] 2020 [2] 2021 [3] 2022");
			System.out.print("Please select the year: ");
			try {
				String input = scanner.nextLine();
				year = Integer.parseInt(input);

				if (year >= 1 && year <= 3) {
					break;
				} else {
					System.out.println("Invalid input! Try Again: ");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
		return year;

	}

	// Helper method to get user input for the selected month
	private int getMonthInput() {
		int monthChosenByUser;
		while (true) {
			try {
				System.out.println(
						"Enter the month of the year: [1] January [2] February [3] March [4] April [5] May [6] June: "
								+ "[7] July [8] August [9] September [10] October [11] November [12] December");
				String input = scanner.nextLine();
				monthChosenByUser = Integer.parseInt(input);
				if (monthChosenByUser >= 1 && monthChosenByUser <= 12) {
					break;
				} else {
					System.out.println("Invalid input. Please write a number between 1 and 12");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
		return getMonth(monthChosenByUser);
	}

	private int getMonth(int monthChosenByUser) {
		if (monthChosenByUser >= 1 && monthChosenByUser <= 12) {
			return monthChosenByUser;
		} else {
			return 0;
		}
	}

	// Calculates the average temperature for a specific country.
	public void calculateAverageTemperatureForCountry() {
		scanner = new Scanner(System.in);
		String countryName;
		Country selectedCountry = null;
		int unitChoice;
		int year;
		double sum = 0;
		int count = 0;
		while (true) {
			System.out.println("Enter the name of the country: ");
			countryName = scanner.nextLine();

			for (Country country : countries) {
				if (country.getName().equalsIgnoreCase(countryName)) {
					selectedCountry = country;
					break;
				}
			}
			if (selectedCountry != null) {
				break;
			} else {
				System.out.println("Country not found. Please enter a valid country name.");
			}
		}
		while (true) {
			System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
			System.out.print("Please select the temperature unit (1/2/3): ");
			try {
				String input = scanner.nextLine();
				unitChoice = Integer.parseInt(input);

				if (unitChoice >= 1 && unitChoice <= 3) {
					break;
				} else {
					System.out.println("Invalid unit selection. Please reenter another option input.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}

		year = getYearChoice();

		for (Temperature temperature : selectedCountry.getTemperature()) {
			if (temperature.getYear() == (year + 2019)) {
				double temperatureValue;

				if (unitChoice == 1) {
					temperatureValue = temperature.getCelsiusMeasurement();
				} else if (unitChoice == 2) {
					temperatureValue = temperature.getFahrenheitMeasurement();
				} else if (unitChoice == 3) {
					temperatureValue = temperature.getKelvinMeasurement();
				} else {
					throw new IllegalStateException("Unexpected value: " + unitChoice);
				}

				sum += temperatureValue;
				count++;
			}
		}

		double averageTemperature = sum / count;
		if (count <= 0) {
			System.out.println("No temperature measurements found for the specified criteria.");
		}
		System.out.println(
				"==> Average temperature of " + selectedCountry.getName() + " in " + temperatureType(unitChoice)
						+ " in " + (year + 2019) + ": " + String.format("%.2f", averageTemperature));
	}

	private String temperatureType(int unitChoice) {

		switch (unitChoice) {
		case 1:
			return "Celsius";
		case 2:
			return "Fahrenheit";
		case 3:
			return "Kelvin";
		default:
			throw new IllegalStateException("Unexpected value: " + unitChoice);
		}
	}

	// Calculates the average temperature for a specific city.
	public void calculateAverageTemperatureForCity() {
		scanner = new Scanner(System.in);
		String cityName;
		City selectedCity = null;
		int unitChoice;
		int year;
		double sum = 0;
		int count = 0;

		while (true) {
			System.out.println("Enter the name of the city: ");
			cityName = scanner.nextLine();
			selectedCity = findCityByName(cityName);
			if (selectedCity != null) {
				break;
			} else {
				System.out.println("City not found. Please enter a valid city name.");
			}
		}
		while (true) {
			System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
			System.out.print("Please select the temperature unit (1/2/3): ");
			try {
				String input = scanner.nextLine();
				unitChoice = Integer.parseInt(input);

				if (unitChoice >= 1 && unitChoice <= 3) {
					break;
				} else {
					System.out.println("Invalid unit selection. Please reenter another option input.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
		year = getYearChoice();
		for (Temperature temperature : selectedCity.getTemperature()) {
			if (temperature.getYear() == (year + 2019)) {
				double temperatureValue;

				switch (unitChoice) {
				case 1:
					temperatureValue = temperature.getCelsiusMeasurement();
					break;
				case 2:
					temperatureValue = temperature.getFahrenheitMeasurement();
					break;
				case 3:
					temperatureValue = temperature.getKelvinMeasurement();
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + unitChoice);
				}

				sum += temperatureValue;
				count++;
			}
		}
		double averageTemperature = sum / count;
		if (count <= 0) {
			System.out.println("No temperature measurements found for the specified criteria.");
		}

		System.out.println("==> Average temperature of " + selectedCity.getName() + " in " + temperatureType(unitChoice)
				+ " in " + (year + 2019) + ": " + String.format("%.2f", averageTemperature));

	}

	// Calculates the average wind speed for a specific city.
	public void calculateAverageWindSpeedForCity() {
		scanner = new Scanner(System.in);
		double sum = 0;
		int count = 0;
		int unitChoice = 0;
		City selectedCity = null;
		double windSpeedValue = 0;
		String windSpeedType = "";

		while (true) {
			System.out.println("Enter the name of the city: ");
			String cityName = scanner.nextLine();

			selectedCity = findCityByName(cityName);

			if (selectedCity != null) {
				break;
			} else {
				System.out.println("City not found. Please enter a valid city name.");
			}
		}
		while (true) {
			System.out.println("[1] meterPerSecond  [2] kilometerPerSecond");
			System.out.print("Please select the temperature unit (1/2): ");
			try {
				String input = scanner.nextLine();
				unitChoice = Integer.parseInt(input);

				if (unitChoice >= 1 && unitChoice <= 2) {
					break;
				} else {
					System.out.println("Invalid unit selection. Please reenter another option input.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}

		int monthValue = getMonthInput();
		for (WindSpeed windspeed : selectedCity.getWindSpeed()) {
			if (monthValue == windspeed.getMonth()) {
				if (unitChoice == 1) {
					windSpeedValue = windspeed.getMetersPerSecond();
					windSpeedType = "m/s";
				} else if (unitChoice == 2) {
					windSpeedValue = windspeed.getKilometersPerHour();
					windSpeedType = "km/h";
				}

				sum += windSpeedValue;
				count++;
			}
		}
		double windSpeedAverage = sum / count;
		if (count <= 0) {
			System.out.println("No temperature measurements found for the specified criteria.");
		}

		System.out.println(selectedCity.getName() + "'s average wind speed in " + months[monthValue - 1]
				+ " in the last 3 years is: " + String.format("%.2f", windSpeedAverage) + " " + windSpeedType);
		System.out.println();
	}

	// Calculates the average humidity for a specific city.
	public void calculateAverageHumidityOfCity() {
		scanner = new Scanner(System.in);
		String cityName;
		City selectedCity = null;
		double sum = 0;
		int count = 0;

		while (true) {
			System.out.println("Enter the name of the city: ");
			cityName = scanner.nextLine();

			for (City city : cities) {
				if (city.getName().equalsIgnoreCase(cityName)) {
					selectedCity = city;
					break;
				}
			}

			if (selectedCity != null) {
				break;
			} else {
				System.out.println("City not found. Please enter a valid city name.");
			}
		}

		for (Humidity humidity : selectedCity.getHumidity()) {
			for (int i = 0; i < 3; i++) {
				sum += humidity.generateHumidityPercentage();
				count++;
			}
		}

		double averageHumidity = sum / count;
		if (count <= 0) {
			System.out.println("No humidity measurements found for the specified city.");
		}
		System.out.println("Average humidity of " + selectedCity.getName() + " across the 3-year period: "
				+ String.format("%.2f", averageHumidity / 100));
		System.out.println();
	}

	// Counts occurrences of a specific radiation intensity value for a city.
	public void calculateRadiationIntensityOccurrences() {
		scanner = new Scanner(System.in);
		String cityName;
		City selectedCity = null;
		RadiationIntensity intensity = null;
		RadiationIntensity IntensityOccurence = null;
		String outputIntensity = null;
		while (true) {
			System.out.print("Enter the city name: ");
			cityName = scanner.nextLine();

			for (City city : cities) {
				if (city.getName().equalsIgnoreCase(cityName)) {
					selectedCity = city;
					break;
				}
			}
			if (selectedCity != null) {
				break;
			} else {
				System.out.println("City not found. Please enter a valid city name.");
			}
		}
		while (true) {
			try {
				System.out.print("[1] Low [2] Medium [3] High\nPlease select a radiation intensity value: ");
				String input = scanner.nextLine();
				int intensityInput = Integer.parseInt(input);
				if (intensityInput >= 1 && intensityInput <= 3) {
					if (intensityInput == 1) {
						intensity = RadiationIntensity.LOW;
						outputIntensity = "low";
					} else if (intensityInput == 2) {
						intensity = RadiationIntensity.MEDIUM;
						outputIntensity = "medium";
					} else if (intensityInput == 3) {
						intensity = RadiationIntensity.HIGH;
						outputIntensity = "high";
					}
					break;
				} else {
					System.out.println("Invalid radiation intensity value. Please reenter another option.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
		int year = getYearChoice();
		int count = 0;
		for (int i = 0; i < 12; i++) {
			IntensityOccurence = generateRandomRadiationIntensity();
			if (IntensityOccurence == intensity) {
				count++;
			}
		}
		System.out.println("Total count of " + outputIntensity + " radiation intensity in " + cityName + " in "
				+ (year + 2019) + ": " + count);
		System.out.println();
	}

	private RadiationIntensity generateRandomRadiationIntensity() {
		int index = random.nextInt(RadiationIntensity.values().length);
		return RadiationIntensity.values()[index];
	}

	// calculating felt temperature for specific city
	public void calculateFeltTemperature() {
		scanner = new Scanner(System.in);
		String cityName;
		City selectedCity = null;
		while (true) {
			System.out.println("Enter the name of the city: ");
			cityName = scanner.nextLine();

			selectedCity = findCityByName(cityName);

			if (selectedCity != null) {
				break;
			} else {
				System.out.println("City not found. Please enter a valid city name.");
			}
		}
		int year = getYearChoice();
		int monthValue = getMonthInput();
		int humiditySum = 0;
		double cityTemperature = 0;
		double radiationUnitAbsorptionValue = 0;
		double windSpeed = 0;

		for (Humidity humidity : selectedCity.getHumidity()) {
			if (monthValue == humidity.getMonth() && (year + 2019) == humidity.getYear()) {
				humiditySum += humidity.generateHumidityPercentage();
			}
		}
		double humidity = humiditySum / 100;

		for (Temperature temperature : selectedCity.getTemperature()) {
			if (monthValue == temperature.getMonth() && (year + 2019) == temperature.getYear()) {
				cityTemperature = temperature.getCelsiusMeasurement();
			}
		}

		for (RadiationAbsorbtion radiation : selectedCity.getUnitAbsorbtionValue()) {
			if (monthValue == radiation.getMonth() && (year + 2019) == radiation.getYear()) {
				radiationUnitAbsorptionValue = radiation.getUnitAbsorbtionValue();
			}
		}

		for (WindSpeed windspeed : selectedCity.getWindSpeed()) {
			if (monthValue == windspeed.getMonth() && (year + 2019) == windspeed.getYear()) {
				windSpeed = windspeed.getMetersPerSecond();
			}
		}

		double feltTemperature = cityTemperature + 0.3 * humidity
				- 0.7 * (radiationUnitAbsorptionValue / (windSpeed + 10));
		System.out.println("Felt temperature for " + selectedCity.getName() + " in " + (2019 + year) + " in "
				+ months[monthValue - 1] + " is " + String.format("%.2f", feltTemperature));
		System.out.println();
	}

	private City findCityByName(String cityName) {
		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(cityName)) {
				return city;
			}
		}
		return null;
	}

}
