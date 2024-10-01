package Climate;
import java.util.Random;
public class Humidity extends ClimateMeasurement{

	private double humidityPercentage;
	Random random = new Random();
	
	public Humidity(int year, int month) {
		super(year, month);
		humidityPercentage = random.nextDouble(0.0, 100.0);
	}
	
	public double getHumidityPercentage() {
		return humidityPercentage;
	}
	
	public double generateHumidityPercentage() {
		return random.nextDouble(0.0, 100.0);
	}

	
}
