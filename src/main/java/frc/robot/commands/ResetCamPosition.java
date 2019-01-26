package frc.robot.commands;

import frc.robot.Robot;

public class ResetCamPosition extends RotateGrabberCW {
    @Override
    protected boolean isFinished() {
        return Robot.grabber.isCamInBackPosition();
    }
}