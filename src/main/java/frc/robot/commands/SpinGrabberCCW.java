package frc.robot.commands;

public class SpinGrabberCCW extends SpinGrabberCW
{
    @Override
    protected void initialize()
    {
        grabber.spinCCW();
    }
}