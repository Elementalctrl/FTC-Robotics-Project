/*
Copyright 2026 FIRST Tech Challenge Team 17133

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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file contains a minimal example of an iterative (Non-Linear) "OpMode". An OpMode is a
 * 'program' that runs in either the autonomous or the TeleOp period of an FTC match. The names
 * of OpModes appear on the menu of the FTC Driver Station. When an selection is made from the
 * menu, the corresponding OpMode class is instantiated on the Robot Controller and executed.
 *
 * Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
 * Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
 * added to the Driver Station.
 */
@TeleOp

public class DriveTrain extends OpMode {
	/* Declare OpMode members. */
	private Blinker control_Hub;
	private Blinker expansion_Hub_4;
	DcMotor t0;
	DcMotor t1;
	DcMotor t2;
	DcMotor t3;
	DcMotor e0;
	Servo s0;
	float lTr_1;
	float rTr_1;
	float y_a1;//The y value of the "a" stick of the first controlle //The forward and back value 
	float x_a1;//The x value of the "a" stick of the first conntroller //The strafe value
	float x_b1;//The x value of the "b" stick of the first conntroller //The turn value


	@Override
	public void init() {
		control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
		expansion_Hub_4 = hardwareMap.get(Blinker.class, "Expansion Hub 4");
		t0 = hardwareMap.get(DcMotor.class, "t0"); 
		t1 = hardwareMap.get(DcMotor.class, "t1");
		t2 = hardwareMap.get(DcMotor.class, "t2");
		t3 = hardwareMap.get(DcMotor.class, "t3");
		e0 = hardwareMap.get(DcMotor.class, "e0");
		s0 = hardwareMap.get(Servo.class, "s0");
		
		t0.setDirection(DcMotor.Direction.REVERSE);
		t1.setDirection(DcMotor.Direction.FORWARD);
		t2.setDirection(DcMotor.Direction.REVERSE);
		t3.setDirection(DcMotor.Direction.FORWARD);
		
		e0.setDirection(DcMotor.Direction.FORWARD);
		
		telemetry.addData("Status", "Initialized");
		telemetry.update();
		
		s0.setPosition(0);
	}

	/*
	 * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
	 */
	@Override
	public void init_loop() {
	}

	/*
	 * Code to run ONCE when the driver hits PLAY
	 */
	@Override
	public void start() {

	}

	/*
	 * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
	 */
	@Override
	public void loop() {
		y_a1 = -gamepad1.left_stick_y; //1 is stick down
		x_a1 = gamepad1.left_stick_x;
		x_b1 = gamepad1.right_stick_x;

		if (y_a1 > 0.99){ //The contollers do not give a 1 when pressed all th way to the right
			//telemetry.addLine("Rounded y_a1 to 1");
			y_a1 = 1;
		}
		
		if (x_a1 > 0.99){ //The contollers do not give a 1 when pressed all th way to the right
			//telemetry.addLine("Rounded x_a1 to 1");
			x_a1 = 1;
		}
		
		if (x_b1 > 0.99){ //The contollers do not give a 1 when pressed all th way to the right
			//telemetry.addLine("Rounded x_b1 to 1");
			x_b1 = 1;
		}

		//Info
		telemetry.addData("y_a1", y_a1);
		telemetry.addData("x_a1", x_a1);
		telemetry.addData("x_b1", x_b1);
		
		telemetry.addData("t0", y_a1-x_a1+x_b1);//Forward-Strave+Turn
		telemetry.addData("t1", y_a1+x_a1-x_b1);//Forward+Strave-Turn
		telemetry.addData("t2", y_a1+x_a1-x_b1);//Forward+Strave-Turn
		telemetry.addData("t3", y_a1-x_a1+x_b1);//Forward-Strave+Turn

		//Set the power of each motor. auto caps at 1 or -1
		t0.setPower(y_a1-x_a1+x_b1);
		t1.setPower(y_a1+x_a1-x_b1);
		t2.setPower(y_a1+x_a1-x_b1);
		t3.setPower(y_a1-x_a1+x_b1);
	}

	/*
	 * Code to run ONCE after the driver hits STOP
	 */
	@Override
	public void stop() {

	}
}
