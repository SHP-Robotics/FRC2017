package vproc;

import org.opencv.core.Mat;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class VProcSubsystem extends Subsystem {
	
	public AxisCamera camera;
	public double centerX = 0.0, centerY = 0.0;

	public final Object imgLock = new Object();

	public CvSink cvSink;
	public CvSource outputStream;
	public Mat mat = new Mat();
	
	public int[] target = new int[2];
	
	public boolean targetingRunning = true;
	
	public VProcSubsystem(String string) {
		super(string);
	}

}
