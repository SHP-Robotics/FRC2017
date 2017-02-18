package org.usfirst.frc.team2144.robot.commands.auto;

import org.usfirst.frc.team2144.robot.Constants;
import org.usfirst.frc.team2144.robot.commands.CommandBase;

/**
 *
 */
public class GyroDrive extends CommandBase {

	private double speed;
	private int distance;
	private double kP = Constants.K_AUTO_P;

	public GyroDrive(double speed, int distance) {
		// Use requires() here to declare subsystem dependencies
		requires(sensors);
		requires(drivetrain);
		this.speed = speed;
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.reset_encoders();
		sensors.resetYaw();
		drivetrain.arcade(speed, 0, false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double error = sensors.getYaw() * kP;
		if (error > 1) {
			error = 1;
		} else if (error < -1) {
			error = -1;
		}
		drivetrain.arcade(speed, error, false);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return drivetrain.have_encoders_reached_pos(distance, distance);
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
