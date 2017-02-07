package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Humanoid Bot 0.1.0", group = "Untested")
public class HumanoidBot extends LinearOpMode{

    DcMotor motorLeft;
    DcMotor motorRight;

    /* Controls:
    * Dpad Up: Accelerate
    * Dpad Down: Decrement*/
    DcMotor motorLegLeft;
    DcMotor motorLegRight;

    DcMotor motorHeadRotate;

    /* Some Magic Numbers Defined */
    private final double LEG_POWER = 0.3;
    private final double HEAD_POWER = 0.5;

    public void runOpMode(){
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLegLeft = hardwareMap.dcMotor.get("motorLegLeft");
        motorLegRight = hardwareMap.dcMotor.get("motorLegRight");

        printMessage("> ", "Ready to Start");

        waitForStart();

        while (opModeIsActive()){
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            if(gamepad1.dpad_up) {
                motorLegLeft.setPower(LEG_POWER);
                motorLegRight.setPower(LEG_POWER);
            }else if(gamepad1.dpad_down){
                motorLegLeft.setPower(-LEG_POWER);
                motorLegRight.setPower(-LEG_POWER);
            }

            if(!gamepad1.dpad_up || !gamepad1.dpad_down){
                motorLegLeft.setPower(0);
                motorLegRight.setPower(0);
            }

            if(gamepad1.left_trigger != 0){
                motorHeadRotate.setPower(HEAD_POWER);
            }else if(gamepad1.right_trigger != 0){
                motorHeadRotate.setPower(-HEAD_POWER);
            }

            if(gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0){
                motorHeadRotate.setPower(0);
            }

            idle();
        }
    }

    public void printMessage(String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }
}
