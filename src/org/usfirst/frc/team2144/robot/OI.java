package org.usfirst.frc.team2144.robot;

import org.usfirst.frc.team2144.robot.commands.ShooterShoot;
import org.usfirst.frc.team2144.robot.commands.ShooterShootVision;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick left = new Joystick(0);
	private Joystick right = new Joystick(1);

	private JoystickButton visionShoot = new JoystickButton(right, ControlMap.B_VISION_FIRE);
	private JoystickButton shootyShoot = new JoystickButton(right, ControlMap.B_NORMAL_FIRE);

	public OI() {
		visionShoot.whileHeld(new ShooterShootVision());
		shootyShoot.whileHeld(new ShooterShoot());
	}

	public double getStickX() {
		return left.getX();
	}

	/**
	 * returns non-inverted Y
	 */
	public double getStickY() {
		return left.getY();
	}

	public double getStickRot() {
		return left.getTwist();
	}

	public double getStick2X() {
		return right.getX();
	}

	public double getStick2Y() {
		return right.getY();
	}

	public double getStick2Rot() {
		return right.getTwist();
	}

	public boolean getStick2Mecanum() {
		return right.getRawButton(ControlMap.B_MECANUM);
	}

	public boolean getGearButton() {
		return right.getRawButton(ControlMap.B_GEAR);
	}

	public int getStick2POV() {
		return right.getPOV();
	}

	public double getIntakePower() {
		return left.getRawButton(ControlMap.B_INTAKE) ? 1 : 0.2;
	}

	public boolean getClimbButton() {
		return left.getRawButton(ControlMap.B_CLIMB);
	}

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
