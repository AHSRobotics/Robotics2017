package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Robotic Arm 0.1.0", group = "Test")

public class RoboticArm extends LinearOpMode{
    /* This is Brownies Code */
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor spin360;
    DcMotor spin180;
    DcMotor expendableArm;

    private final double SPIN_POWER = 0.25;

    public void runOpMode () {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        spin180 = hardwareMap.dcMotor.get("spin180");
        spin360 = hardwareMap.dcMotor.get("spin360");
        expendableArm = hardwareMap.dcMotor.get("expendableArm");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        writeMessage(">", "Ready to start");

        waitForStart();

        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            // The 360 Motor
            if(gamepad2.left_trigger > 0.0){
                spin360.setPower(-SPIN_POWER);
            }else if(gamepad2.right_trigger > 0.0){
                spin360.setPower(SPIN_POWER);
            }else{
                spin360.setPower(0);
            }

            // The 180 Motor
            if(gamepad2.left_bumper){
                spin180.setPower(-SPIN_POWER);
            }else if(gamepad2.right_bumper){
                spin180.setPower(SPIN_POWER);
            }

            // The Expendable Arm
            if(gamepad2.dpad_up){
                expendableArm.setPower(SPIN_POWER);
            }else if(gamepad2.dpad_down){
                expendableArm.setPower(-SPIN_POWER);
            }else{
                expendableArm.setPower(0);
            }

            idle();
        }
    }

    public void writeMessage (String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }
}