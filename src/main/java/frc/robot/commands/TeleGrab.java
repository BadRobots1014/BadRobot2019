package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TeleGrab extends Command
{
    public TeleGrab()
    {
        super(Robot.grabber);
    }

    @Override
    protected void execute()
    {
        double rightTrigger = OI.xboxController.getTriggerAxis(Hand.kRight);
        rightTrigger *= rightTrigger;
        double leftTrigger = OI.xboxController.getTriggerAxis(Hand.kLeft);
        leftTrigger *= leftTrigger;

        if (rightTrigger > 0.05)
            Robot.grabber.rotate(rightTrigger);
        else if (leftTrigger > 0.05)
            Robot.grabber.rotate(-leftTrigger);
        else
            Robot.grabber.stopMotor();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        Robot.grabber.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}