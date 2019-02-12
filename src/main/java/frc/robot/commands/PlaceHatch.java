package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Lifter;

public class PlaceHatch extends CommandGroup
{
    public PlaceHatch(Lifter lifter, Claw claw, double height)
    {
        addSequential(new SetLifterHeight(lifter, height));
        addSequential(new OpenClaw(claw)); // TODO change this
    }
}