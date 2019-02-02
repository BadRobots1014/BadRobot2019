package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetLifterHeight extends Command
{
    private static final double MAX_SPEED = 1.0;

    private double requestedHeight;

    public SetLifterHeight(double height)
    {
        super(Robot.lifter);
        this.requestedHeight = height;
    }

    @Override
    protected void execute()
    {
        double speed = MAX_SPEED * smoothingFactor(Robot.lifter.getHeight(), requestedHeight);
        Robot.lifter.setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        return isWithinThreshold(Robot.lifter.getHeight(), requestedHeight, 0.01);
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

    private boolean isWithinThreshold(double value, double target, double maxDeviation)
    {
        return Math.abs(target - value) <= maxDeviation;
    }

    private double smoothingFactor(double value, double target)
    {
        return Math.tanh(10.0 * (target - value));
    }
}