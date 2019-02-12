package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Lifter;

public class SetLifterHeight extends PIDCommand
{
    protected final Lifter lifter;

    public SetLifterHeight(Lifter lifter, double height)
    {
        super(1, 1, 1, lifter);
        this.lifter = lifter;
        setSetpoint(height);
        getPIDController().setPercentTolerance(5); // TODO tweak tolerance
    }

    @Override
    protected double returnPIDInput()
    {
        return lifter.getHeight();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        lifter.setSpeed(output);
    }

    @Override
    protected boolean isFinished()
    {
        return getPIDController().onTarget();
    }
}