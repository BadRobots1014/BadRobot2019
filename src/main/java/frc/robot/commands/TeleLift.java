package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Lifter;
import frc.robot.utils.CustomJoystick;

public class TeleLift extends Command
{
    protected final Lifter lifter;
    protected final CustomJoystick joystick;

    public TeleLift(Lifter lifter, CustomJoystick joystick)
    {
        super(lifter);
        this.lifter = lifter;
        this.joystick = joystick;
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