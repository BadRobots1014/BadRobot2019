package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveStraight extends PIDCommand
{
    private AHRS navx;

    public DriveStraight()
    {
        super(1, 1, 1, Robot.driveTrain);
        navx = new AHRS(SPI.Port.kMXP);
    }

    @Override
    protected void initialize()
    {
        setSetpoint(navx.getAngle());
    }

    @Override
    protected double returnPIDInput()
    {
        return navx.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        double speed = OI.xboxController.getY(Hand.kLeft);
        Robot.driveTrain.tankDrive(speed + output, speed - output);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}