package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.BackHatchCam;

public class ResetCamPosition extends Command
{
    protected final BackHatchCam backHatchCam;

    public ResetCamPosition()
    {
        super(Subsystems.getInstance().backHatchCam);
        this.backHatchCam = Subsystems.getInstance().backHatchCam;
    }

    @Override
    protected boolean isFinished()
    {
        return backHatchCam.isCamInBackPosition();
    }
}