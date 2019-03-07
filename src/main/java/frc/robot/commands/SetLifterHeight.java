package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Subsystems;
import frc.robot.subsystems.Lifter;

public class SetLifterHeight extends PIDCommand
{
    protected final Lifter lifter;
    protected final double height;
    protected long startTime;

    public SetLifterHeight(double height)
    {
        super(0.1, 0.15, 0.15, Subsystems.getInstance().lifter);
        this.lifter = Subsystems.getInstance().lifter;
        this.height = height;
        getPIDController().setPercentTolerance(5); // TODO tweak tolerance
        setSetpoint(height);
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
        return lifter.getHeight();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        System.err.println("E: " + lifter.getEncoderValue() + "\t\t|\t\tPID: " + output + "\t\t|\t\tHeight: "
                + lifter.getHeight());
        lifter.setSpeed(output * -0.25);
    }

    @Override
    protected void end()
    {
        lifter.stopMotor();
    }

    @Override
    protected boolean isFinished()
    {
        if (Math.abs(getSetpoint() - lifter.getHeight()) <= getSetpoint() * 0.1)
        {
            System.err.println("Time: " + ((System.currentTimeMillis() - startTime) / 1000.0));
            return true;
        }

        return false;
    }
}