package org.firstinspires.ftc.teamcode.ActionHub;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.ActionHub.Exceptions.InvalidPowerLevelException;
import org.firstinspires.ftc.teamcode.ActionHub.Exceptions.MotorUndefinedException;

public class DcMotorAction {
    public void moveSingleDcMotorForwards(DcMotor motor, double power) throws InvalidPowerLevelException, MotorUndefinedException{
        boolean validPowerLevel = isPowerValid(power);

        if(!validPowerLevel){
            throw new InvalidPowerLevelException("\"" +power + "\" is an invalid power level. The power level must be between the numbers -1.0 & 1.0");
        }

        String isDefined = motor.getDeviceName();

        if(isDefined.equals("") || isDefined.equals(null)){
            try{
                throw new MotorUndefinedException();
            }

            catch (NullPointerException e){
                /* This case just is here so the program doesn't die */
            }
        }

        // Since it passed the case that proved the motor was real

        motor.setPower(power);
    }

    public boolean isPowerValid(double power){
        return (power > -1.1 && power < 1.1);
    }
}
