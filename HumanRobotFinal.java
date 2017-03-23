package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Human v0.1.0", group = "Team One")
public class HumanRobotFinal extends LinearOpMode{

    private DcMotor motorLeft;
    private DcMotor motorRight;

    private DcMotor legMotorLeft;
    private DcMotor legMotorRight;

    private DcMotor armMotorLeft;
    private DcMotor armMotorRight;

    private final double defaultSpeed = 0.5; // bot needs to be slower
    private final double fullSpeed = 1.0;

    private boolean variablesInit = false;

    public void runOpMode(){
        initializeHardware();

        waitForStart();

        String legs, armsUp, armsDown;

        while(opModeIsActive()){
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            // Checking to see if there is some input
            legs = checkIfDpadPressed();
            armsUp = checkIfTriggerPressed();
            armsDown = checkIfBumperPressed();

        }
    }


    /**
     * takes in data gotten from hardware, and then uses that to make everything move based on input
     * @param legCode string that controls how the legs move
     * @param armUpCode string that controls how the arms move up
     * @param armDownCode string that controls how the arms move down
     */
    public void setHardware(String legCode, String armUpCode, String armDownCode){
        
    }


    /**
     * assigns instance variables to hardware using hardwareMap
     * config name: iron_man
     */
    public void initializeHardware(){
        if(!variablesInit) {
            motorLeft = hardwareMap.dcMotor.get("motorLeft");
            motorRight = hardwareMap.dcMotor.get("motorRight");
            legMotorLeft = hardwareMap.dcMotor.get("legMotorLeft");
            legMotorRight = hardwareMap.dcMotor.get("legMotorRight");
            armMotorLeft = hardwareMap.dcMotor.get("armMotorLeft");
            armMotorRight = hardwareMap.dcMotor.get("armMotorRight");

            motorLeft.setDirection(DcMotor.Direction.REVERSE);

            variablesInit = true;
        }else{
            printMessage(">", "Initialization occurred when robot already initialized.");
        }
    }


    /**
     * outputs string containing data about dpad being pressed and how
     * N - Null
     * P - Pressed
     * U - Up
     * D - Down
     *
     * Example "PD" - Pressed Down
     *
     * @return the character code that will tell the hardware what to do
     */
    public String checkIfDpadPressed(){
        String dataBuff ;

        if(gamepad1.dpad_up) {
            dataBuff = "PU";
        }else if(gamepad1.dpad_down){
            dataBuff = "PD";
        }else{
            dataBuff = "NN";
        }

        return dataBuff;
    }


    /**
     * outputs string containing data about triggers being pressed
     * Note: both triggers pressed at the same time should result  both hands going up
     *
     * L - Left Trigger
     * R - Right Trigger
     * N - Nothing
     *
     * Example "LR" - Left and Right arms need to move
     *
     * @return the character code that will tell the hardware what to do
     */
    public String checkIfTriggerPressed(){
        String dataBuff = "";

        if(gamepad1.left_trigger > 0){
            dataBuff += "L";
        }

        if(gamepad1.right_trigger > 0){
            dataBuff += "R";
        }

        return dataBuff;
    }


    /**
     * Almost the same method as checkIfTriggerPressed but in different method
     * to help read ability
     */
    public String checkIfBumperPressed(){
        String dataBuff = "";

        if(gamepad1.left_bumper){
            dataBuff += "L";
        }

        if(gamepad1.right_bumper){
            dataBuff += "R";
        }

        return dataBuff;
    }


    /**
     * does the entire telemetry process in one go
     * @param prompt what is going to be at the start of the new message
     * @param message the content of the message
     */
    public void printMessage(String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }

}