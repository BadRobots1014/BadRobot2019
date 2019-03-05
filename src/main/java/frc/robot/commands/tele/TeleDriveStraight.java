package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.hardware.CustomXboxController;

public class TeleDriveStraight extends PIDCommand
{
    protected DriveTrain driveTrain;
    protected CustomXboxController xboxController;

    public TeleDriveStraight()
    {
        super(1, 1, 1, Subsystems.getInstance().driveTrain);
    }

    @Override
    protected void initialize()
    {
        this.driveTrain = Subsystems.getInstance().driveTrain;
        this.xboxController = Controls.getInstance().xboxController;
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
        double speed = Controls.getInstance().getLeftY();
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