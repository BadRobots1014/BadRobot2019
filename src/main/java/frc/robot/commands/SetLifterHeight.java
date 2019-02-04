package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.utils.MiniPID;
import frc.robot.Robot;
import frc.robot.utils.MathUtils;

public class SetLifterHeight extends Command
{
    private MiniPID pid;
    private double requestedHeight;

    public SetLifterHeight(double height)
    {
        super(Robot.lifter);
        pid = new MiniPID(1, 1, 1);
        pid.setSetpoint(height);
        requestedHeight = height;
    }

    @Override
    protected void execute()
    {
        Robot.lifter.setSpeed(pid.getOutput(Robot.lifter.getHeight()));
    }

    @Override
    protected boolean isFinished()
    {
        return MathUtils.isWithinThreshold(Robot.lifter.getHeight(), requestedHeight, 0.01);
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