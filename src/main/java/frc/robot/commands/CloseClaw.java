package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CloseClaw extends Command
{
    public CloseClaw()
    {
        super(Robot.claw);
    }

    @Override
    protected void execute()
    {
        Robot.claw.close();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}