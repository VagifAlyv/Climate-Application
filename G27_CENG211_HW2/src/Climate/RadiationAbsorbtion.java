package Climate;

import java.util.Random;

public class RadiationAbsorbtion extends ClimateMeasurement {

	enum RadiationIntensity {
		LOW, MEDIUM, HIGH
	}

	private RadiationIntensity radiationIntensity;
	private double unitAbsorbtionValue;
	Random random = new Random();

	public RadiationAbsorbtion(int year, int month) {
		super(year, month);
		this.unitAbsorbtionValue = generateUnitAbsorbtionValue();
	}

	public RadiationIntensity getRadiationIntensity() {
		return radiationIntensity;
	}

	public double getUnitAbsorbtionValue() {
		return unitAbsorbtionValue;
	}

	public double generateUnitAbsorbtionValue() {
		return random.nextDouble(5, 20);
	}
}
