package frc.robot.utils;

public class MathUtils
{
    public static boolean isWithinThreshold(double value, double target, double maxDeviation)
    {
        return Math.abs(target - value) <= maxDeviation;
    }
}