/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import badlog.lib.BadLog;
import badlog.lib.DataInferMode;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lifter;
import frc.robot.utils.CameraProcessingThread;
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
  public final DriveTrain driveTrain = new DriveTrain(this);
  public final Lifter lifter = new Lifter(this);
  public final BackHatchCam backHatchCam = new BackHatchCam(this);
  public final Grabber grabber = new Grabber(this);
  public final OI oi = new OI(this);
  private final CameraProcessingThread cameraProcessingThread = new CameraProcessingThread();
  // public final LightDriveCAN lightDriveCAN = new LightDriveCAN(10);

  @Override
  public void robotInit()
  {
    cameraProcessingThread.start();

    BadLog log = BadLog.init("/home/lvuser/test.bag");
    BadLog.createValue("Example Value", System.getProperty("os.version"));
    BadLog.createTopic("Example Topic", "Bytes", () -> (double) Runtime.getRuntime().freeMemory());
    BadLog.createTopic("Topic with attributes", BadLog.UNITLESS, () -> 3.2, "attr1", "attr2");
    BadLog.createTopicSubscriber("Subscribed topic", "s", DataInferMode.DEFAULT);
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

  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit()
  {
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
