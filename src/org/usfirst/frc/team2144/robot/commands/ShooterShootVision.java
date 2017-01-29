package org.usfirst.frc.team2144.robot.commands;

import org.usfirst.frc.team2144.robot.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ShooterShootVision extends CommandBase {

	int[] target = new int[2];
	double startSpool;
	

	public ShooterShootVision() {
		// Use requires() here to declare subsystem dependencies
		super("ShooterShootVision");
		requires(camera);
		requires(drivetrain);
		requires(shooter);

		target[0] = 0;
		target[1] = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startSpool = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.setFlywheel(1);
		doTargeting();
		if (driveToTarget()) {
			if (Timer.getFPGATimestamp() > startSpool + 1.0) {
				shooter.setIntake(Constants.D_SHOOTER_INTAKE_PWR);
			}
		}
	}
	
	private void doTargeting() {
		if (oi.getStick2POV() != -1) {
			int povDeg = oi.getStick2POV() - 90;
			double preProcdX = Math.cos(Math.toRadians(povDeg));
			double preProcdY = Math.sin(Math.toRadians(povDeg));
			if (povDeg % 90 != 0) {
				int dX = (int) (preProcdX > 0 ? Math.ceil(preProcdX) : Math.floor(preProcdX));
				int dY = (int) (preProcdY > 0 ? Math.ceil(preProcdY) : Math.floor(preProcdY));
				target[0] += dX;
				target[1] += dY;
			} else {
				int dX = (int) preProcdX;
				int dY = (int) preProcdY;
				target[0] += dX;
				target[1] += dY;
			}
		}
	}
	
	private boolean driveToTarget() { // we should replace this with calcDisttoTarget()
		double centerX, centerY;
		synchronized (camera.imgLock) {
			centerX = camera.centerX;
			centerY = camera.centerY;
		}
		double turn = centerX - target[0]; // negative if to left of target,
											// else positive
		double drv = centerY - target[1]; // negative if to bottom of target,
											// else positive
		drivetrain.arcade(drv * 0.005, -turn * 0.005, false);
		return Math.abs(turn) < Constants.K_VISION_TOLERANCE && Math.abs(drv) < Constants.K_VISION_TOLERANCE;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		shooter.setFlywheel(0);
		shooter.setIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
