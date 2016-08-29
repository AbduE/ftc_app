package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Created by Gabe on 4/4/2016.
 */
public class ioModule extends LinearOpMode {
   DeviceInterfaceModule cdim;

    byte mode = 3;


    @Override
    public void runOpMode() throws InterruptedException {

        cdim = hardwareMap.deviceInterfaceModule.get("dim");
        cdim.setDigitalChannelMode(5, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelMode(4, DigitalChannelController.Mode.INPUT);

        waitForStart();
        while(opModeIsActive()){
            cdim.getDigitalChannelState(5);
            cdim.getAnalogInputValue(4);
            cdim.setPulseWidthOutputTime(0, 5);
            cdim.setPulseWidthPeriod(1, 5);
            cdim.setAnalogOutputMode(0, mode);
            cdim.setAnalogOutputFrequency(0, 150);
            cdim.setAnalogOutputVoltage(0, 1023);
            cdim.setLED(1, true);
            cdim.setDigitalChannelState(5, true);
            sleep(1000);
            cdim.setLED(1, false);
            cdim.setDigitalChannelState(5, false);
            sleep(1000);

        }
    }


}
