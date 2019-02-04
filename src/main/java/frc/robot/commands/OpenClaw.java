package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class OpenClaw extends Command
{
    public OpenClaw()
    {
        super(Robot.claw);
    }

    @Override
    protected void execute()
    {
        Robot.claw.open();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}