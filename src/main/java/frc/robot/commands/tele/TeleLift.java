package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.Lifter;
import frc.robot.utils.hardware.CustomJoystick;
import frc.robot.utils.hardware.CustomXboxController;

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
        // TODO implement
        double speed = joystick.getY();

        // if (Math.abs(speed) > 0.05)
        lifter.setSpeed(speed);
        // else
        // lifter.stopMotor();

        System.err.println(lifter.getEncoderValue());
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