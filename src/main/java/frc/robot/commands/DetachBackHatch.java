package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems;
import frc.robot.subsystems.BackHatchCam;

public class DetachBackHatch extends Command
{
    protected final BackHatchCam backHatchCam;

    public DetachBackHatch()
    {
        super(5, Subsystems.getInstance().backHatchCam);
        this.backHatchCam = Subsystems.getInstance().backHatchCam;
    }

    @Override
    protected void execute()
    {
        backHatchCam.rotateCW();
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

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}