package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public class BasicDriving extends LinearOpMode{

    DcMotor motorLeft;
    DcMotor motorRight;

    public void runOpMode(){
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        printMessage("> ", "Ready to Start");

        waitForStart();

        while (opModeIsActive()){
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            idle();
        }
    }

    public void printMessage(String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }
}
