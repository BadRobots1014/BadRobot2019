package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.hardware.CustomXboxController;

public class TeleDrive extends Command
{
    protected DriveTrain driveTrain;
    protected CustomXboxController xboxController;

    public TeleDrive()
    {
        super(Subsystems.getInstance().driveTrain);
    }

    @Override
    protected void initialize()
    {
        this.driveTrain = Subsystems.getInstance().driveTrain;
        this.xboxController = Controls.getInstance().mainController;
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