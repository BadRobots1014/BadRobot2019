package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Grabber;

public class RotateGrabberCW extends Command
{
    protected final Grabber grabber;

    public RotateGrabberCW(Grabber grabber)
    {
        super(grabber);
        this.grabber = grabber;
    }

    @Override
    protected void initialize()
    {
        grabber.rotateCW();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        grabber.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}