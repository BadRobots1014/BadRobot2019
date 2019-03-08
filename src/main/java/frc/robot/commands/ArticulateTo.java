package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.Articulator;

public class ArticulateTo extends Command
{
    private int limit;
    private Articulator articulator;

    public ArticulateTo(int limit)
    {
        super(Subsystems.getInstance().articulator);
        this.limit = limit;
    }

    @Override
    protected void initialize()
    {
        this.articulator = Subsystems.getInstance().articulator;
    }

    @Override
    protected void end()
    {
        articulator.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }

    @Override
    protected boolean isFinished()
    {
        return articulator.getEncoderValue() == limit;
    }
}