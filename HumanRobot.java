package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Human 0.1.0", group = "Team Two")
public class HumanRobot extends LinearOpMode{

    private DcMotor motorLeft;
    private DcMotor motorRight;

    private DcMotor legMotorLeft;
    private DcMotor legMotorRight;

    private DcMotor armMotorLeft;
    private DcMotor armMotorRight;

    public void runOpMode(){
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");


    }

}
