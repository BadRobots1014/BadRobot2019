package frc.robot.commands;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BackHatchCam;

public class RotateBackHatchCamXTimes extends Command
{
    protected final BackHatchCam backHatchCam;
    protected final Counter counter;
    protected final int x;

    public RotateBackHatchCamXTimes(BackHatchCam backHatchCam, int x)
    {
        super(backHatchCam);
        this.backHatchCam = backHatchCam;
        this.x = x;
        this.counter = new Counter(); // TODO link to isCamInBackPosition()
    }

    @Override
    protected boolean isFinished()
    {
        return counter.get() == x;
    }
}