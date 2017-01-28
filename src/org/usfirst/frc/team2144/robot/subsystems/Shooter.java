package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.Constants;
import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends PIDSubsystem {

	private Encoder encoder;
	private Talon flywheel; // controlled by PID
	private Talon intake;

	// Initialize your subsystem here
	public Shooter() {
		super("Shooter", Constants.K_SHOOTER_P, Constants.K_SHOOTER_I, Constants.K_SHOOTER_D, Constants.K_SHOOTER_F);
		encoder = new Encoder(RobotMap.FLYWHEEL_ENC_A, RobotMap.FLYWHEEL_ENC_B);
		encoder.setDistancePerPulse(1); // 40 ppr
		setOutputRange(-1, 1);
		setSetpoint(0); // for shooting we want to target 75 rps
		setAbsoluteTolerance(Constants.K_SHOOTER_TOLERANCE);
		flywheel = new Talon(RobotMap.FLYWHEEL_SHOOTER);
		intake = new Talon(RobotMap.FLYWHEEL_INTAKE);
	}

	public void setIntake(double power) {
		intake.set(power);
	}

	public double getEncRate() {
		return encoder.getRate();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ShooterShoot());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		SmartDashboard.putNumber("Flywheel Rate", encoder.getRate() / 40);
		return encoder.getRate() / 40; // rev/sec, 40ppr
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		flywheel.set(output);
	}
}
