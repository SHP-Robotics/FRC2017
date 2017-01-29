package org.usfirst.frc.team2144.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2144.robot.commands.VProc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;
import vproc.GripPipeline;

/**
 *
 */
public class Camera extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public AxisCamera camera;
	private VisionThread visionThread;
	public double centerX = 0.0, centerY = 0.0;

	public final Object imgLock = new Object();

	public Camera() {
		super("Camera");
	}

	public void init() {
		// Get the Axis camera from CameraServer
		camera = CameraServer.getInstance().addAxisCamera("Shooter Cam", "axis-camera.local");
		// Set the resolution
		camera.setResolution(320, 240);
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (imgLock) {
					centerX = r.x + (r.width / 2);
					centerY = r.y + (r.height / 2);
				}
			}
		});
		visionThread.start();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new VProc());
	}
}
