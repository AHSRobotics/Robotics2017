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

    private final double DEFAULT_SPEED= 0.25; // bot needs to be slower
    private final double FULL_SPEED = 1.0;
    private final double STOP_SPEED = 0.0;

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
        char dpadPushed = legCode.charAt(0), dpadDirection = legCode.charAt(1), armUp, armDown;

        armUp = (armUpCode.length() > 1) ? 'B':armUpCode.charAt(0);
        armDown = (armDownCode.length() > 1) ? 'B':armDownCode.charAt(0);

        String armCode = "T" + armUp + "B" + armDown; // Example "TNBR" = Triggers None Bumpers Right

        setLegs(dpadPushed, dpadDirection);
        setArms(armCode);

    }


    /**
     * Uses the input code gotten from checkIfDpadPressed() and sets motors accordingly
     * @param push tells if the dpad is being pushed
     * @param pushDirection tells which direction the dpad is being pushed
     * @return exits out of method if there is nothing to work with
     */

    public void setLegs(char push, char pushDirection){
        if(push == 'P'){
            double power = (pushDirection == 'U') ? DEFAULT_SPEED:(-DEFAULT_SPEED); // Setting sign of speed
            legMotorLeft.setPower(power);
            legMotorRight.setPower(power);
        }else if(legMotorLeft.getPower() > 0 || legMotorRight.getPower() > 0){
            legMotorLeft.setPower(STOP_SPEED);
            legMotorRight.setPower(STOP_SPEED);
        }else{
            return;
        }
    }


    /**
     * Uses the input code gotten from the checkIfTriggerPressed() and checkIfBumperPressed()
     * @param armCode the code that will tell the motors what to do
     */
    public void setArms(String armCode){
        char triggerCode = armCode.charAt(1); // In a code like TRBL the value would be R
        char bumperCode = armCode.charAt(3);

        if(triggerCode == 'N' && bumperCode == 'N'){
            armMotorLeft.setPower(STOP_SPEED);
            armMotorRight.setPower(STOP_SPEED);
            return;
        }

        /*
        * TODO Check if case with no breaks will continue running down the cases
         */
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
     * NOTE: Left arm is gone
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

        if(gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0){
            dataBuff = "N";
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

        if(!gamepad1.left_bumper && !gamepad1.right_bumper){
            dataBuff = "N";
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