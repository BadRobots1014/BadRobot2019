package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateGrabberCW extends Command
{
    public RotateGrabberCW()
    {
        super(Robot.grabber);
    }

    @Override
    protected void initialize()
    {
        Robot.grabber.rotateCW();
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