package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.utils.AdafruitMultiplexer;
import frc.robot.utils.RevColorSensorV2;

public class MulticolorSensor extends Subsystem
{
    private AdafruitMultiplexer multiplexer;
    private RevColorSensorV2 colorSensor;
    private final short[][] buffer;

    public MulticolorSensor()
    {
        multiplexer = new AdafruitMultiplexer(Port.kOnboard, 0x70);
        colorSensor = new RevColorSensorV2(Port.kOnboard);
        buffer = new short[2][4];
        multiplexer.selectChannel(0);
    }

    private void readRGB(int channel)
    {
        multiplexer.selectChannel(channel);

        try
        {
            Thread.sleep(10);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        colorSensor.init();
        buffer[channel] = colorSensor.getRGBA();
        System.out.println(multiplexer.getCurrentChannel() + " : " + colorSensor.getStatus() + " | "
                + Arrays.toString(buffer[channel]) + " | " + colorSensor.getControl());

        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public short[] getRGB(int channel)
    {
        return buffer[channel];
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new Command()
        {
            {
                requires(MulticolorSensor.this);
            }

            @Override
            protected void execute()
            {
                // for (int i = 0; i < buffer.length; i++)
                readRGB(0);
                readRGB(1);

                System.out.println("Reading Values");
            }

            @Override
            protected boolean isFinished()
            {
                return false;
            }
        });
    }
}