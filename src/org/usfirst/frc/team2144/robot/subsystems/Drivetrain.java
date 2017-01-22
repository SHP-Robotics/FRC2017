package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.GatorDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public RobotDrive robit;
	private Talon m_front_left, m_back_left, m_front_right, m_back_right;
	
	public Drivetrain() {
		m_front_left = new Talon(RobotMap.FRONT_LEFT_MOTOR);
		m_back_left = new Talon(RobotMap.BACK_LEFT_MOTOR);
		m_front_right = new Talon(RobotMap.FRONT_RIGHT_MOTOR);
		m_back_right = new Talon(RobotMap.BACK_RIGHT_MOTOR);
		robit = new RobotDrive(m_front_left, m_back_left, m_front_right, m_back_right);
	}
	
	public void mecanum_cartesian(double x, double y, double rotation, double gyro) {
		robit.mecanumDrive_Cartesian(x, y, rotation, gyro);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GatorDrive());
    }
}

