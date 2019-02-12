package frc.robot.commands;

import frc.robot.subsystems.Grabber;

public class RotateGrabberCCW extends RotateGrabberCW
{
    public RotateGrabberCCW(Grabber grabber)
    {
        super(grabber);
    }

    @Override
    protected void initialize()
    {
        grabber.rotateCCW();
    }
}