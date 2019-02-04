package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.utils.MiniPID;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveStraight extends Command
{
    private AHRS navx;
    private MiniPID pid;

    public DriveStraight()
    {
        super(Robot.driveTrain);
        navx = new AHRS(SPI.Port.kMXP);
    }

    @Override
    protected void initialize()
    {
        pid = new MiniPID(1, 1, 1);
        pid.setSetpoint(navx.getAngle());
    }

    @Override
    protected void execute()
    {
        double speed = OI.xboxController.getY(Hand.kLeft);
        double turnComp = pid.getOutput(navx.getAngle());
        Robot.driveTrain.tankDrive(speed + turnComp, speed - turnComp);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}