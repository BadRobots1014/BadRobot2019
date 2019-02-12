package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BackHatchCam;

public class RotateGrabberCW extends Command
{
    protected final BackHatchCam backHatchCam;

    public RotateGrabberCW(BackHatchCam backHatchCam)
    {
        super(backHatchCam);
        this.backHatchCam = backHatchCam;
    }

    @Override
    protected void initialize()
    {
        backHatchCam.rotateCW();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        backHatchCam.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}