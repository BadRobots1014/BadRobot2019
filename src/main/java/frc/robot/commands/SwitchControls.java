package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Controls;

public class SwitchControls extends InstantCommand
{
    @Override
    protected void execute()
    {
        Controls.getInstance().switchContollers();
    }
}