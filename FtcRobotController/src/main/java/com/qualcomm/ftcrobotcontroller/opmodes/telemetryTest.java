package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;


/**
 * Created by Gabe on 2/6/2016.
 */

public class telemetryTest extends OpMode {
    //declare variables that will represent motors to make life eaiser
    DcMotor leftMotor;
    DcMotor rightMoto;
    DcMotor rack;
    DcMotor winch;
    float leftEnc;
    float rightEnc;

    @Override
    public void init() {
        //get references to the motors from the hardware map
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMoto = hardwareMap.dcMotor.get("rightMoto");
        rack = hardwareMap.dcMotor.get("rack");
        winch = hardwareMap.dcMotor.get("winch");
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMoto.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        //reverse the right motor
      //  leftMotor.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop() {
        //get the values from the gamepads
        //note: pushing the stick all the way up returns -1,
        // so we need to reverse the values
        double leftY = gamepad1.left_stick_y;
        double rightY = gamepad1.right_trigger;
        //remap the value of right y stick to match what we need.
        double roY = rightY - 0.5;
        double rY = roY * 2;
        //make the control system exponential for fine detail.
        double left2 = leftY * leftY;
        double right2 = rY * rY;
        //read the buttons that control the winch and the rack.
        boolean rackPower1 = gamepad1.a;
        boolean rackPower2 = gamepad1.x;
        boolean winchPower1 = gamepad1.b;
        boolean winchPower2 = gamepad1.y;

        //read the values of the rack buttons to control rack
        if(rackPower1 == true) {
            rack.setPower(1);
        } else if(rackPower2 == true) {
            rack.setPower(-1);
        } else {
            rack.setPower(0);
        }
        //read buttons to control winch
        if(winchPower1 == true) {
            winch.setPower(1);
        } else if(winchPower2 == true) {
            winch.setPower(-1);
        } else {
            winch.setPower(0);
        }

        //read left and right gamepads to control winch
        if(leftY < 0) {
            leftMotor.setPower(-left2);
        } else {
            leftMotor.setPower(left2);
        }
        if(rY < 0) {
            rightMoto.setPower(right2);
        } else {
            rightMoto.setPower(-right2);
        }
        leftEnc = leftMotor.getCurrentPosition() / 1440;
        rightEnc = leftMotor.getCurrentPosition() / 1440;
        // Encoders currently read number of rotations from start (with negative rotations subtracting).
        //TODO add conversion to distance travled (just to test)
        telemetry.addData("LeftMotor Pwr", leftMotor.getPower());
        telemetry.addData("RightMotor Pwr", rightMoto.getPower());
        telemetry.addData("LeftMotor Pos", leftEnc);
        telemetry.addData("RightMotor Pos", rightEnc);
    }

}
