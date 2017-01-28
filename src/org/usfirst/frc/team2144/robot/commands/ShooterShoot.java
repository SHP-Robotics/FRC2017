package org.usfirst.frc.team2144.robot.commands;

import org.usfirst.frc.team2144.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterShoot extends CommandBase {

	public ShooterShoot() {
		// Use requires() here to declare subsystem dependencies
		requires(shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		shooter.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putBoolean("test", oi.getFireButton());
		SmartDashboard.putNumber("Flywheel Rate", shooter.getEncRate());
		if (oi.getFireButton()) {
			shooter.setSetpoint(Constants.D_SHOOTER_FIRE);
		} else {
			shooter.setSetpoint(Constants.D_SHOOTER_REST);
			return;
		}
		if (shooter.onTarget()) {
			shooter.setIntake(Constants.D_SHOOTER_INTAKE_PWR);
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		shooter.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
