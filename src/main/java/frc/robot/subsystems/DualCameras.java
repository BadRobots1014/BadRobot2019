package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;

public class DualCameras extends BadSubsystem
{
    private VideoSink server;
    private UsbCamera camera1, camera2;

    @Override
    protected void initComponents()
    {
        VideoMode videoMode = new VideoMode(PixelFormat.kYUYV, 320, 180, 30);

        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setVideoMode(videoMode);
        camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setVideoMode(videoMode);
        camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

        server = CameraServer.getInstance().addServer("POV");
        server.setSource(camera1);

        System.err.println("POV Camera Server Created");
    }

    public void toggle()
    {
        if (server.getSource() == camera1)
            server.setSource(camera2);
        else
            server.setSource(camera1);
    }

    public static boolean isEnabled()
    {
        return true;
    }
}