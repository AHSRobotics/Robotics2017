package org.firstinspires.ftc.teamcode.ActionHub.Exceptions;

public class InvalidPowerLevelException extends Exception{
    public InvalidPowerLevelException(){
        super("Power level entered must be between -1.0 & 1.0");
    }

    public InvalidPowerLevelException(String message){
        super(message);
    }
}
