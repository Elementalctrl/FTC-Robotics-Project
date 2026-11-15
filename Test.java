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
import java.math.RoundingMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
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

public class Test extends LinearOpMode {
		private Blinker control_Hub;
		private Blinker expansion_Hub_4;
		DcMotor t0;
		DcMotor t1;
		DcMotor e0;
		//Servo s0;
		float lTr_1;
		float rTr_1;
		float x_a1;//the x value of the "a" stick of the first conntroller
		float x_b1;

		@Override
		public void runOpMode() {
			control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
			expansion_Hub_4 = hardwareMap.get(Blinker.class, "Expansion Hub 4");
			t0 = hardwareMap.get(DcMotor.class, "t0"); 
			t1 = hardwareMap.get(DcMotor.class, "t1");
			e0 = hardwareMap.get(DcMotor.class, "e0");
			//s0 = hardwareMap.get(Servo.class, "s0");

			
			t0.setDirection(DcMotor.Direction.FORWARD);
			t1.setDirection(DcMotor.Direction.FORWARD);
			e0.setDirection(DcMotor.Direction.FORWARD);
			
			telemetry.addData("Status", "Initialized");
			telemetry.update();
			
			//s0.setPosition(0);
			
			waitForStart();			// Wait for the game to start (driver presses PLAY)
			
			while (opModeIsActive()) {
				
				x_a1 = gamepad1.left_stick_x;
				x_b1 = gamepad1.right_stick_x;
				lTr_1 = gamepad1.left_trigger;
				rTr_1 = gamepad1.right_trigger;
				
				if (x_a1 > 0.99){ //The contollers do not give a 1 when pressed all th way to the right
					//telemetry.addLine("Rounded x_a1 to 1");
					x_a1 = 1;
				}
				
				if (x_b1 > 0.99){ //The contollers do not give a 1 when pressed all th way to the right
					//telemetry.addLine("Rounded x_b1 to 1");
					x_b1 = 1;
				}
				

				telemetry.addData("x_a1", x_a1);
				telemetry.addData("x_b1", x_b1);
				telemetry.addData("lTrg", lTr_1);
				telemetry.addData("rTrg", rTr_1);
				telemetry.update();
				t0.setPower(1);
				t1.setPower(0);
				e0.setPower(1);
				//s0.setPosition(0.5+x_b1);
			}
		}
}