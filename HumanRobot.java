package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Human 0.1.0", group = "Team Two")
public class HumanRobot extends LinearOpMode{

    private DcMotor motorLeft;
    private DcMotor motorRight;

    private DcMotor legMotorLeft;
    private DcMotor legMotorRight;

    private DcMotor armMotorLeft;
    private DcMotor armMotorRight;
    
    private final double armSpeed = 0.5;
    private final double legSpeed = 0.5;

    public void runOpMode() {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        legMotorLeft = hardwareMap.dcMotor.get("legMotorLeft");
        legMotorRight = hardwareMap.dcMotor.get("legMotorRight");
        armMotorLeft = hardwareMap.dcMotor.get("armMotorLeft");
        armMotorRight = hardwareMap.dcMotor.get("armMotorRight");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        boolean legMoving, armMoving;
        double armSpeedLeft, armSpeedRight, legSpeed;
        
        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            // Checking to see if there is some input
            legMoving = checkIfDpadPressed();
            armMoving = checkIfTriggerPressed();

            setHardware(legMoving, armMoving);

        }
    }
    
    public void setHardware(boolean legMoving, boolean armMoving){
        if(legMoving){
            int directionCode = getDirection();
            
            if(directionCode == 0x1){
                legMotorLeft.setPower(legSpeed);
                legMotorRight.setPower(legSpeed);
            }else if(directionCode == 0x2){
                double backSpeedBuff = legSpeed * -1;
                
                legMotorLeft.setPower(backSpeedBuff);
                legMotorRight.setPower(backSpeedBuff);
            }else{
                telemetry.addData(">", "Direction code in set hardware for leg is set to the wrong number.");
                telemetry.update();
            }
        }
        
        if(armMoving){
            int directionCode = getDirection();
        }
    }
    
    public int getDirection(char part) {
        if (part == 'L') {
            if (gamepad1.dpad_up) {
                return 0x1;
            } else if (gamepad1.dpad_down) {
                return 0x2;
            }
        }else if(part == 'A'){
            
        }
        
        return -0x1;
    }
    
    // On/Off Switch
    public boolean checkIfDpadPressed() {
        if (gamepad1.dpad_up || gamepad1.dpad_down){
            return true;
        }
        
        return false;
    }
    
    public boolean checkIfTriggerPressed(){
        if(gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0){
            return true;
        }else if(gamepad1.left_bumper || gamepad2.right_bumper){
            return true;
        }
        
        return false;
    }
}
