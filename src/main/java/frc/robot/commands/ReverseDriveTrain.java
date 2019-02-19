package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;

public class ReverseDriveTrain extends InstantCommand
{
    protected final DriveTrain driveTrain;

    public ReverseDriveTrain()
    {
        // TODO super(Subsystems.getInstance().driveTrain);
        this.driveTrain = Subsystems.getInstance().driveTrain;
    }

    @Override
    protected void execute()
    {
        driveTrain.toggleReversed();
    }
}