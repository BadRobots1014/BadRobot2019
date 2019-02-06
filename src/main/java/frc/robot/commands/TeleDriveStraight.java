package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.OI;
import frc.robot.Robot;

public class TeleDriveStraight extends PIDCommand
{
    public TeleDriveStraight()
    {
        super(1, 1, 1, Robot.driveTrain);
    }

    @Override
    protected void initialize()
    {
        setSetpoint(Robot.driveTrain.getAngle());
    }

    @Override
    protected double returnPIDInput()
    {
        return Robot.driveTrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        double speed = OI.xboxController.getY(Hand.kLeft);
        // Robot.driveTrain.tankDrive(speed + output, speed - output);
        Robot.driveTrain.tankDrive(speed, speed);
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