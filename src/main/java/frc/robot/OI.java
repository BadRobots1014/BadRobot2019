package frc.robot;

import frc.robot.commands.tele.TeleDriveStraight;
import frc.robot.commands.tele.TeleTurnInPlace;
import frc.robot.commands.ReverseDriveTrain;
import frc.robot.commands.RotateGrabberCCW;
import frc.robot.commands.RotateGrabberCW;
import frc.robot.utils.CustomJoystick;
import frc.robot.utils.CustomXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
  public final CustomXboxController xboxController;
  public final CustomJoystick joystick;

  public OI(Robot robot)
  {
    xboxController = new CustomXboxController(0);
    xboxController.xButton.whileHeld(new TeleDriveStraight(robot.driveTrain, this.xboxController));
    xboxController.yButton.whileHeld(new TeleTurnInPlace(robot.driveTrain, this.xboxController));
    xboxController.aButton.whenPressed(new ReverseDriveTrain(robot.driveTrain));

    joystick = new CustomJoystick(1);
    joystick.frontLeftButton.whileHeld(new RotateGrabberCW(robot.grabber));
    joystick.backLeftButton.whileHeld(new RotateGrabberCCW(robot.grabber));
  }
}
