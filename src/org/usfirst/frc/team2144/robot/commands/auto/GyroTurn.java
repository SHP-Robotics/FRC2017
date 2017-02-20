package org.usfirst.frc.team2144.robot.commands.auto;

import org.usfirst.frc.team2144.robot.Constants;
import org.usfirst.frc.team2144.robot.commands.CommandBase;

/**
 *
 */
public class GyroTurn extends CommandBase {

	private double speed, angle;

	/**
	 * Turn to a given angle using navX gyro.
	 * 
	 * @param spd
	 *            The speed to turn at (positive for right, neg for left)
	 * @param angl
	 *            How far to turn (in degrees)
	 */
	public GyroTurn(double speed, double angle) {
		// Use requires() here to declare subsystem dependencies
		requires(sensors);
		requires(drivetrain);
		this.speed = -speed;
		this.angle = normalizeAngle(angle);
	}
	
	private double normalizeAngle(double angle) {
		while (angle < -180) {
			angle += 360;
		}
		while (angle > 180) {
			angle -= 360;
		}
		return angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		sensors.resetYaw();
		if (sensors.getYaw() < angle) {
			drivetrain.arcade(0, speed, false);
		} else {
			drivetrain.arcade(0, -speed, false);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (sensors.getYaw() < normalizeAngle(angle + 180) && sensors.getYaw() > normalizeAngle(angle)) {
			drivetrain.arcade(0, speed, false);
		} else {
			drivetrain.arcade(0, -speed, false);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return sensors.getYaw() > angle - Constants.K_TURN_TOLERANCE
				&& sensors.getYaw() < angle + Constants.K_TURN_TOLERANCE;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.arcade(0, 0);
		drivetrain.reset_encoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
