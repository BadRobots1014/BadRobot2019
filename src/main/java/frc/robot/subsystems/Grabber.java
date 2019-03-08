package frc.robot.subsystems;

import frc.robot.utils.hardware.CANTalonSRX;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotMap;
import frc.robot.Subsystems;
import frc.robot.commands.SpinGrabberCW;

public class Grabber extends BadSubsystem
{
    private CANTalonSRX motor;

    @Override
    protected void initComponents()
    {
        motor = new CANTalonSRX(RobotMap.GRABBER_MOTOR);
    }

    // @Override
    // protected void initDefaultCommand()
    // {
    //     setDefaultCommand(new CommandGroup()
    //     {
    //         {
    //             addSequential(new WaitCommand(2));
    //             addSequential(new SpinGrabberCW());
    //             addSequential(new InstantCommand()
    //             {
    //                 {
    //                     requires(Subsystems.getInstance().grabber);
    //                 }

    //                 @Override
    //                 protected void execute()
    //                 {
    //                     Subsystems.getInstance().grabber.stopMotor();
    //                 }
    //             });
    //         }

    //     });
    // }

    public void spin(double speed)
    {
        motor.set(speed);
    }

    public void spinCW()
    {
        motor.set(1);
    }

    public void spinCCW()
    {
        motor.set(-1);
    }

    public void stopMotor()
    {
        motor.stopMotor();
    }

    public static boolean isEnabled()
    {
        return true;
    }
}