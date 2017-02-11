package org.usfirst.frc.team2144.robot.commands;

import org.usfirst.frc.team2144.robot.OI;
import org.usfirst.frc.team2144.robot.subsystems.Camera;
import org.usfirst.frc.team2144.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2144.robot.subsystems.Intake;
import org.usfirst.frc.team2144.robot.subsystems.NoCamera;
import org.usfirst.frc.team2144.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import vproc.VProcSubsystem;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Zhu
 */
public abstract class CommandBase extends Command {

	public static OI oi;
	// Create a single static instance of all of your subsystems
	public static Drivetrain drivetrain = new Drivetrain();
	public static Shooter shooter = new Shooter();
	public static VProcSubsystem camera = new NoCamera();
	public static Intake intake = new Intake();

	public static void init() {
		// This MUST be here. If the OI creates Commands (which it very likely
		// will), constructing it during the construction of CommandBase (from
		// which commands extend), subsystems are not guaranteed to be
		// yet. Thus, their requires() statements may grab null pointers. Bad
		// news. Don't move it.
		oi = new OI();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(shooter);

	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}
}