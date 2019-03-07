package frc.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot
{
  private BadLog logger;

  public Robot()
  {
    super(0.03);
  }

  @Override
  public void robotInit()
  {
    String timestamp = new SimpleDateFormat("dd-MM-yyyy-HH-mm").format(new Date());
    logger = BadLog.init("/home/lvuser/" + timestamp + ".badbag");

    BadLog.createValue("Match Type", DriverStation.getInstance().getMatchType().toString());
    BadLog.createValue("Match Number", "" + DriverStation.getInstance().getMatchNumber());
    BadLog.createTopic("Match Time", "s", DriverStation.getInstance()::getMatchTime);

    BadLog.createValue("OS Version", System.getProperty("os.version"));
    BadLog.createTopic("Free Memory", "Bytes", () -> (double) Runtime.getRuntime().freeMemory());

    Subsystems.init();
    Controls.init();

    logger.finishInitialization();
  }

  @Override
  public void robotPeriodic()
  {
    // System.err.println(Subsystems.getInstance().lifter.getEncoderValue());
  }

  @Override
  public void disabledInit()
  {
  }

  @Override
  public void disabledPeriodic()
  {
    // Scheduler.getInstance().run();
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

    if (!DriverStation.getInstance().isDisabled())
    {
      logger.updateTopics();
      logger.log();
    }
  }

  @Override
  public void testPeriodic()
  {
  }
}
