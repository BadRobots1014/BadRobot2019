package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class TurnInPlace extends PIDCommand
{
    public TurnInPlace(double delta)
    {
        super(1, 1, 1, Robot.driveTrain);
        setSetpoint(Robot.driveTrain.getAngle() + delta);
        getPIDController().setPercentTolerance(0.05);
    }

    @Override
    protected double returnPIDInput()
    {
        return Robot.driveTrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        // TODO may need to flip signs
        Robot.driveTrain.tankDrive(output, -output);
    }

    @Override
    protected boolean isFinished()
    {
        return getPIDController().onTarget();
    }
}