package frc.robot.commands;

import frc.robot.subsystems.BackHatchCam;

public class ResetCamPosition extends RotateGrabberCW
{
    public ResetCamPosition(BackHatchCam backHatchCam)
    {
        super(backHatchCam);
    }

    @Override
    protected boolean isFinished()
    {
        return backHatchCam.isCamInBackPosition();
    }
}