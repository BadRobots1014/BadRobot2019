package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
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

    // 28.5 = mid encoder value with slipping

    @Override
    protected void execute()
    {
        // TODO implement
        // double speed = -joystick.getY();

        double speed = -joystick.getThrottle();

        // System.err.println("Throttle: " + (-speed));

        if (joystick.getPOV() == 0)
            lifter.setSpeed(-speed);
        else if (joystick.getPOV() == 180)
            lifter.setSpeed(speed * 0.45);
        // else
            // lifter.setSpeed(-0.05);

        // if (Math.abs(speed) > 0.05)
        // else
        // lifter.setSpeed(-0.1);
        // System.out.println(lifter.getEncoderValue());
        // else
        // lifter.stopMotor();
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