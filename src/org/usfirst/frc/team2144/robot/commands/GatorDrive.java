package org.usfirst.frc.team2144.robot.commands;

/**
 *
 */
public class GatorDrive extends CommandBase {

	public GatorDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (oi.getStick2Mecanum()) {
			drivetrain.mecanum_cartesian(oi.getStick2X(), oi.getStick2Y(), oi.getStick2Rot(), 0);
		} else {
			drivetrain.tank(oi.getStickY(), oi.getStick2Y());
		}
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
		end();
	}
}
