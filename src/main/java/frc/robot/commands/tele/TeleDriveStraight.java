package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.hardware.CustomXboxController;

public class TeleDriveStraight extends PIDCommand
{
    protected final DriveTrain driveTrain;
    protected final CustomXboxController xboxController;

    public TeleDriveStraight(DriveTrain driveTrain, CustomXboxController xboxController)
    {
        super(1, 1, 1, driveTrain);
        this.driveTrain = driveTrain;
        this.xboxController = xboxController;
    }

    @Override
    protected void initialize()
    {
        setSetpoint(driveTrain.getAngle());
    }

    @Override
    protected double returnPIDInput()
    {
        return driveTrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        double speed = xboxController.getY(Hand.kLeft);
        // Robot.driveTrain.tankDrive(speed + output, speed - output);
        driveTrain.tankDrive(speed, speed);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        getPIDController().reset();
    }
}