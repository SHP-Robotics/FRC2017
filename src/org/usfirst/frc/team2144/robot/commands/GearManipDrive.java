package org.usfirst.frc.team2144.robot.commands;

import org.usfirst.frc.team2144.robot.Constants;

/**
 *
 */
public class GearManipDrive extends CommandBase {

    public GearManipDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(gearManip);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearManip.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (oi.getGearButton()) {
    		gearManip.setSetpoint(Constants.D_GEAR_OPEN);
    	} else {
    		gearManip.setSetpoint(Constants.D_GEAR_CLOSE);
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
    }
}
