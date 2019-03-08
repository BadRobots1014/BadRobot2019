package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.I2C;
import frc.robot.RobotMap;
import frc.robot.utils.hardware.CANTalonSRX;
import frc.robot.utils.hardware.RevColorSensorV2;

public class Articulator extends BadSubsystem
{
    private CANTalonSRX motor;
    private RevColorSensorV2 colorSensor;
    private boolean lastColorWasBlack, forward;
    private int ticks;

    @Override
    protected void initComponents()
    {
        motor = new CANTalonSRX(RobotMap.ARTICULATOR_MOTOR);
        motor.overrideLimitSwitchesEnable(false);
        motor.setNeutralMode(NeutralMode.Brake);

        this.colorSensor = new RevColorSensorV2(I2C.Port.kOnboard);
    }

    @Override
    public void periodic()
    {
        if (colorSensor.getRGBA()[0] < 2500)
        {
            if (!lastColorWasBlack)
                lastColorWasBlack = true;
        } else
        {
            if (lastColorWasBlack)
            {
                lastColorWasBlack = false;
                ticks += (forward ? 1 : -1);
            }
        }

        // System.err.println("Ticks: " + ticks + "\t\t\tReading: " + colorSensor.getRGBA()[0]);
    }

    public void rotate(double speed)
    {
        motor.set(speed);
        forward = speed >= 0;
    }

    public int getEncoderValue()
    {
        return ticks;
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