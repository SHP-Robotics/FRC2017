package org.usfirst.frc.team2144.robot.commands;

import org.usfirst.frc.team2144.robot.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ShooterShootVision extends CommandBase {

	double startSpool;

	public ShooterShootVision() {
		// Use requires() here to declare subsystem dependencies
		super("ShooterShootVision");
		requires(camera);
		requires(drivetrain);
		requires(shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startSpool = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.setFlywheel(Constants.D_SHOOTER_MAN_PWR);
		if (driveToTarget()) {
			if (Timer.getFPGATimestamp() > startSpool + Constants.D_SHOOTER_SPOOL_DELAY) {
				shooter.setIntake(Constants.D_SHOOTER_INTAKE_PWR);
			} else {
				shooter.setIntake(0);
			}
		} else {
			shooter.setIntake(0);
		}
	}

	private boolean driveToTarget() { // we should replace this with
										// calcDisttoTarget()
		double centerX, centerY;
		synchronized (camera.imgLock) {
			centerX = camera.centerX;
			centerY = camera.centerY;
		}
		double turn = centerX == -1 ? 0 : centerX - camera.target[0];
		// negative if to left of target, else positive
		double drv = centerY == -1 ? 0 : centerY - camera.target[1];
		turn = turn * Constants.K_TURN_MULTIPLIER;
		turn = turn < -0.3 ? -0.3 : turn > 0.3 ? 0.3 : turn; 
		// negative if to bottom of target, else positive
		drivetrain.arcade(turn , drv * -0.01, false);
		if (centerX == -1 || centerY == -1) {
			return false;
		} else {
			return Math.abs(turn) < Constants.K_VISION_TOLERANCE && Math.abs(drv) < Constants.K_VISION_TOLERANCE;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		shooter.setFlywheel(0);
		shooter.setIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
