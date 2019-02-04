package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class CustomJoystick extends Joystick
{
    public final JoystickButton trigger = new JoystickButton(this, 2);

    public final JoystickButton sideThumbButton = new JoystickButton(this, 2);

    public final JoystickButton frontLeftButton = new JoystickButton(this, 5);
    public final JoystickButton frontRightButton = new JoystickButton(this, 6);
    public final JoystickButton backleftButton = new JoystickButton(this, 3);
    public final JoystickButton backRightButton = new JoystickButton(this, 4);

    public final JoystickButton button7 = new JoystickButton(this, 7);
    public final JoystickButton button8 = new JoystickButton(this, 8);
    public final JoystickButton button9 = new JoystickButton(this, 9);
    public final JoystickButton button10 = new JoystickButton(this, 10);
    public final JoystickButton button11 = new JoystickButton(this, 11);
    public final JoystickButton button12 = new JoystickButton(this, 12);

    // TODO declare side buttons here

    public CustomJoystick(int port)
    {
        super(port);
    }
}