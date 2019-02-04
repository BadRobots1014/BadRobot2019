package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TeleLift extends Command
{
    public TeleLift()
    {
        super(Robot.lifter);
    }

    @Override
    protected void execute()
    {
        Robot.lifter.setSpeed(OI.joystick.getY());
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        Robot.lifter.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}