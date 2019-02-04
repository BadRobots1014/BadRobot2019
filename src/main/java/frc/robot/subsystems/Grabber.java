package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleGrab;

public class Grabber extends Subsystem
{
    private SpeedController motor = new CANSparkMax(RobotMap.GRABBER_MOTOR, MotorType.kBrushless);
    private DigitalInput camLimitSwitch = new DigitalInput(RobotMap.CAM_LIMIT_SWITCH);

    public void rotate(double speed)
    {
        motor.set(speed);
    }

    public void rotateCW()
    {
        motor.set(1);
    }

    public void rotateCCW()
    {
        motor.set(-1);
    }

    public void stopMotor()
    {
        motor.stopMotor();
    }

    public boolean isCamInBackPosition()
    {
        return camLimitSwitch.get();
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleGrab());
    }
}