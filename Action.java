package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Action {
    /* Motor Singles */
    public String moveSingleDcMotorForwards(DcMotor motor, double power){
        if(!(power > 1.0) || !(power < -1.0)) {
            motor.setPower(power);
            return "";
        }else{
            return Double.toString(power) + " is not a valid option for motors power.";
        }
    }

    public void moveSingleDcMotorBackwards(DcMotor motor, double power){
        moveSingleDcMotorForwards(motor, -power);
    }

    /* Motor Pairs */
    public String movePairDcMotorForwards(DcMotor motorOne, DcMotor motorTwo, double power){
        if(!(power > 1.0) || !(power < -1.0)){
            moveSingleDcMotorForwards(motorOne, power);
            moveSingleDcMotorForwards(motorTwo, power);
            return "";
        }else{
            return Double.toString(power) + " is not a valid option for motors power.";
        }
    }

    public void movePairDcMotorBackwards(DcMotor motorOne, DcMotor motorTwo, double power){
        movePairDcMotorForwards(motorOne, motorTwo, -power);
    }

    /* Motor Groups (!!! These are unstable as of now, so be careful testing) */
    public String moveGroupDcMotorForwards(DcMotor[] motors, double[] powers){
        String out = loopAndSetThroughDcMotorArray(motors, powers, "moveGroupDcMotorForwards");

        if(out.equals("")){
            return null;
        }else{
            return out;
        }
    }

    public String moveGroupDcMotorBackwards(DcMotor[] motors, double[] powers){
        /* Redefining powers so it will move backwards */
        double[] powersBuf = new double[powers.length];
        double powerBuf;

        for(int i = 0; i < powers.length; i++){
            powerBuf = powers[i];

            if(powerBuf > 0.0){
                powersBuf[i] = -powerBuf;
            }else{
                powersBuf[i] = powerBuf;
            }
        }

        powers = powersBuf;

        String out = loopAndSetThroughDcMotorArray(motors, powers, "moveGroupDcMotorBackwards");

        if(out.equals("")){
            return null;
        }else{
            return out;
        }
    }

    /* Motor Interments Singles */


    /* Helpers */
    public String functionCallError(String function, String message){
        return "In call to \"" + function + "()\"\n" + message;
    }

    /**
    * returns a value once checked that will see if the values passed into
    * moveGroupDcMotors methods pass to continue on with the method
    *
    * @param lenOne the length of one array
    * @param lenTwo the length of another array
    * @return the value that is returned is the result of the tests
    * */
    public boolean motorGroupPassInspection(int lenOne, int lenTwo){
        return (lenOne == lenTwo) ? true : false;
    }

    /**
    * loops through a set of DcMotors and sets their power based on a set of doubles
     * @param motors the array or set of motors that are to be set
     * @param powers the array or set of values to set the motors to
     * @param functionName the name of the function being called, this is used for error messages mostly
     * @return if anything of content returns, than that means there was an error, it's something to put into telemetry
    * */
    public String loopAndSetThroughDcMotorArray(DcMotor[] motors, double[] powers, String functionName){
        int lenOfArray;
        double powerBuf;
        DcMotor motorBuf;

        if(motorGroupPassInspection(motors.length, powers.length)){
            lenOfArray = motors.length;
        }else{
            return functionCallError(functionName, "the length of \"DcMotor[] motors\" and \"double[] powers\" must be the same.");
        }

        for(int i = 0; i < lenOfArray ; i++){
            powerBuf = powers[i];
            motorBuf = motors[i];

            // Additional if statement just to make sure there are no errors
            if(!(powerBuf > 1.0) || !(powerBuf < -1.0)){
                moveSingleDcMotorForwards(motorBuf, powerBuf);
                return "";
            }else{
                return functionCallError(functionName, "The value " + Double.toString(powerBuf) + " is not a valid power option.");
            }
        }
        return functionCallError(functionName, "An unknown error has occurred.");
    }

}
