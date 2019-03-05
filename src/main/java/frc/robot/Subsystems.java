package frc.robot;

import frc.robot.subsystems.Articulator;
import frc.robot.subsystems.BackHatchCam;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DualCameras;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.MulticolorSensor;

/**
 * Contains all subsystems of the robot
 */
public class Subsystems
{
    private static Subsystems instance;

    public BackHatchCam backHatchCam;
    public DriveTrain driveTrain;
    public Grabber grabber;
    public Lifter lifter;
    public MulticolorSensor multicolorSensor;
    public DualCameras dualCameras;
    public Articulator articulator;

    private Subsystems()
    {
        System.out.println("Subsystems : Initialization Started");

        if (BackHatchCam.isEnabled())
            backHatchCam = new BackHatchCam();

        if (DriveTrain.isEnabled())
            driveTrain = new DriveTrain();

        if (Grabber.isEnabled())
            grabber = new Grabber();

        if (Lifter.isEnabled())
            lifter = new Lifter();

        if (MulticolorSensor.isEnabled())
            multicolorSensor = new MulticolorSensor();
<<<<<<< HEAD

        if (DualCameras.isEnabled())
            dualCameras = new DualCameras();

        if (Articulator.isEnabled())
            articulator = new Articulator();

=======
            
>>>>>>> 1c2b4843b25b8ef1dd1a0cb200631575c4880199
        System.out.println("Subsystems : Initialization Finished");
    }

    public static void init()
    {
        if (instance == null)
            instance = new Subsystems();
    }

    public static Subsystems getInstance()
    {
        return instance;
    }
}