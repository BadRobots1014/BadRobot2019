package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class SetLifterHeight extends PIDCommand
{
    public SetLifterHeight(double height)
    {
        super(1, 1, 1, Robot.lifter);
        setSetpoint(height);
        getPIDController().setPercentTolerance(5);
    }

    @Override
    protected double returnPIDInput()
    {
        return Robot.lifter.getHeight();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        Robot.lifter.setSpeed(output);
    }

    @Override
    protected boolean isFinished()
    {
        return getPIDController().onTarget();
    }

    @Override
    protected void end()
    {
        Robot.lifter.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}