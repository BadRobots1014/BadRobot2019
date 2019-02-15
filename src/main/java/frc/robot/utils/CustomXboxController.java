package frc.robot.utils;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.hal.HAL;

public class CustomXboxController extends GenericHID
{
    public final JoystickButton aButton = new JoystickButton(this, 1);
    public final JoystickButton bButton = new JoystickButton(this, 2);
    public final JoystickButton xButton = new JoystickButton(this, 3);
    public final JoystickButton yButton = new JoystickButton(this, 4);
    public final JoystickButton bumperLeftButton = new JoystickButton(this, 5);
    public final JoystickButton bumperRightButton = new JoystickButton(this, 6);
    public final JoystickButton backButton = new JoystickButton(this, 7);
    public final JoystickButton startButton = new JoystickButton(this, 8);
    public final JoystickButton stickLeftButton = new JoystickButton(this, 9);
    public final JoystickButton stickRightButton = new JoystickButton(this, 10);

    /**
     * Construct an instance of a joystick. The joystick index is the USB port on
     * the drivers station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public CustomXboxController(final int port)
    {
        super(port);

        HAL.report(tResourceType.kResourceType_XboxController, port);
    }

    /**
     * Get the trigger axis value of the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The trigger axis value of the controller.
     */
    public double getTriggerAxis(Hand hand)
    {
        if (hand.equals(Hand.kLeft))
            return getRawAxis(2);
        else
            return getRawAxis(3);
    }

    /**
     * Get the X axis value of the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The X axis value of the controller.
     */
    @Override
    public double getX(Hand hand)
    {
        if (hand.equals(Hand.kLeft))
            return getRawAxis(0);
        else
            return getRawAxis(4);
    }

    /**
     * Get the Y axis value of the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The Y axis value of the controller.
     */
    @Override
    public double getY(Hand hand)
    {
        if (hand.equals(Hand.kLeft))
            return getRawAxis(1);
        else
            return getRawAxis(5);
    }
}
