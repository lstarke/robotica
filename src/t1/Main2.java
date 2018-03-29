package t1;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Main2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		ColorSensor sensorCor = new ColorSensor(SensorPort.S2);
		
		final int DENTRO = 7;
		final int FORA   = 6;
		
		int valorLuz = 0;
		boolean corrigeDir = false;
		boolean corrigeEsq = false;
		
		Motor.B.setSpeed(100);
		Motor.C.setSpeed(100);
		
		
		
		
		

			Motor.B.rotate(90, false);
			Motor.B.rotate(-90, false);			
			
			
			Motor.C.rotate(-90, false);
			Motor.C.rotate(90, false);
		
		
		
	}
		
		
}
