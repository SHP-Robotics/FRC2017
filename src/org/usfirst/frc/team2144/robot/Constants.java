package org.usfirst.frc.team2144.robot;

public class Constants {
	
	// tolerances
	public static final double K_SHOOTER_TOLERANCE = 0.5; // in rps
	public static final double K_VISION_TOLERANCE = 7; // in px
	
	// PID
	public static final double K_GEAR_P = 15;
	public static final double K_GEAR_I = 0;
	public static final double K_GEAR_D = 0;
	
	// Dynamic constants
	public static final int D_SHOOTER_FIRE = 25;
	public static final int D_SHOOTER_REST = 0;
	public static final double D_SHOOTER_MAN_PWR = 1;
	public static final double D_SHOOTER_INTAKE_PWR = 0.5;
	
	public static final double D_GEAR_OPEN = 0.2377;
	public static final double D_GEAR_CLOSE = 0.1154;
}
