package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Claw;

public class CloseClaw extends Command
{
    protected final Claw claw;

    public CloseClaw(Claw claw)
    {
        super(claw);
        this.claw = claw;
    }

    @Override
    protected void execute()
    {
        claw.close();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}