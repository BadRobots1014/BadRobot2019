package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.Articulator;
import frc.robot.utils.hardware.CustomJoystick;
import frc.robot.utils.hardware.CustomXboxController;

public class TeleArticulate extends Command
{
    protected Articulator articulator;
    protected CustomXboxController xboxController;

    public TeleArticulate()
    {
        super(Subsystems.getInstance().articulator);
    }

    @Override
    protected void initialize()
    {
        this.articulator = Subsystems.getInstance().articulator;
        this.xboxController = Controls.getInstance().xboxController;
    }

    @Override
    protected void execute()
    {
        // TODO implement
        double speed = xboxController.getY(Hand.kLeft);

        if (Math.abs(speed) > 0.1)
            articulator.rotate(speed * .25);
        else
            articulator.stopMotor();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        articulator.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}