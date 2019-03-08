package frc.robot.utils.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DIOTrigger extends Trigger
{
    private DigitalInput dio;
    private boolean inverted;

    public DIOTrigger(int address)
    {
        dio = new DigitalInput(address);
    }

    public void setInverted(boolean inverted)
    {
        this.inverted = inverted;
    }

    @Override
    public boolean get()
    {
        return inverted ? !dio.get() : dio.get();
    }
}