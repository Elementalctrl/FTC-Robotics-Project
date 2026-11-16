/*
Copyright 2025 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.Math;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this OpMode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class DRVTRN extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_1;
    private DcMotor t0;
    private DcMotor t1;
    private DcMotor t2;
    private DcMotor t3;
    private Gyroscope imu_1;
    private Gyroscope imu;
    
    /*
    V1
    public void set_base_movement(double jstck_x, double jstck_y, double trig_l, double trig_r) {
        t0.setPower(trig_r * (-jstck_x + jstck_y));
        t1.setPower(trig_r * (-jstck_x * (1 - 2* trig_l) + jstck_y));
        t2.setPower(trig_r * (jstck_x + jstck_y));
        t3.setPower(trig_r * (-jstck_x * (-1 + 2* trig_l) + jstck_y));
    }
    */
    
    public void set_base_movement(double forward, double side, double strafe) {
        if (strafe == 0) {
            t0.setPower(forward * (1 - side ));
            t1.setPower(forward * (1 - side));
            t2.setPower(forward * (1 + side));
            t3.setPower(forward * (1 + side));
        } else {
            t0.setPower(strafe);
            t1.setPower(-strafe);
            t2.setPower(-strafe);
            t3.setPower(strafe);
        }
        
    }


    @Override
    public void runOpMode() {
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_1 = hardwareMap.get(Blinker.class, "Expansion Hub 1");
        t0 = hardwareMap.get(DcMotor.class, "T0");
        t1 = hardwareMap.get(DcMotor.class, "T1");
        t2 = hardwareMap.get(DcMotor.class, "T2");
        t3 = hardwareMap.get(DcMotor.class, "T3");
        imu_1 = hardwareMap.get(Gyroscope.class, "imu 1");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        
        t0.setDirection(DcMotor.Direction.REVERSE);
        t1.setDirection(DcMotor.Direction.REVERSE);
        t2.setDirection(DcMotor.Direction.REVERSE);
        t3.setDirection(DcMotor.Direction.FORWARD);
        

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.addData("t0", -gamepad1.left_stick_x+gamepad1.left_stick_y);
            telemetry.addData("t1", -gamepad1.left_stick_x*(1-2*gamepad1.right_trigger)+gamepad1.left_stick_y);
            telemetry.addData("t2", gamepad1.left_stick_x+gamepad1.left_stick_y);
            telemetry.addData("t3", -gamepad1.left_stick_x*(-1+2*gamepad1.right_trigger)+gamepad1.left_stick_y);
            telemetry.update();
            //set_base_movement(gamepad1.left_trigger - gamepad1.right_trigger, - gamepad1.left_stick_x, - gamepad1.right_stick_x);
            
            //V1
            //set_base_movement(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger, gamepad1.right_trigger);
            //set_base_movement(gamepad1.left_stick_x * Math.abs(gamepad1.left_stick_x), gamepad1.left_stick_y * Math.abs(gamepad1.left_stick_y), gamepad1.right_trigger);
            
            
            t0.setPower(-gamepad1.left_stick_x+gamepad1.left_stick_y);
            t1.setPower(-gamepad1.left_stick_x*(1-2*gamepad1.right_trigger)+gamepad1.left_stick_y);
            t2.setPower(gamepad1.left_stick_x+gamepad1.left_stick_y);
            t3.setPower(-gamepad1.left_stick_x*(-1+2*gamepad1.right_trigger)+gamepad1.left_stick_y);
            
        }
    }
}
