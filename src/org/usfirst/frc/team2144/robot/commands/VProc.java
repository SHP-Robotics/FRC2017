package org.usfirst.frc.team2144.robot.commands;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class VProc extends CommandBase {

	int[] target;

	public VProc() {
		// Use requires() here to declare subsystem dependencies
		super("VProc");
		requires(camera);
		
		target = new int[2];
		target[0] = 0;
		target[1] = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int dX = (int) Math.ceil(Math.cos(Math.toRadians(oi.getStick2POV())));
		int dY = (int) Math.ceil(Math.sin(Math.toRadians(oi.getStick2POV())));
		target[0] += dX;
		target[1] += dY;
		
		
		// Tell the CvSink to grab a frame from the camera and put it
		// in the source mat. If there is an error notify the output.
		if (camera.cvSink.grabFrame(camera.mat) == 0) {
			// Send the output the error.
			camera.outputStream.notifyError(camera.cvSink.getError());
			// skip the rest of the current iteration
			return;
		}
		// Put a circle on the image
		Imgproc.circle(camera.mat, new Point(target[0], target[1]), 2, new Scalar(0, 255, 0));
		// Give the output stream a new image to display
		camera.outputStream.putFrame(camera.mat);
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
