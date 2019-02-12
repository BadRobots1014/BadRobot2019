package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ReverseDriveTrain extends InstantCommand
{
    protected final DriveTrain driveTrain;

    public ReverseDriveTrain(DriveTrain driveTrain)
    {
        super(driveTrain);
        this.driveTrain = driveTrain;
    }

    @Override
    protected void execute()
    {
        driveTrain.reverseMotor();
    }
}