package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Controls;

public class TeleDriveStraightBackward extends TeleDriveStraight
{
    @Override
    protected void usePIDOutput(double output)
    {
        double speed = Controls.getInstance().mainController.getTriggerAxis(Hand.kLeft);
        // TODO IMPLEMENT
        System.err.println("Angle: " + driveTrain.getAngle());
        // driveTrain.tankDrive(-speed, -speed);
        driveTrain.tankDrive(speed + output, speed - output);
    }
}