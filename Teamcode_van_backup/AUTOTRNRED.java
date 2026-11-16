/*
Copyright 2026 FIRST Tech Challenge Team FTC

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
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

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
@Autonomous

public class AUTOTRNRED extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_4;
    private DcMotor trw;
    private Gyroscope imu;
    private DcMotor t0;
    private DcMotor t1;
    private DcMotor t2;
    private DcMotor t3;
    private DcMotor e0;
    private DcMotor e1;
    private Servo s0;

    public void move_robo(double x_power, double y_power, double s_power, long time) {
        t0.setPower(x_power + y_power);
        t1.setPower(x_power * (1 - 2 * s_power) + y_power);
        t2.setPower((-x_power + y_power));
        t3.setPower((x_power * (-1 + 2 * s_power) + y_power));
        sleep(time);
        t0.setPower(0);
        t1.setPower(0);
        t2.setPower(0);
        t3.setPower(0);
    }
    
    public void throw_ball() {
        e0.setPower(1);
        e1.setPower(1);
        sleep(500);
        e0.setPower(0);
        e1.setPower(0);
    }
    
    public void reload() {
        s0.setPosition(0.14);
        sleep(700);
        s0.setPosition(0);
    }

    @Override
    public void runOpMode() {
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_4 = hardwareMap.get(Blinker.class, "Expansion Hub 4");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        t0 = hardwareMap.get(DcMotor.class, "t0");
        t1 = hardwareMap.get(DcMotor.class, "t1");
        t2 = hardwareMap.get(DcMotor.class, "t2");
        t3 = hardwareMap.get(DcMotor.class, "t3");
        e0 = hardwareMap.get(DcMotor.class, "e0");
        e1 = hardwareMap.get(DcMotor.class, "e1");
        s0 = hardwareMap.get(Servo.class, "s0");
        
        t0.setDirection(DcMotor.Direction.REVERSE);
        t1.setDirection(DcMotor.Direction.FORWARD);
        t2.setDirection(DcMotor.Direction.FORWARD);
        t3.setDirection(DcMotor.Direction.REVERSE);
        e0.setDirection(DcMotor.Direction.REVERSE);
        e1.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        
        s0.setPosition(0);
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            
            move_robo(0,0.3,0,4500);//forward
            move_robo(0.3,0,0,1200);//turn right 45 deg
            //move_robo(0,0.3,0,700);
            sleep(1300);
            for (int i = 0; i < 6; i++) { //throw balls
                reload();
                sleep(1300);
                throw_ball();
                sleep(1300);
                move_robo(0,0.3,0,100);
                sleep(100);
                move_robo(0,-0.3,0,1500);
            }
            move_robo(-0.5,0,1,1000);
            /*move_robo(0,0.3,0,700); //Move robot
            move_robo(-0.3,0,0,1475);
            move_robo(0,-0.3,0,2000);*/
            
            
           /* reload();
            sleep(1000);
            throw_ball();
            sleep(1000);
            reload();
            sleep(1000);
            throw_ball();
            sleep(1500);
            reload();
            sleep(1500);
            throw_ball();*/
            /*move_robo(0.3,0.3,0,2000);//turn left 45 deg
            move_robo(0,0.3,0,2000);//backward*/
            
            
            /*move_robo(0,-0.3,0,2000);//back untill lines meet
            reload();
            throw_ball();
            reload();
            throw_ball();
            reload();
            throw_ball();
            
            move_robo(0,0.3,0,1000);//forward
            move_robo(0.3,0.3,0,1000);//turn 45 deg right
            move_robo(0,0.3,0,1000);//move right to push balls 
            //refill robot*/
            requestOpModeStop();
        }
    }
}
