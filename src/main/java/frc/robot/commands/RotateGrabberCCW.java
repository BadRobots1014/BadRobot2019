package frc.robot.commands;

public class RotateGrabberCCW extends RotateGrabberCW
{
    @Override
    protected void initialize()
    {
        grabber.rotateCCW();
    }
}