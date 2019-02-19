package frc.robot;

import java.util.UUID;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.BackHatchCam;

public class Robot extends TimedRobot
{
  public DriveTrain driveTrain;
  public Lifter lifter;
  public BackHatchCam backHatchCam;
  public Grabber grabber;
  public OI oi;

  @Override
  public void robotInit()
  {
    BadLog log = BadLog.init("/home/lvuser/" + UUID.randomUUID() + ".badbag");

    driveTrain = new DriveTrain(this);
    lifter = new Lifter(this);
    backHatchCam = new BackHatchCam(this);
    grabber = new Grabber(this);
    oi = new OI(this);

    BadLog.createValue("OS Version", System.getProperty("os.version"));
    BadLog.createTopic("Free Memory", "Bytes", () -> (double) Runtime.getRuntime().freeMemory());
    log.finishInitialization();
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
