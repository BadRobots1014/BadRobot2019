package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;

public class TurnInPlace extends PIDCommand
{
    protected DriveTrain driveTrain;

    public TurnInPlace(double delta)
    {
        super(1, 1, 1, Subsystems.getInstance().driveTrain);
        this.driveTrain = Subsystems.getInstance().driveTrain;
        setSetpoint(driveTrain.getAngle() + delta);
        getPIDController().setPercentTolerance(0.05);
    }

    @Override
    protected double returnPIDInput()
    {
        return driveTrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        // TODO may need to flip signs
        driveTrain.tankDrive(output, -output);
    }

    @Override
    protected boolean isFinished()
    {
        return getPIDController().onTarget();
    }
}