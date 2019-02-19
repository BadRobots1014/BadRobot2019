package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.hardware.CustomXboxController;

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
        double left = xboxController.getY(Hand.kLeft);
        double right = xboxController.getY(Hand.kRight);
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