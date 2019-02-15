package frc.robot.utils;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;

public class CameraProcessingThread extends Thread
{
    private final UsbCamera camera;
    private final CvSink inputStream;
    private final CvSource outputStream;

    public CameraProcessingThread()
    {
        int width = 320, height = 240, fps = 30;

        // camera = new UsbCamera("POV Camera", 0);

        // MjpegServer mjpegServer = CameraServer.getInstance().addServer("MJPEG Server
        // 1");
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setPixelFormat(PixelFormat.kYUYV);
        camera.setResolution(width, height);
        camera.setFPS(fps);
        // TODO remove this
        // System.out.println(camera.enumerateProperties());
        // System.out.println(camera.getConfigJson());
        // System.out.println(UsbCamera.enumerateUsbCameras());

        inputStream = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("POV Output", width, height);
    }

    private Mat processFrame(Mat source)
    {
        Mat output = new Mat();

        // Do processing operations here
        // Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);

        return source;
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