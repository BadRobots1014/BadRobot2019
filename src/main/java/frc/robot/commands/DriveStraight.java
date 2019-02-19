package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.SimplePIDSource;

public class DriveStraight extends Command
{
    protected final DriveTrain driveTrain;
    private final PIDController angularContoller;
    private final PIDController linearContoller;
    private double angularOutput;
    private double linearOutput;

    public DriveStraight(double distance_m)
    {
        super(Subsystems.getInstance().driveTrain);
        this.driveTrain = Subsystems.getInstance().driveTrain;

        angularContoller = new PIDController(1, 1, 1, new SimplePIDSource(this::getCurrAngle), this::setAngularOutput);
        angularContoller.setSetpoint(getCurrAngle());

        linearContoller = new PIDController(1, 1, 1, new SimplePIDSource(this::getCurrDisplacement),
                this::setLinearOutput);
        linearContoller.setSetpoint(getCurrDisplacement() + distance_m);
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
        driveTrain.tankDrive(linearOutput + angularOutput, linearOutput - angularOutput);
    }

    private double getCurrAngle()
    {
        return driveTrain.getAngle();
    }

    private double getCurrDisplacement()
    {
        return driveTrain.getDisplacement();
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