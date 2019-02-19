package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceHatch extends CommandGroup
{
    public PlaceHatch(double height)
    {
        addSequential(new SetLifterHeight(height));
        addSequential(new RotateGrabberCCW()); // TODO change this
    }
}