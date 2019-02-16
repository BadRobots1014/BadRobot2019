package frc.robot.utils;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;

/**
 * Implementation of the RevRobotics Color Sensor V2
 * 
 * See http://www.revrobotics.com/content/docs/TMD3782_v2.pdf
 */
public class RevColorSensorV2 implements AutoCloseable
{
    /* ---------------------------- READ/WRITE ---------------------------- */

    private final static int ENABLE = 0x00; // Enables states and interrupts
    private final static int ATIME = 0x01; // RGBC time
    private final static int WTIME = 0x03; // Wait time

    /**
     * Clear Channel Interrupt Threshold Registers
     */
    private final static int AILTL = 0x04; // Clear interrupt low threshold low byte
    private final static int AILTH = 0x05; // Clear interrupt low threshold high byte
    private final static int AIHTL = 0x06; // Clear interrupt high threshold low byte
    private final static int AIHTH = 0x07; // Clear interrupt high threshold high byte

    /**
     * Proximity Interrupt Threshold Registers
     */
    private final static int PILTL = 0x08; // Proximity interrupt low threshold low byte
    private final static int PILTH = 0x09; // Proximity interrupt low threshold high byte
    private final static int PIHTL = 0x0A; // Proximity interrupt high threshold low byte
    private final static int PIHTH = 0x0B; // Proximity interrupt high threshold high byte

    private final static int PERS = 0x0C; // Interrupt persistence filters
    private final static int CONFIG = 0x0D; // Configuration
    private final static int PPULSE = 0x0E; // Proximity pulse count
    private final static int CONTROL = 0x0F; // Gain control

    /* ----------------------------- READ ONLY ----------------------------- */

    private final static int REVISION = 0x11; // Die revision number
    private final static int ID = 0x12; // Device ID
    private final static int STATUS = 0x13; // Device status

    /**
     * RGBC Data Registers
     */
    private final static int CDATA = 0x14; // Clear ADC low data register
    private final static int CDATAH = 0x15; // Clear ADC high data register
    private final static int RDATA = 0x16; // Red ADC low data register
    private final static int RDATAH = 0x17; // Red ADC high data register
    private final static int GDATA = 0x18; // Green ADC low data register
    private final static int GDATAH = 0x19; // Green ADC high data register
    private final static int BDATA = 0x1A; // Blue ADC low data register
    private final static int BDATAH = 0x1B; // Blue ADC high data register

    /**
     * Proximity Data Registers
     */
    private final static int PDATA = 0x1C; // Proximity ADC low data register
    private final static int PDATAH = 0x1D; // Proximity ADC high data register

    /**
     * Enable Register Fields
     */
    private final static int PIEN = 1 << 5; // Proximity Interrupt Enable
    private final static int AIEN = 1 << 4; // Ambient Light Sensor Interupt Enable
    private final static int WEN = 1 << 3; // Wait Enable
    private final static int PEN = 1 << 2; // Proximity Enable
    private final static int AEN = 1 << 1; // ADC Enable
    private final static int PON = 1; // Power ON

    /**
     * Command Register Fields
     */
    private final static int COMMAND = 1 << 7; // Specified register address
    private final static int MULTI_BYTE_BIT = 1 << 5; // Signals device to read successive bytes

    /**
     * Instance Variables
     */
    private final I2C i2c;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(8);
    private final double integrationTime = 10;

    private short red;
    private short green;
    private short blue;
    private short proximity;

    /**
     * Creates a new instance of a RevColorSensorV2 and prepares it for operation
     * 
     * @param i2c the I2C instance to use for communication
     */
    public RevColorSensorV2(I2C i2c)
    {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.i2c = i2c;

        i2c.write(COMMAND | ENABLE, PON | AEN | PEN);
        i2c.write(COMMAND | ATIME, (int) (256 - integrationTime / 2.38));
        i2c.write(COMMAND | PPULSE, 0b1111);
    }

    /**
     * Read the red, green, blue, and proximity values from the sensor
     */
    public void readAll()
    {
        byteBuffer.clear();
        i2c.read(COMMAND | MULTI_BYTE_BIT | RDATA, 8, byteBuffer);

        red = asUnsignedShort(byteBuffer.getShort(0));
        green = asUnsignedShort(byteBuffer.getShort(2));
        blue = asUnsignedShort(byteBuffer.getShort(4));
        proximity = asUnsignedShort(byteBuffer.getShort(6));
    }

    /**
     * @return the Color represented by the last RGB reading
     */
    public Color getColor()
    {
        return new Color(red, green, blue);
    }

    public boolean isColorSimilarTo(Color a)
    {
        Color b = getColor();
        int dR = b.getRed() - a.getRed();
        int dG = b.getGreen() - a.getGreen();
        int dB = b.getBlue() - a.getBlue();
        double delta = Math.sqrt((dR * dR) + (dG * dG) + (dB * dB));
        return delta < 1000; // TODO magic number
    }

    private short asUnsignedShort(short signedShort)
    {
        if (signedShort < 0)
            return signedShort += 1 << 16;
        else
            return signedShort;
    }

    public short getRed()
    {
        return red;
    }

    public short getGreen()
    {
        return green;
    }

    public short getBlue()
    {
        return blue;
    }

    public short getProximity()
    {
        return proximity;
    }

    /**
     * Reads the current status of the sensor
     * 
     * @return the current value in the status register
     */
    public int getStatus()
    {
        byteBuffer.clear();
        i2c.read(COMMAND | STATUS, 1, byteBuffer);
        return byteBuffer.get(0);
    }

    public void close()
    {
        i2c.close();
    }
}