package frc.robot.commands;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateGrabberXTimes extends Command
{
    private final Counter counter;
    private final int x;

    public RotateGrabberXTimes(int x)
    {
        super(Robot.grabber);
        this.counter = new Counter(); // TODO link to isCamInBackPosition()
        this.x = x;
    }

    @Override
    protected boolean isFinished()
    {
        return counter.get() == x;
    }
}