package frc.robot.commands;

import frc.robot.subsystems.BackHatchCam;

public class RotateGrabberCCW extends RotateGrabberCW
{
    public RotateGrabberCCW(BackHatchCam backHatchCam)
    {
        super(backHatchCam);
    }

    @Override
    protected void initialize()
    {
        backHatchCam.rotateCCW();
    }
}