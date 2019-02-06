package frc.robot.utils;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SimplePIDSource implements PIDSource
{
    private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;
    private final DataSource dataSource;

    public SimplePIDSource(DataSource dataSource)
    {
        this(PIDSourceType.kDisplacement, dataSource);
    }

    public SimplePIDSource(PIDSourceType type, DataSource dataSource)
    {
        setPIDSourceType(type);
        this.dataSource = dataSource;
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
        return dataSource.getData();
    }
}