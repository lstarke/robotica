package t1;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Teste2 {
	
	public static void main(String[] args) {
		
		ColorSensor sensorCor = new ColorSensor(SensorPort.S2);
		
		final int DENTRO = 7;
		final int FORA   = 6;
		
		Motor.B.setSpeed(100);
		Motor.C.setSpeed(100);
		
		int valorLuz = 0;
		
		Motor.B.rotate(90, true);
		Motor.C.forward();
		
		
//		while (true) {
//			valorLuz = sensorCor.getColorID();
//			if (valorLuz == DENTRO) {
//				Motor.B.forward();
//				Motor.C.forward();
//			} else {
//				if (valorLuz == FORA) {
//					
//				}
//			}
//		}
	}

}
