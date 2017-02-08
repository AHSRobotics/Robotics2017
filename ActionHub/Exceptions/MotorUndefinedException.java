package org.firstinspires.ftc.teamcode.ActionHub.Exceptions;

public class MotorUndefinedException extends Exception{
    public MotorUndefinedException(){
        super("A motor passed was not defined.");
    }

    public MotorUndefinedException(String message){
        super(message);
    }
}
