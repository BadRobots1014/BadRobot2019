package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.hardware.CustomXboxController;

public class TeleDriveStraight extends PIDCommand
{
    protected final DriveTrain driveTrain;
    protected final CustomXboxController xboxController;

    public TeleDriveStraight()
    {
        super(1, 1, 1, Subsystems.getInstance().driveTrain);
        this.driveTrain = Subsystems.getInstance().driveTrain;
        this.xboxController = Controls.getInstance().xboxController;
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