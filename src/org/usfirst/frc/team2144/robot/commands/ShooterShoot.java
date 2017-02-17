package org.usfirst.frc.team2144.robot.commands;

import org.usfirst.frc.team2144.robot.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterShoot extends CommandBase {
	private int lastMeasurement;
	private int targetSpeed;
	private double output;

	public ShooterShoot() {
		// Use requires() here to declare subsystem dependencies
		requires(shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		lastMeasurement = shooter.getEnc();
		targetSpeed = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int delta = shooter.getEnc() - lastMeasurement; // pulses per 1/20th of a sec (should be 83 @ 5000 RPM)
		lastMeasurement = shooter.getEnc();
		targetSpeed = oi.getFireButton() ? Constants.D_SHOOTER_FIRE : Constants.D_SHOOTER_REST;
		int error = targetSpeed - delta;
		if (targetSpeed != 0) {
			output += error / 100;
			output = output > 1 ? 1 : output < -1 ? -1 : output; 
		} else {
			output = 0;
		}
		shooter.setFlywheel(output);
		
		if (Math.abs(error) < Constants.K_SHOOTER_TOLERANCE) {
			shooter.setIntake(Constants.D_SHOOTER_INTAKE_PWR);
		} else {
			shooter.setIntake(0);
		}
		Timer.delay(0.05); // 20/sec
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
