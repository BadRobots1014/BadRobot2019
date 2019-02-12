package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Lifter;

public class PlaceHatch extends CommandGroup
{
    public PlaceHatch(Lifter lifter, Grabber grabber, double height)
    {
        addSequential(new SetLifterHeight(lifter, height));
        addSequential(new RotateGrabberCCW(grabber)); // TODO change this
    }
}