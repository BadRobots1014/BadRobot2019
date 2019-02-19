/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.UUID;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.MulticolorSensor;
import frc.robot.subsystems.BackHatchCam;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
  public DriveTrain driveTrain;
  public Lifter lifter;
  public BackHatchCam backHatchCam;
  public Grabber grabber;
  public OI oi;
  // public MulticolorSensor multicolorSensor;
  // TODO private final CameraProcessingThread cameraProcessingThread = new
  // CameraProcessingThread();
  // public final LightDriveCAN lightDriveCAN = new LightDriveCAN(10);

  @Override
  public void robotInit()
  {
    BadLog log = BadLog.init("/home/lvuser/" + UUID.randomUUID() + ".badbag");

    // TODO cameraProcessingThread.start();
    driveTrain = new DriveTrain(this);
    lifter = new Lifter(this);
    backHatchCam = new BackHatchCam(this);
    // grabber = new Grabber(this);
    oi = new OI(this);
    // multicolorSensor = new MulticolorSensor();

    BadLog.createValue("OS Version", System.getProperty("os.version"));
    BadLog.createTopic("Free Memory", "Bytes", () -> (double) Runtime.getRuntime().freeMemory());
    log.finishInitialization();

    // lightDriveCAN.SetColor(1, Color.BLUE);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic()
  {
    // System.out.println(Arrays.toString(colorSensor.getRGB()));
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit()
  {
    // TODO cameraProcessingThread.stop();
  }

  @Override
  public void disabledPeriodic()
  {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit()
  {
    teleopInit();
  }

  @Override
  public void autonomousPeriodic()
  {
    teleopPeriodic();
  }

  @Override
  public void teleopInit()
  {

  }

  @Override
  public void teleopPeriodic()
  {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic()
  {
  }
}
