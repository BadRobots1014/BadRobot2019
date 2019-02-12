package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BackHatchCam;

public class ResetCamPosition extends Command
{
    protected final BackHatchCam backHatchCam;

    public ResetCamPosition(BackHatchCam backHatchCam)
    {
        super(backHatchCam);
        this.backHatchCam = backHatchCam;
    }

    @Override
    protected boolean isFinished()
    {
        return backHatchCam.isCamInBackPosition();
    }
}