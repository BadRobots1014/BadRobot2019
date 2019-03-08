package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.Articulator;

public class Articulate extends Command
{
    private Articulator articulator;
    private double speed;

    public Articulate(double speed)
    {
        super(Subsystems.getInstance().articulator);
        this.speed = speed;
    }

    @Override
    protected void initialize()
    {
        articulator = Subsystems.getInstance().articulator;
    }

    @Override
    protected void execute()
    {
        articulator.rotate(speed);
    }

    @Override
    protected void end()
    {
        articulator.stopMotor();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
