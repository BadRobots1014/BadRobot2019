package frc.robot.utils.hardware;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickAxis extends Trigger
{
    protected final GenericHID joystick;
    protected final int axisNumber;
    protected final double deadband;

    public JoystickAxis(GenericHID joystick, int axisNumber, double deadband)
    {
        this.joystick = joystick;
        this.axisNumber = axisNumber;
        this.deadband = deadband;
    }

    @Override
    public boolean get()
    {
        return Math.abs(joystick.getRawAxis(axisNumber)) > deadband;
    }
}