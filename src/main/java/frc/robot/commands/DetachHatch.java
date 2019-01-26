package frc.robot.commands;

import frc.robot.Robot;

public class DetachHatch extends RotateGrabberCW {
    private boolean hasPassedLimitSwitch;

    @Override
    protected void initialize() {
        hasPassedLimitSwitch = false;
    }
    
    @Override
    protected void execute() {
        if(!Robot.grabber.isCamInBackPosition())
            hasPassedLimitSwitch = true;
    }
    
    @Override
    protected boolean isFinished() {
        return hasPassedLimitSwitch && Robot.grabber.isCamInBackPosition();
    }
}