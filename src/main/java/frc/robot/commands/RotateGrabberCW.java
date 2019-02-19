package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.Grabber;

public class RotateGrabberCW extends Command
{
    protected final Grabber grabber;

    public RotateGrabberCW()
    {
        super(Subsystems.getInstance().grabber);
        this.grabber = Subsystems.getInstance().grabber;
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