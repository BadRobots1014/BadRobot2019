package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateGrabberCCW extends Command {
    public RotateGrabberCCW() {
        super(2, Robot.grabber);
    }

    @Override
    protected void initialize() {
        Robot.grabber.rotateCCW();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.grabber.stopMotor();
    }

    @Override
    protected void interrupted() {
        end();
    }
}