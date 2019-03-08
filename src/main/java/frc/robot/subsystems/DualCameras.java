package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;

public class DualCameras extends BadSubsystem
{
    // private VideoSink server;
    private UsbCamera camera;// , camera2;

    @Override
    protected void initComponents()
    {
        VideoMode videoMode = new VideoMode(PixelFormat.kYUYV, 320, 180, 30);

        camera = CameraServer.getInstance().startAutomaticCapture(0);
        camera.setVideoMode(videoMode);
        // camera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

        // camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        // camera2.setVideoMode(videoMode);
        // camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

        // server = CameraServer.getInstance().addServer("POV");
        // server.setSource(camera);

        System.err.println("POV Camera Server Created");
    }

    public void toggle()
    {
        // if (server.getSource() == camera)
        // server.setSource(camera2);
        // else
        // server.setSource(camera);
        System.err.println("Toggle cameras not implemented");
    }

    public static boolean isEnabled()
    {
        return true;
    }
}