package frc.robot.utils.hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class CustomJoystick extends Joystick
{
    public final JoystickButton trigger = new JoystickButton(this, 1);

    public final JoystickButton sideThumbButton = new JoystickButton(this, 2);

    public final JoystickButton frontLeftButton = new JoystickButton(this, 5);
    public final JoystickButton frontRightButton = new JoystickButton(this, 6);
    public final JoystickButton backLeftButton = new JoystickButton(this, 3);
    public final JoystickButton backRightButton = new JoystickButton(this, 4);

    public final JoystickButton button7 = new JoystickButton(this, 7);
    public final JoystickButton button8 = new JoystickButton(this, 8);
    public final JoystickButton button9 = new JoystickButton(this, 9);
    public final JoystickButton button10 = new JoystickButton(this, 10);
    public final JoystickButton button11 = new JoystickButton(this, 11);
    public final JoystickButton button12 = new JoystickButton(this, 12);

    public final SimpleDigitalInput povUp = new SimpleDigitalInput(() -> this.getPOV() == 0);
    public final SimpleDigitalInput povDown = new SimpleDigitalInput(() -> this.getPOV() == 180);

    public final SimpleDigitalInput axisYForward = new SimpleDigitalInput(() -> this.getY() <= -0.2);
    public final SimpleDigitalInput axisYBackward = new SimpleDigitalInput(() -> this.getY() >= 0.2);


    public final SimpleDigitalInput povMoved = new SimpleDigitalInput(() -> this.getPOV() != -1);

    // public final Button povUp = new Button()
    // {
    // @Override
    // public boolean get()
    // {
    // return CustomJoystick.this.getPOV() == 0;
    // }
    // };

    // public final Button povDown = new Button()
    // {
    // @Override
    // public boolean get()
    // {
    // return CustomJoystick.this.getPOV() == 180;
    // }
    // };

    // public final Button axisYForward = new Button()
    // {
    // @Override
    // public boolean get()
    // {
    // return CustomJoystick.this.getY() > 0.1;
    // }
    // };

    // public final Button axisYBackward = new Button()
    // {
    // @Override
    // public boolean get()
    // {
    // return CustomJoystick.this.getY() < -0.1;
    // }
    // };

    public CustomJoystick(int port)
    {
        super(port);
    }
}