package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
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
        super(0.05, 0.001, 0.025, Subsystems.getInstance().driveTrain);
    }

    @Override
    protected void initialize()
    {
        this.driveTrain = Subsystems.getInstance().driveTrain;
        this.xboxController = Controls.getInstance().mainController;
        setSetpoint(driveTrain.getAngle());
        System.err.println("START ANGLE: " + driveTrain.getAngle());
    }

    @Override
    protected double returnPIDInput()
    {
        return driveTrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        double speed = Controls.getInstance().mainController.getTriggerAxis(Hand.kRight);
        // TODO IMPLEMENT
        System.err.println("Angle: " + driveTrain.getAngle() + "\t\t\tPID: " + output);
        driveTrain.tankDrive(speed - output, speed + output);
        // driveTrain.tankDrive(speed, speed);
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