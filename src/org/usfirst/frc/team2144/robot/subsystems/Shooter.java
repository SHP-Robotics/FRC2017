package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private Encoder encoder;
	private Talon flywheel;
	private Talon intake;

	// Initialize your subsystem here
	public Shooter() {
		super("Shooter");
		encoder = new Encoder(RobotMap.FLYWHEEL_ENC_A, RobotMap.FLYWHEEL_ENC_B);
		flywheel = new Talon(RobotMap.FLYWHEEL_SHOOTER);
		intake = new Talon(RobotMap.FLYWHEEL_INTAKE);
		flywheel.setSafetyEnabled(false);
	}

	public void setIntake(double power) {
		intake.set(power);
	}

	public int getEnc() {
		return encoder.get();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new ShooterShoot());
	}

	public void setFlywheel(double power) {
		flywheel.set(power);
	}
}
