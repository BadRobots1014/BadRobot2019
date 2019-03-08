package frc.robot.utils.hardware;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class SimpleDigitalInput extends Trigger
{
    private Supplier<Boolean> source;

    public SimpleDigitalInput(Supplier<Boolean> source)
    {
        this.source = source;
    }

    @Override
    public boolean get()
    {
        return source.get();
    }
}