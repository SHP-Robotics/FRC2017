package org.usfirst.frc.team2144.robot.subsystems;

import org.opencv.core.Mat;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public AxisCamera camera;
	public CvSink cvSink;
	public CvSource outputStream;
	public Mat mat;

	public Camera() {
		super("Camera");
		// Get the Axis camera from CameraServer
		camera = CameraServer.getInstance().addAxisCamera("Shooter Cam", "axis-camera.local");
		// Set the resolution
		camera.setResolution(640, 480);
		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		outputStream = CameraServer.getInstance().putVideo("Shooter", 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

