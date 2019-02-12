package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.CustomXboxController;

public class TeleDrive extends Command
{
    protected final DriveTrain driveTrain;
    protected final CustomXboxController xboxController;

    public TeleDrive(DriveTrain driveTrain, CustomXboxController xboxController)
    {
        super(driveTrain);
        this.driveTrain = driveTrain;
        this.xboxController = xboxController;
    }

    @Override
    protected void execute()
    {
        double left = 0, right = 0;
        left = xboxController.getY(Hand.kLeft);
        right = xboxController.getY(Hand.kRight);
        driveTrain.tankDrive(left, right);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        driveTrain.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}