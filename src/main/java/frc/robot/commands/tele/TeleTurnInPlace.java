package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Subsystems;
import frc.robot.subsystems.DriveTrain;

public class TeleTurnInPlace extends Command
{
    protected DriveTrain driveTrain;

    public TeleTurnInPlace()
    {
        super(Subsystems.getInstance().driveTrain);
    }

    @Override
    protected void initialize()
    {
        this.driveTrain = Subsystems.getInstance().driveTrain;
    }

    @Override
    protected void execute()
    {
        double speed = Controls.getInstance().getLeftX();
        driveTrain.tankDrive(speed, -speed);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        driveTrain.stopMotor();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}