package frc.robot;

import frc.robot.subsystems.BackHatchCam;
import frc.robot.subsystems.DriveTrain;
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