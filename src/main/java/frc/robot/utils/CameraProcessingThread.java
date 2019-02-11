package frc.robot.utils;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class CameraProcessingThread extends Thread
{
    private final UsbCamera camera;
    private final CvSink inputStream;
    private final CvSource outputStream;

    public CameraProcessingThread()
    {
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);

        inputStream = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
    }

    private Mat processFrame(Mat source)
    {
        Mat output = new Mat();

        // Do processing operations here
        Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);

        return output;
    }

    @Override
    public void run()
    {
        Mat source = new Mat();

        while (!Thread.interrupted())
        {
            inputStream.grabFrame(source);
            outputStream.putFrame(processFrame(source));
        }
    }
}