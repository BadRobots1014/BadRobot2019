package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Claw;

public class OpenClaw extends Command
{
    protected final Claw claw;

    public OpenClaw(Claw claw)
    {
        super(claw);
        this.claw = claw;
    }

    @Override
    protected void execute()
    {
        claw.open();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}