package org.usfirst.frc.team2144.robot.commands.auto;

import org.usfirst.frc.team2144.robot.Constants;
import org.usfirst.frc.team2144.robot.commands.CommandBase;

/**
 *
 */
public class GyroFOD extends CommandBase {

	private double travelAngle, turnAngle, nyoomSpeed, turnSpeed;
	private int linearDistance;
	private boolean turnComplete = false, distanceComplete = false;

	/**
	 * This is a fun one - Field Oriented Drive.
	 * 
	 * @param linearDistance
	 *            - The linear distance (in... something. meters?) to drive.
	 * @param travelAngle
	 *            - The angle (in degrees, relative to navX 0) to travel at. (+
	 *            right, - left)
	 * @param turnAngle
	 *            - The angle (in degrees) to turn. (+ right, - left)
	 * @param nyoomSpeed
	 *            - The speed [0..1.0] to drive at.
	 * @param turnSpeed
	 *            - The speed [0..1.0] to turn at.
	 */
	public GyroFOD(int linearDistance, double travelAngle, double turnAngle, double nyoomSpeed, double turnSpeed) {
		// Use requires() here to declare subsystem dependencies
		requires(sensors);
		requires(drivetrain);
		this.linearDistance = linearDistance;
		this.travelAngle = travelAngle;
		this.turnAngle = turnAngle;
		this.nyoomSpeed = nyoomSpeed;
		this.turnSpeed = Math.abs(turnSpeed);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		sensors.resetYaw();
		sensors.resetDisplacement();
		drivetrain.reset_encoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double turnSpeed, x, y;

		if (sensors.getYaw() > turnAngle - Constants.K_TURN_TOLERANCE
				&& sensors.getYaw() < turnAngle + Constants.K_TURN_TOLERANCE) {
			turnComplete = true;
			turnSpeed = 0;
		} else {
			turnComplete = false;
			turnSpeed = this.turnSpeed;
			if (sensors.getYaw() > turnAngle) {
				turnSpeed = -turnSpeed;
			}
		}

		if (sensors.getDisplacement() > linearDistance - Constants.K_DISP_TOLERANCE
				&& sensors.getDisplacement() < linearDistance + Constants.K_DISP_TOLERANCE) {
			distanceComplete = true;
			x = 0;
			y = 0;
		} else {
			distanceComplete = false;
			x = nyoomSpeed * Math.cos(Math.toRadians(travelAngle));
			y = nyoomSpeed * Math.sin(Math.toRadians(travelAngle));
		}
		double yaw = sensors.getYaw() + 90; // godsdang it, hardware
		drivetrain.mecanum_cartesian(x, y, turnSpeed, yaw);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return turnComplete && distanceComplete;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.arcade(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
