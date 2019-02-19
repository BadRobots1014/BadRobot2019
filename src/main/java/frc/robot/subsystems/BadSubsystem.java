package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class BadSubsystem extends Subsystem
{
    public BadSubsystem()
    {
        super();

        initComponents();
        initLogging();
    }

    protected abstract void initComponents();

    protected abstract void initLogging();

    @Override
    protected void initDefaultCommand()
    {

    }

    public static boolean isEnabled()
    {
        return true;
    }
}