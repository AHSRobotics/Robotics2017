package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Second Op Mode", group = "Test")

public class SecondOpMode extends LinearOpMode{
    /* This is Brownies Code */
    DcMotor motorLeft;
    DcMotor motorRight;

    public void runOpMode () {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorLeft");

        writeMessage(">", "Ready to start");

        waitForStart();

        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            idle();
        }
    }

    public void writeMessage (String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }
}

