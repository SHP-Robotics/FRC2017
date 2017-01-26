package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.Constants;
import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	private Encoder encoder;
	private Talon flywheel;

    // Initialize your subsystem here
    public Shooter() {
    	super("Shooter", Constants.K_SHOOTER_P, Constants.K_SHOOTER_I, Constants.K_SHOOTER_D, Constants.K_SHOOTER_F);
    	encoder = new Encoder(RobotMap.FLYWHEEL_ENC_A, RobotMap.FLYWHEEL_ENC_B);
    	encoder.setDistancePerPulse(1/20);
    	setOutputRange(-1, 1);
        setSetpoint(0); // for shooting we want to target 75 rps
    	setAbsoluteTolerance(Constants.K_SHOOTER_TOLERANCE);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShooterShoot());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return encoder.getRate(); // rev/sec
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	flywheel.set(output);
    }
}
