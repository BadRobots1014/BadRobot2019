package frc.robot.subsystems;

import java.lang.reflect.Field;

import com.revrobotics.CANSparkMax;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.utils.hardware.CANTalonSRX;

public abstract class BadSubsystem extends Subsystem
{
    public BadSubsystem()
    {
        super();
        System.out.println("Subsystems/" + getClass().getSimpleName() + " : Initialization Started");

        initComponents();
        initLogging();

        System.out.println("Subsystems/" + getClass().getSimpleName() + " : Initialization Finished");
    }

    protected abstract void initComponents();

    protected void initLogging()
    {
        String subsystemName = getName();

        for (Field field : getClass().getDeclaredFields())
        {
            try
            {
                Object obj = field.get(this);
                String fieldName = field.getName();
                String combo = subsystemName + "/" + fieldName;

                if (obj instanceof CANSparkMax)
                {
                    CANSparkMax spark = (CANSparkMax) obj;

                    BadLog.createValue(combo + " Front Firmware", spark.getFirmwareString());

                    BadLog.createTopic(combo + " Output Percent", BadLog.UNITLESS, () -> spark.get(), "hide",
                            "join:" + subsystemName + "Output Percents");

                    BadLog.createTopic(combo + " Current", "A", () -> spark.getOutputCurrent(), "hide",
                            "join:" + subsystemName + "Output Currents");
                    BadLog.createTopic(combo + " Temperature", "C", () -> spark.getMotorTemperature(), "hide",
                            "join:" + subsystemName + "Output Temperatures");
                } else if (obj instanceof CANTalonSRX)
                {
                    CANTalonSRX talon = (CANTalonSRX) obj;

                    BadLog.createValue(combo + " Front Firmware", "" + talon.getFirmwareVersion());

                    BadLog.createTopic(combo + " Output Percent", BadLog.UNITLESS, () -> talon.get(), "hide",
                            "join:" + subsystemName + "Output Percents");

                    BadLog.createTopic(combo + " Current", "A", () -> talon.getOutputCurrent(), "hide",
                            "join:" + subsystemName + "Output Currents");
                    BadLog.createTopic(combo + " Temperature", "C", () -> talon.getTemperature(), "hide",
                            "join:" + subsystemName + "Output Temperatures");
                }
            } catch (Exception e)
            {
                continue;
            }
        }
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public static boolean isEnabled()
    {
        return false;
    }
}