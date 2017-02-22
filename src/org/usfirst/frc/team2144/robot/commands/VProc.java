package org.usfirst.frc.team2144.robot.commands;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import vproc.GripPipeline;

/**
 *
 */
public class VProc extends CommandBase {

	public VProc() {
		// Use requires() here to declare subsystem dependencies
		super("VProc");
		requires(camera);
		setRunWhenDisabled(true);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (camera.targetingRunning) {
			return;
		}
		System.out.println("Starting vision thread...");
		camera.visionThread = new VisionThread(camera.camera, new GripPipeline(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (camera.imgLock) {
					camera.centerX = r.x + (r.width / 2);
					camera.centerY = r.y + (r.height / 2);
				}
			} else {
				synchronized (camera.imgLock) {
					camera.centerX = -1;
					camera.centerY = -1;
				}
			}
			if (oi.getStick2POV() != -1) {
				int povDeg = oi.getStick2POV() - 90;
				double preProcdX = Math.cos(Math.toRadians(povDeg));
				double preProcdY = Math.sin(Math.toRadians(povDeg));
				if (povDeg % 90 != 0) {
					int dX = (int) (preProcdX > 0 ? Math.ceil(preProcdX) : Math.floor(preProcdX));
					int dY = (int) (preProcdY > 0 ? Math.ceil(preProcdY) : Math.floor(preProcdY));
					camera.target[0] += dX;
					camera.target[1] += dY;
				} else {
					int dX = (int) preProcdX;
					int dY = (int) preProcdY;
					camera.target[0] += dX;
					camera.target[1] += dY;
				}
				
			}

			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat. If there is an error notify the output.
			if (camera.cvSink.grabFrame(camera.mat) == 0) {
				// Send the output the error.
				camera.outputStream.notifyError(camera.cvSink.getError());
				System.out.println("AHHHHHHHHHH!");
			} else {
				// Put a circle on the image
				Imgproc.circle(camera.mat, new Point(camera.target[0], camera.target[1]), 5, new Scalar(0, 255, 0));
				Imgproc.circle(camera.mat, new Point(camera.centerX, camera.centerY), 5, new Scalar(255, 255, 255));
				// Give the output stream a new image to display
				camera.outputStream.putFrame(camera.mat);
			}
		});
		camera.visionThread.setDaemon(true);
		camera.visionThread.start();
		camera.targetingRunning = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("TargetX", camera.target[0]);
		SmartDashboard.putNumber("TargetY", camera.target[1]);
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
