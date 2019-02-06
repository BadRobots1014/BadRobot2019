package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.SimplePIDSource;

public class DriveStraight extends Command
{
    private final PIDController angularContoller;
    private final PIDController linearContoller;
    private double angularOutput;
    private double linearOutput;

    public DriveStraight()
    {
        super(Robot.driveTrain);
        angularContoller = new PIDController(1, 1, 1, new SimplePIDSource(this::getCurrAngle), this::setAngularOutput);
        linearContoller = new PIDController(1, 1, 1, new SimplePIDSource(this::getCurrDisplacement),
                this::setLinearOutput);
    }

    @Override
    protected void initialize()
    {
        angularContoller.enable();
        linearContoller.enable();
    }

    @Override
    protected void execute()
    {
        Robot.driveTrain.tankDrive(linearOutput + angularOutput, linearOutput - angularOutput);
    }

    private double getCurrAngle()
    {
        return Robot.driveTrain.getAngle();
    }

    private double getCurrDisplacement()
    {
        return Robot.driveTrain.getDisplacement();
    }

    private void setAngularOutput(double angularOutput)
    {
        this.angularOutput = angularOutput;
    }

    private void setLinearOutput(double linearOutput)
    {
        this.linearOutput = linearOutput;
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        angularContoller.reset();
        linearContoller.reset();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}