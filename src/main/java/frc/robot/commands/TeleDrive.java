package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TeleDrive extends Command
{
    public TeleDrive()
    {
        super(Robot.driveTrain);
    }

    @Override
    protected void execute()
    {
        double left = 0, right = 0;
        left = OI.xboxController.getY(Hand.kLeft);
        right = OI.xboxController.getY(Hand.kRight);
        Robot.driveTrain.tankDrive(left, right);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        Robot.driveTrain.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}