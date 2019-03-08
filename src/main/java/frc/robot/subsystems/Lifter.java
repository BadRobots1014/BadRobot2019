package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleLift;
import frc.robot.utils.hardware.DIOTrigger;

public class Lifter extends BadSubsystem
{
    private CANSparkMax motor;
    private DIOTrigger zeroSwitch;

    @Override
    protected void initComponents()
    {
        motor = new CANSparkMax(RobotMap.LIFTER_MOTOR, MotorType.kBrushless);
        motor.setIdleMode(IdleMode.kBrake);
        // System.err.println("Zero Switch: " + !zeroSwitch.get());
        // zeroSwitch = new DIOTrigger(10);
        // zeroSwitch.setInverted(true);
        // zeroSwitch.whenActive(new InstantCommand()
        // {
        //     @Override
        //     protected void execute()
        //     {
        //         motor.getEncoder().setPosition(0);
        //         System.err.println("ZERO ZERO ZERO");
        //     }
        // });
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleLift());
    }

    public boolean isAtBottom()
    {
        return false;// !zeroSwitch.get();
    }

    // This should return 0 at the lowest height
    public double getEncoderValue()
    {
        return motor.getEncoder().getPosition();
    }

    public void zeroEncoder()
    {
        motor.getEncoder().setPosition(0);
    }

    public void setSpeed(double speed)
    {
        motor.set(speed);
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