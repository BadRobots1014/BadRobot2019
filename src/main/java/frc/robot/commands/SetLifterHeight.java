package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Subsystems;
import frc.robot.subsystems.Lifter;

public class SetLifterHeight extends PIDCommand
{
    protected final Lifter lifter;
    protected double height;
    protected long startTime;

    public SetLifterHeight(double height)
    {
        super(0.1, 0.15, 0.15, Subsystems.getInstance().lifter);
        this.lifter = Subsystems.getInstance().lifter;
        this.height = -height;
        // getPIDController().setPercentTolerance(5); // TODO tweak tolerance
        setSetpoint(this.height);
    }

    @Override
    protected void initialize()
    {
        this.startTime = System.currentTimeMillis();
        getPIDController().reset();
        getPIDController().enable();
    }

    @Override
    protected double returnPIDInput()
    {
        return lifter.getEncoderValue();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        System.err.println("E: " + lifter.getEncoderValue() + "\t\t|\t\tPID: " + output);
        lifter.setSpeed(output * 0.65);
    }

    @Override
    protected void end()
    {
        lifter.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }

    @Override
    protected boolean isFinished()
    {
        if (Math.abs(getSetpoint() - lifter.getEncoderValue()) <= getSetpoint() * 0.2)
        {
            System.err.println("Time: " + ((System.currentTimeMillis() - startTime) / 1000.0));
            return true;
        }

        return false;
    }
}