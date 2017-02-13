package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.Constants;
import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.GearManipDrive;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GearManip extends PIDSubsystem {
	
	private Spark motor;
	private AnalogPotentiometer pot;
	
	public GearManip() {
		super("GearManip", Constants.K_GEAR_P, Constants.K_GEAR_I, Constants.K_GEAR_D);
		motor = new Spark(RobotMap.GEAR_MOTOR);
		pot = new AnalogPotentiometer(RobotMap.GEAR_POT);
		setAbsoluteTolerance(0.005);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearManipDrive());
    }

	protected double returnPIDInput() {
		return pot.get();
	}

	protected void usePIDOutput(double output) {
		motor.set(output);
	}
}

