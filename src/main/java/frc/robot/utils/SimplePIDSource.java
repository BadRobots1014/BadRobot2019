package frc.robot.utils;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SimplePIDSource implements PIDSource
{
    private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;
    private final Supplier<Double> dataSupplier;

    public SimplePIDSource(Supplier<Double> dataSupplier)
    {
        this(PIDSourceType.kDisplacement, dataSupplier);
    }

    public SimplePIDSource(PIDSourceType type, Supplier<Double> dataSupplier)
    {
        setPIDSourceType(type);
        this.dataSupplier = dataSupplier;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSourceType)
    {
        this.pidSourceType = pidSourceType;
    }

    @Override
    public PIDSourceType getPIDSourceType()
    {
        return pidSourceType;
    }

    @Override
    public double pidGet()
    {
        return dataSupplier.get();
    }
}