package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public abstract class BadSubsystem extends Subsystem
{
    protected final Robot robot;

    public BadSubsystem(Robot robot)
    {
        super();
        this.robot = robot;

        initComponents();
        initLogging();
    }

    protected abstract void initComponents();

    protected abstract void initLogging();

    @Override
    protected void initDefaultCommand()
    {

    }
}