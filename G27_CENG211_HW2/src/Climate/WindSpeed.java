package Climate;
import java.util.Random;
public class WindSpeed extends ClimateMeasurement{
	private double metersPerSecond;
	private double kmPerHour;
	Random random = new Random();
	
	
	public WindSpeed(int year, int month) {
		super(year, month);
		metersPerSecond = random.nextDouble(0.0, 113.2);
		kmPerHour = metersPerSecondToKmPerHour(metersPerSecond);

	}
	private double metersPerSecondToKmPerHour(double mps) {
		return mps * 3.6;
	}
	
	public double getMetersPerSecond() {
		return metersPerSecond;
	}
	
	public double getKilometersPerHour() {
		return kmPerHour;
	}
	
	public int getMonth() {
		return month;
	}
	
}
