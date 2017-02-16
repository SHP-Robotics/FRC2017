package org.usfirst.frc.team2144.robot.subsystems;

import org.opencv.core.Mat;
import org.usfirst.frc.team2144.robot.commands.VProc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.vision.VisionThread;
import vproc.VProcSubsystem;

/**
 *
 */
public class Camera extends VProcSubsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public UsbCamera camera;
	public VisionThread visionThread;
	public double centerX = 0.0, centerY = 0.0;

	public final Object imgLock = new Object();

	public CvSink cvSink;
	public CvSource outputStream;
	public Mat mat = new Mat();
	
	public int[] target = new int[2];
	
	public boolean targetingRunning = false;

	public Camera() {
		super("Camera");
		init();
		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		outputStream = CameraServer.getInstance().putVideo("Targeting", 320, 240);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();
		target[0] = 0;
		target[1] = 0;
	}

	public void init() {
		// Get the Axis camera from CameraServer
		camera = CameraServer.getInstance().startAutomaticCapture();
		// Set the resolution
		camera.setResolution(320, 240);
		camera.setBrightness(0);
		camera.setExposureManual(0);
		camera.setWhiteBalanceManual(50);
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new VProc());
	}

}
