package org.usfirst.frc.team2144.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// PWM
	public static int FRONT_LEFT_MOTOR = 0;
	public static int BACK_LEFT_MOTOR = 1;
	public static int FRONT_RIGHT_MOTOR = 2;
	public static int BACK_RIGHT_MOTOR = 3;
	
	public static int FLYWHEEL_SHOOTER = 4;
	public static int FLYWHEEL_INTAKE = 5;
	
	public static int INTAKE_MOTOR = 6;
	
	public static int GEAR_MOTOR = 7;
	
	// DIO
	public static int FLYWHEEL_ENC_A = 0;
	public static int FLYWHEEL_ENC_B = 1;
	
	public static int BACK_LEFT_ENC_A = 2;
	public static int BACK_LEFT_ENC_B = 3;
	public static int BACK_RIGHT_ENC_A = 6;
	public static int BACK_RIGHT_ENC_B = 7;
	
	// Analog
	public static int GEAR_POT = 0;
	
}
