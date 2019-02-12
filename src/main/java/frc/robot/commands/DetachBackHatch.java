package frc.robot.commands;

import frc.robot.subsystems.BackHatchCam;

public class DetachBackHatch extends RotateBackHatchCamXTimes
{
    public DetachBackHatch(BackHatchCam backHatchCam)
    {
        super(backHatchCam, 5);
    }
}