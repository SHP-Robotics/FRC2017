package org.usfirst.frc.team2144.robot.subsystems;

import org.usfirst.frc.team2144.robot.RobotMap;
import org.usfirst.frc.team2144.robot.commands.GatorDrive;

import edu.wpi.first.wpilibj.Encoder;
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
	private Encoder e_back_left, e_back_right, e_front_left, e_front_right;

	public Drivetrain() {
		super("Drivetrain");
		m_front_left = new Talon(RobotMap.FRONT_LEFT_MOTOR);
		m_back_left = new Talon(RobotMap.BACK_LEFT_MOTOR);
		m_front_right = new Talon(RobotMap.FRONT_RIGHT_MOTOR);
		m_back_right = new Talon(RobotMap.BACK_RIGHT_MOTOR);
		e_front_left = new Encoder(RobotMap.FRONT_LEFT_ENC_A, RobotMap.FRONT_LEFT_ENC_B);
		e_back_left = new Encoder(RobotMap.BACK_LEFT_ENC_A, RobotMap.BACK_LEFT_ENC_B);
		e_front_right = new Encoder(RobotMap.FRONT_RIGHT_ENC_A, RobotMap.FRONT_RIGHT_ENC_B);
		e_back_right = new Encoder(RobotMap.BACK_RIGHT_ENC_A, RobotMap.BACK_RIGHT_ENC_B);
		robit = new RobotDrive(m_front_left, m_back_left, m_front_right, m_back_right);
		m_front_right.setInverted(true);
		m_back_right.setInverted(true);
	}

	public void mecanum_cartesian(double x, double y, double rotation, double gyro) {
		robit.mecanumDrive_Cartesian(x, y, rotation, gyro);
	}

	public void tank(double left, double right) {
		left = -left;
		robit.tankDrive(left, right, true);
	}

	public void arcade(double spd, double rot) {
		robit.arcadeDrive(spd, rot);
	}

	public void arcade(double spd, double rot, boolean squared) {
		robit.arcadeDrive(spd, rot, squared);
	}

	public boolean have_encoders_reached_pos(int leftPos, int rightPos) {
		if (has_left_encoder_reached_pos(leftPos) && has_right_encoder_reached_pos(rightPos)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean has_left_encoder_reached_pos(int pos) {
		if (Math.abs(e_front_left.get()) >= pos && Math.abs(e_back_left.get()) >= pos) {
			return true;
		} else {
			return false;
		}
	}

	public boolean has_right_encoder_reached_pos(int pos) {
		if (Math.abs(e_front_right.get()) >= pos && e_back_right.get() >= pos) {
			return true;
		} else {
			return false;
		}
	}

	public void reset_left_encoder() {
		e_front_left.reset();
		e_back_left.reset();
	}

	public void reset_right_encoder() {
		e_front_right.reset();
		e_back_right.reset();
	}

	public void reset_encoders() {
		reset_left_encoder();
		reset_right_encoder();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new GatorDrive());
	}
}
