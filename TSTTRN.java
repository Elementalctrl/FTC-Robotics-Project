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
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.Blinker;
//import com.qualcomm.robotcore.hardware.Gyroscope;
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
@TeleOp

public class TSTTRN extends LinearOpMode {
		private Blinker control_Hub;
		private Blinker expansion_Hub_4;
		private DcMotor e0;
		private DcMotor e1;
		private Gyroscope imu;
		private DcMotor t0;
		private DcMotor t1;
		private DcMotor t2;
		private DcMotor t3;
		float x_a;
		float y_a;
		double sens;
		double strafe_var;
		boolean b_pressed;
		boolean x_pressed;
		private Servo s0;


		@Override
		public void runOpMode() {
				sens = 0.5;
				strafe_var = 1;
				control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
				expansion_Hub_4 = hardwareMap.get(Blinker.class, "Expansion Hub 4");
				e0 = hardwareMap.get(DcMotor.class, "e0");
				e1 = hardwareMap.get(DcMotor.class, "e1");
				imu = hardwareMap.get(Gyroscope.class, "imu");
				t0 = hardwareMap.get(DcMotor.class, "t0");
				t1 = hardwareMap.get(DcMotor.class, "t1");
				t2 = hardwareMap.get(DcMotor.class, "t2");
				t3 = hardwareMap.get(DcMotor.class, "t3");
				s0 = hardwareMap.get(Servo.class, "s0");

				t0.setDirection(DcMotor.Direction.FORWARD);
				t1.setDirection(DcMotor.Direction.REVERSE);
				t2.setDirection(DcMotor.Direction.REVERSE);
				t3.setDirection(DcMotor.Direction.FORWARD);
				e0.setDirection(DcMotor.Direction.REVERSE);
				e1.setDirection(DcMotor.Direction.FORWARD);

				b_pressed = false;
				x_pressed = false;

				telemetry.addData("Status", "Initialized");
			
				telemetry.update();
				// Wait for the game to start (driver presses PLAY)
				s0.setPosition(0);
				waitForStart();

				// run until the end of the match (driver presses STOP)
				while (opModeIsActive()) {
						telemetry.addData("Status", b_pressed);
						telemetry.update();
					
						x_a = gamepad1.left_stick_x;
						y_a = gamepad1.left_trigger - gamepad1.right_trigger;
						
						t0.setPower(0);
						t1.setPower(0);
						t2.setPower(0);
						t3.setPower(0);
						
						t0.setPower(-(x_a*sens)+(y_a));
						t1.setPower(-(x_a*sens)+(y_a));
						t2.setPower((x_a*sens)+(y_a));
						t3.setPower((x_a*sens)+(y_a));
						
						// t0.setPower(gamepad1.left_stick_x + gamepad1.left_stick_y);
						// t1.setPower(gamepad1.left_stick_x * (1 - 2 * gamepad1.left_trigger) + gamepad1.left_stick_y);
						// t2.setPower(-gamepad1.left_stick_x + gamepad1.left_stick_y);
						// t3.setPower(-gamepad1.left_stick_x * (1 - 2 * gamepad1.left_trigger) + gamepad1.left_stick_y);
						
						/*
						
							--------------------------------------------------------------
							--------yo Ruben ek moes kinda van die goed uit comment-------
							--------want die configuration konnie al die devices vind nie,
							--------en ek wou nie 2 ure spandeer om dit so te fix nie------
							---------------------------------------------------------------				 
						
						
						
						
						
						*/
					
					if (gamepad1.left_bumper == true) {
						t0.setPower(strafe_var); //1
						t1.setPower(-strafe_var);//-1
						t2.setPower(-strafe_var);//-1
						t3.setPower(strafe_var); //1
					} 
					
					if (gamepad1.right_bumper == true) {
						t0.setPower(-strafe_var);
						t1.setPower(strafe_var);
						t2.setPower(strafe_var);
						t3.setPower(-strafe_var);
					} 
						
					if (gamepad1.b == true && b_pressed == false) {
						b_pressed = true;
						e0.setPower(1.0);
						e1.setPower(1.0);
						sleep(500);
						e0.setPower(0);
						e1.setPower(0);
					} if (gamepad1.b == false) {
						b_pressed = false;
					}
					
					if (gamepad1.a == true) {
							s0.setPosition(0.14);
							sleep(700);
							s0.setPosition(0);
							telemetry.addData("Servo used", 0);
					}
					
						//t0.setPower()
						//t0.setDirection(DcMotor.Direction.FORWARD);
						//t1.setDirection(DcMotor.Direction.REVERSE);
						//t2.setDirection(DcMotor.Direction.REVERSE);
						//t3.setDirection(DcMotor.Direction.FORWARD);
				}
		}
}
