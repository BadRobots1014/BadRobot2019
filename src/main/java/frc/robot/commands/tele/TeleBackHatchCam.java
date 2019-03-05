package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.BackHatchCam;
import frc.robot.utils.hardware.CustomXboxController;

public class TeleBackHatchCam extends Command
{
    protected BackHatchCam backHatchCam;
    protected CustomXboxController xboxController;

    public TeleBackHatchCam()
    {
        super(Subsystems.getInstance().backHatchCam);
    }

    @Override
    protected void initialize()
    {
        this.backHatchCam = Subsystems.getInstance().backHatchCam;
        this.xboxController = Controls.getInstance().xboxController;
    }

    @Override
    protected void execute()
    {
        double rightTrigger = xboxController.getTriggerAxis(Hand.kRight);
        double leftTrigger = xboxController.getTriggerAxis(Hand.kLeft);

        if (rightTrigger > 0.05)
            backHatchCam.rotate(rightTrigger);
        else if (leftTrigger > 0.05)
            backHatchCam.rotate(-leftTrigger);
        else
            backHatchCam.stopMotor();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        backHatchCam.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}