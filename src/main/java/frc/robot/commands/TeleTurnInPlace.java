package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TeleTurnInPlace extends Command
{
    public TeleTurnInPlace()
    {
        super(Robot.driveTrain);
    }

    @Override
    protected void execute()
    {
        double speed = OI.xboxController.getX(Hand.kLeft);
        Robot.driveTrain.tankDrive(speed, -speed);
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