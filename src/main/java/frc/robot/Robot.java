package frc.robot;

import java.util.UUID;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot
{
  @Override
  public void robotInit()
  {
    BadLog log = BadLog.init("/home/lvuser/" + UUID.randomUUID() + ".badbag");

    Subsystems.init();
    Controls.init();

    BadLog.createValue("OS Version", System.getProperty("os.version"));
    BadLog.createTopic("Free Memory", "Bytes", () -> (double) Runtime.getRuntime().freeMemory());
    log.finishInitialization();
  }

  @Override
  public void robotPeriodic()
  {
  }

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
