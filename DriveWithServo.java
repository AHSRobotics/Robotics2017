package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Drive With Servo 0.1.0", group = "Test")

public class DriveWithServo extends LinearOpMode{
    /* This is Brownies Code */
    DcMotor motorLeft;
    DcMotor motorRight;
    Servo servo;

    public void runOpMode () {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        servo = hardwareMap.servo.get("servo");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        writeMessage(">", "Ready to start");

        waitForStart();

        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            writeMessage("Power Level Motor Left: ", Double.toString(motorLeft.getPower()));
            writeMessage("Power Level Motor Left: ", Double.toString(motorRight.getPower()));

            if(gamepad1.dpad_up){
                servo.setPosition(servo.getPosition() + 0.01);

                if(servo.getPosition() == 1.0){
                    servo.setPosition(0);
                }
            }

            idle();
        }
    }

    public void writeMessage (String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }
}