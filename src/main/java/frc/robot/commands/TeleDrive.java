package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

public class TeleDrive extends Command
{
    private DriveTrain driveTrain;
    private XboxController controller0;
    private Joystick controller1;
    public TeleDrive(DriveTrain driveTrain, XboxController controller0, Joystick controller1)
    {
        this.driveTrain = driveTrain;
        this.controller0 = controller0;
        this.controller1 = controller1;
    }

    @Override
    protected void initialize() 
    {
       
    }
    
    @Override
    protected void execute() 
    {
        double left = 0, right = 0;
        left = controller0.getY(Hand.kLeft);
        right = controller0.getY(Hand.kRight);
        driveTrain.tankDrive(left, right);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}