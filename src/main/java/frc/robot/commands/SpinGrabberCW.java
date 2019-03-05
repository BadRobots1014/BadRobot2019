package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.Grabber;

public class SpinGrabberCW extends Command
{
    protected final Grabber grabber;

    public SpinGrabberCW()
    {
        super(Subsystems.getInstance().grabber);
        this.grabber = Subsystems.getInstance().grabber;
    }

    @Override
    protected void initialize()
    {
        grabber.spinCW();
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