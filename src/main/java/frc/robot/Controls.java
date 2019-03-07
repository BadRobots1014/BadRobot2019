package frc.robot;

import frc.robot.commands.tele.TeleDriveStraight;
import frc.robot.commands.tele.TeleTurnInPlace;
import frc.robot.subsystems.Articulator;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DualCameras;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Lifter;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.ReverseDriveTrain;
import frc.robot.commands.SetLifterHeight;
import frc.robot.commands.SpinGrabberCCW;
import frc.robot.commands.SpinGrabberCW;
import frc.robot.commands.SwitchCameras;
import frc.robot.commands.SwitchControls;
import frc.robot.utils.hardware.CustomJoystick;
import frc.robot.utils.hardware.CustomXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Controls
{
  private static Controls instance;

  public CustomXboxController xboxController;
  public final CustomJoystick joystick;

  private final CustomXboxController mainController;
  private final CustomXboxController frontAuxController;
  private final CustomXboxController backAuxController;

  private Controls()
  {
    System.out.println("Controls : Initialization Started");

    mainController = new CustomXboxController(0);
    frontAuxController = new CustomXboxController(1);
    backAuxController = new CustomXboxController(1);

    xboxController = mainController;

    mainController.startButton.whenPressed(new SwitchControls());

    if (DriveTrain.isEnabled())
    {
      mainController.xButton.whileHeld(new TeleDriveStraight());
      mainController.yButton.whileHeld(new TeleTurnInPlace());
      mainController.aButton.whenPressed(new CommandGroup()
      {
        {
          if (DualCameras.isEnabled())
            addParallel(new SwitchCameras());
          addParallel(new ReverseDriveTrain());
        }
      });

      frontAuxController.xButton.whileHeld(new TeleDriveStraight());
      frontAuxController.yButton.whileHeld(new TeleTurnInPlace());
      frontAuxController.aButton.whenPressed(new CommandGroup()
      {
        {
          addSequential(new InstantCommand()
          {
            @Override
            protected void execute()
            {
              xboxController = backAuxController;
            }
          });
          if (DualCameras.isEnabled())
            addParallel(new SwitchCameras());
          addParallel(new ReverseDriveTrain());
        }
      });

      backAuxController.xButton.whileHeld(new TeleDriveStraight());
      backAuxController.yButton.whileHeld(new TeleTurnInPlace());
      backAuxController.aButton.whenPressed(new CommandGroup()
      {
        {
          addSequential(new InstantCommand()
          {
            @Override
            protected void execute()
            {
              xboxController = frontAuxController;
            }
          });

          if (DualCameras.isEnabled())
            addParallel(new SwitchCameras());
          addParallel(new ReverseDriveTrain());
        }
      });
    }

    if (DualCameras.isEnabled())
    {
      mainController.backButton.whenPressed(new SwitchCameras());
      frontAuxController.backButton.whenPressed(new SwitchCameras());
      backAuxController.backButton.whenPressed(new SwitchCameras());
    }

    if (Grabber.isEnabled())
    {
      mainController.bumperLeftButton.whileHeld(new SpinGrabberCCW());
      mainController.bumperRightButton.whileHeld(new SpinGrabberCW());

      frontAuxController.bumperLeftButton.whileHeld(new SpinGrabberCCW());
      frontAuxController.bumperRightButton.whileHeld(new SpinGrabberCW());
    }

    if (Lifter.isEnabled())
    {
      mainController.dpadUp.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_HIGH));
      mainController.dpadRight.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_MED));
      mainController.dpadLeft.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_LOW));
      mainController.dpadDown.whenPressed(new SetLifterHeight(LifterLevel.GROUND));

      frontAuxController.dpadUp.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_HIGH));
      frontAuxController.dpadRight.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_MED));
      frontAuxController.dpadLeft.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_LOW));
      frontAuxController.dpadDown.whenPressed(new SetLifterHeight(LifterLevel.GROUND));
    }

    joystick = new CustomJoystick(2);

    if (Lifter.isEnabled())
    {
      joystick.frontLeftButton.whenPressed(new InstantCommand()
      {
        @Override
        protected void execute()
        {
          Subsystems.getInstance().lifter.zeroEncoder();
        }
      });

      joystick.backLeftButton.whenPressed(new SetLifterHeight(0.5));
      joystick.backRightButton.whenPressed(new SetLifterHeight(1));
      joystick.frontRightButton.whenPressed(new SetLifterHeight(1.5));
    }

    // if (Grabber.isEnabled())
    // {
    // joystick.frontLeftButton.whileHeld(new SpinGrabberCW());
    // joystick.backLeftButton.whileHeld(new SpinGrabberCCW());
    // }

    System.out.println("Controls : Initialization Finished");
  }

  // public double getJoystickY()
  // {
  // return joystick.getY();
  // }

  public double getLeftY()
  {
    return xboxController.getY(Hand.kLeft);
  }

  public double getRightY()
  {
    return xboxController.getY(Hand.kRight);
  }

  public double getLeftX()
  {
    return xboxController.getX(Hand.kLeft);
  }

  public double getRightX()
  {
    return xboxController.getX(Hand.kRight);
  }

  public void switchContollers()
  {
    if (xboxController == mainController)
      xboxController = frontAuxController;
    else
      xboxController = mainController;
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
