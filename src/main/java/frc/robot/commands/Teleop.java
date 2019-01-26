package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.TeleDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class Teleop extends CommandGroup
{
    public Teleop(DriveTrain driveTrain)
    {
        super.addParallel(new TeleDrive(driveTrain, Robot.m_oi.xboxController, Robot.m_oi.joystick));
    }

}
