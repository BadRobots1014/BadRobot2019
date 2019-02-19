package frc.robot;

import frc.robot.commands.tele.TeleDriveStraight;
import frc.robot.commands.tele.TeleTurnInPlace;
import frc.robot.commands.ReverseDriveTrain;
import frc.robot.commands.RotateGrabberCCW;
import frc.robot.commands.RotateGrabberCW;
import frc.robot.utils.hardware.CustomJoystick;
import frc.robot.utils.hardware.CustomXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Controls
{
  private static Controls instance;

  public final CustomXboxController xboxController;
  public final CustomJoystick joystick;

  private Controls()
  {
    xboxController = new CustomXboxController(0);
    xboxController.xButton.whileHeld(new TeleDriveStraight());
    xboxController.yButton.whileHeld(new TeleTurnInPlace());
    xboxController.aButton.whenPressed(new ReverseDriveTrain());

    joystick = new CustomJoystick(1);
    joystick.frontLeftButton.whileHeld(new RotateGrabberCW());
    joystick.backLeftButton.whileHeld(new RotateGrabberCCW());
  }

  public static void init()
  {
    if (instance == null)
      instance = new Controls();
  }

  public static Controls getInstance()
  {
    return instance;
  }
}
