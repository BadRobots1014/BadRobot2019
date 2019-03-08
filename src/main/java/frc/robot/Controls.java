package frc.robot;

import frc.robot.commands.tele.TeleDriveStraight;
import frc.robot.commands.tele.TeleDriveStraightBackward;
import frc.robot.commands.tele.TeleLift;
import frc.robot.commands.tele.TeleTurnInPlace;
import frc.robot.subsystems.Articulator;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DualCameras;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Lifter;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.Articulate;
import frc.robot.commands.DetachBackHatch;
import frc.robot.commands.ReverseDriveTrain;
import frc.robot.commands.SetLifterHeight;
import frc.robot.commands.SetLifterHeightRelative;
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

  public CustomXboxController mainController;
  // public CustomXboxController auxController;
  public final CustomJoystick joystick;

  // private final CustomXboxController mainController;
  // private final CustomXboxController frontAuxController;
  // private final CustomXboxController backAuxController;

  // sticks = drive
  // start = reverse drive train
  // back = hold to run cam
  // x = drive straight
  // a = lifter offset
  // pad = lifter pos
  // y = artic forward hold
  // b = artic back hold
  // r bumper = gather
  // l bumper = shoot
  // r trigger = lifter up
  // l trigger = lifter down

  private Controls()
  {
    System.out.println("Controls : Initialization Started");

    mainController = new CustomXboxController(0);
    // frontAuxController = new CustomXboxController(1);
    // backAuxController = new CustomXboxController(1);

    // auxController = frontAuxController;

    mainController.startButton.whenPressed(new SwitchControls());

    if (DriveTrain.isEnabled())
    {
      mainController.rightTrigger.whileHeld(new TeleDriveStraight());
      mainController.leftTrigger.whileHeld(new TeleDriveStraightBackward());
      mainController.aButton.whenPressed(new CommandGroup()
      {
        {
          if (DualCameras.isEnabled())
            addParallel(new SwitchCameras());
          addParallel(new ReverseDriveTrain());
        }
      });
    }
    // frontAuxController.xButton.whileHeld(new TeleDriveStraight());
    // frontAuxController.yButton.whileHeld(new TeleTurnInPlace());
    // frontAuxController.aButton.whenPressed(new CommandGroup()
    // {
    // {
    // addSequential(new InstantCommand()
    // {
    // @Override
    // protected void execute()
    // {
    // mainController = backAuxController;
    // }
    // });
    // if (DualCameras.isEnabled())
    // addParallel(new SwitchCameras());
    // addParallel(new ReverseDriveTrain());
    // }
    // });

    // backAuxController.xButton.whileHeld(new TeleDriveStraight());
    // backAuxController.yButton.whileHeld(new TeleTurnInPlace());
    // backAuxController.aButton.whenPressed(new CommandGroup()
    // {
    // {
    // addSequential(new InstantCommand()
    // {
    // @Override
    // protected void execute()
    // {
    // mainController = frontAuxController;
    // }
    // });

    // if (DualCameras.isEnabled())
    // addParallel(new SwitchCameras());
    // addParallel(new ReverseDriveTrain());
    // }
    // });
    // }

    if (DualCameras.isEnabled())
    {
      mainController.backButton.whenPressed(new SwitchCameras());
      // frontAuxController.backButton.whenPressed(new SwitchCameras());
      // backAuxController.backButton.whenPressed(new SwitchCameras());
    }

    if (Grabber.isEnabled())
    {
      mainController.bumperLeftButton.whileHeld(new DetachBackHatch());
      mainController.bumperRightButton.whileHeld(new DetachBackHatch());

      // frontAuxController.bumperLeftButton.whileHeld(new SpinGrabberCCW());
      // frontAuxController.bumperRightButton.whileHeld(new SpinGrabberCW());
    }

    // if (Lifter.isEnabled())
    // {
    // mainController.dpadUp.whenPressed(new
    // SetLifterHeight(LifterLevel.ROCKET_HIGH));
    // mainController.dpadRight.whenPressed(new
    // SetLifterHeight(LifterLevel.ROCKET_MED));
    // mainController.dpadLeft.whenPressed(new
    // SetLifterHeight(LifterLevel.ROCKET_LOW));
    // mainController.dpadDown.whenPressed(new SetLifterHeight(LifterLevel.GROUND));

    // frontAuxController.dpadUp.whenPressed(new
    // SetLifterHeight(LifterLevel.ROCKET_HIGH));
    // frontAuxController.dpadRight.whenPressed(new
    // SetLifterHeight(LifterLevel.ROCKET_MED));
    // frontAuxController.dpadLeft.whenPressed(new
    // SetLifterHeight(LifterLevel.ROCKET_LOW));
    // frontAuxController.dpadDown.whenPressed(new
    // SetLifterHeight(LifterLevel.GROUND));
    // }

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

      joystick.povMoved.whileActive(new TeleLift());

      joystick.backLeftButton.whenPressed(new SetLifterHeight(0.1));

      joystick.frontRightButton.whenPressed(new InstantCommand(){

        {
          requires(Subsystems.getInstance().lifter);
        }

        @Override
        protected boolean isFinished()
        {
          return false;
        }
      });
      // joystick.backRightButton.whenPressed(new SetLifterHeightRelative(-4));

      joystick.button7.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_HATCH_HIGH));
      joystick.button9.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_HATCH_MED));
      joystick.button11.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_HATCH_LOW));

      joystick.button8.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_BALL_HIGH));
      joystick.button10.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_BALL_MED));
      joystick.button12.whenPressed(new SetLifterHeight(LifterLevel.ROCKET_BALL_LOW));
    }

    if (Grabber.isEnabled())
    {
      joystick.trigger.whileHeld(new SpinGrabberCW());
      joystick.sideThumbButton.whileHeld(new SpinGrabberCCW());
    }

    if (Articulator.isEnabled())
    {
      joystick.axisYForward.whileActive(new Articulate(-0.35));
      joystick.axisYBackward.whileActive(new Articulate(0.35));

      // joystick.povUp.whileActive(new SetLifterHeight(2));
      // joystick.povDown.whileActive(new SetLifterHeight(-2));
    }

    System.out.println("Controls : Initialization Finished");
  }

  // public double getJoystickY()
  // {
  // return joystick.getY();
  // }

  public double getLeftY()
  {
    return mainController.getY(Hand.kLeft);
  }

  public double getRightY()
  {
    return mainController.getY(Hand.kRight);
  }

  public double getLeftX()
  {
    return mainController.getX(Hand.kLeft);
  }

  public double getRightX()
  {
    return mainController.getX(Hand.kRight);
  }

  public void switchContollers()
  {
    // if (mainController == mainController)
    // mainController = frontAuxController;
    // else
    // mainController = mainController;

    // if(auxController == )
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
