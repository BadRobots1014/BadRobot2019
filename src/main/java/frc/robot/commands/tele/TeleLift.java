package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.Lifter;
import frc.robot.utils.hardware.CustomJoystick;

public class TeleLift extends Command
{
    protected Lifter lifter;
    protected CustomJoystick joystick;

    public TeleLift()
    {
        super(Subsystems.getInstance().lifter);
    }

    @Override
    protected void initialize()
    {
        this.lifter = Subsystems.getInstance().lifter;
        this.joystick = Controls.getInstance().joystick;
    }

    @Override
    protected void execute()
    {
        lifter.setSpeed(joystick.getY());
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        lifter.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}