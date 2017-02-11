package org.usfirst.frc.team2144.robot;

public class Constants {
	// tolerances
	public static double K_SHOOTER_TOLERANCE = 0.5; // in rps
	public static double K_VISION_TOLERANCE = 3; // in px
	
	// PID
	public static double K_GEAR_P = 1;
	public static double K_GEAR_I = 0.1;
	public static double K_GEAR_D = 0.1;
	
	// Dynamic constants
	public static int D_SHOOTER_FIRE = 25;
	public static int D_SHOOTER_REST = 0;
	public static double D_SHOOTER_INTAKE_PWR = 0.5;
	
	public static double D_GEAR_OPEN = 0.5;
	public static double D_GEAR_CLOSE = 0.4;
}
