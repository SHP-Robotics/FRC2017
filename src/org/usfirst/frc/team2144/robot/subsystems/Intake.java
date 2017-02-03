package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.IntakeDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon intakeMotor;
	
	public Intake() {
		intakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeDrive());
    }
    
    public void setIntakeMotor(double power) {
    	intakeMotor.set(power);
    }
}

