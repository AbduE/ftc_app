package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabe R. (marsfan) on 4/1/2016.
 */
public class roperAutom extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor rack;
    DcMotor winch;

    @Override
    public void init(){
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMoto");
        rack = hardwareMap.dcMotor.get("rack");
        winch = hardwareMap.dcMotor.get("winch");
    }

    @Override
    public void loop(){

    }
}
