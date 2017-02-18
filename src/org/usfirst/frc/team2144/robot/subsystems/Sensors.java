package org.usfirst.frc.team2144.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Sensors extends Subsystem {

	AHRS navx;

	public Sensors() {
		try {
			navx = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
		LiveWindow.addSensor("Sensors", "navx", navx);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void resetNavX() {
		navx.reset();
	}

	public void resetYaw() {
		navx.zeroYaw();
	}

	public double getYaw() {
		return navx.getYaw();
	}

	public void resetDisplacement() {
		navx.resetDisplacement();
	}

	public double getDisplacement() {
		return Math.sqrt(Math.pow(navx.getDisplacementX(), 2) + Math.pow(navx.getDisplacementZ(), 2));
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}
}
