package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Basic Drive 1.0.0", group = "Test")

public class BasicDrive extends LinearOpMode{
    DcMotor motorLeft;
    DcMotor motorRight;

    public void runOpMode () {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        writeMessage(">", "Ready to start");

        waitForStart();

        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            writeMessage("Power Level Motor Left: ", Double.toString(motorLeft.getPower()));
            writeMessage("Power Level Motor Left: ", Double.toString(motorRight.getPower()));

            idle();
        }
    }

    public void writeMessage (String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }
}
