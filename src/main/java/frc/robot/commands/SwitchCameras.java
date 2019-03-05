package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Subsystems;
import frc.robot.subsystems.DualCameras;

public class SwitchCameras extends InstantCommand
{
    protected DualCameras dualCameras;

    public SwitchCameras()
    {
        super(Subsystems.getInstance().dualCameras);
    }

    @Override
    protected void initialize()
    {
        dualCameras = Subsystems.getInstance().dualCameras;
    }

    @Override
    protected void execute()
    {
        dualCameras.toggle();
    }
}