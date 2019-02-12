package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.BackHatchCam;

public class DetachBackHatch extends TimedCommand
{
    public DetachBackHatch(BackHatchCam backHatchCam)
    {
        super(5, backHatchCam);
    }
}