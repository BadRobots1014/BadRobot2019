package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.CustomXboxController;

public class TeleTurnInPlace extends Command
{
    protected final DriveTrain driveTrain;
    protected final CustomXboxController xboxController;

    public TeleTurnInPlace(DriveTrain driveTrain, CustomXboxController xboxController)
    {
        super(driveTrain);
        this.driveTrain = driveTrain;
        this.xboxController = xboxController;
    }

    @Override
    protected void execute()
    {
        double speed = xboxController.getX(Hand.kLeft);
        driveTrain.tankDrive(speed, -speed);
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