package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ReverseDriveTrain extends InstantCommand
{
    public ReverseDriveTrain()
    {
        super(Robot.driveTrain);
    }

    @Override
    protected void execute()
    {
        Robot.driveTrain.reverseMotor();
    }
}