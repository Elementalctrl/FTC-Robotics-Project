// /*
// Copyright 2025 FIRST Tech Challenge Team FTC
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
// associated documentation files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge, publish, distribute,
// sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all copies or substantial
// portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
// NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
// */
// package org.firstinspires.ftc.teamcode;
// 
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.Blinker;
// import com.qualcomm.robotcore.hardware.Servo;
// import com.qualcomm.robotcore.hardware.Gyroscope;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.DcMotorSimple;
// import com.qualcomm.robotcore.util.ElapsedTime;
// 
// /**
//  * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
//  * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
//  * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
//  * class is instantiated on the Robot Controller and executed.
//  *
//  * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
//  * It includes all the skeletal structure that all linear OpModes contain.
//  *
//  * Remove a @Disabled the on the next line or two (if present) to add this OpMode to the Driver Station OpMode list,
//  * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
//  */
// @TeleOp
// 
// public class KloutjieNuut extends LinearOpMode {
//     private Blinker control_Hub;
//     private Blinker expansion_Hub_1;
//     private DcMotor mBL;
//     private DcMotor mBR;
//     private DcMotor mFL;
//     private DcMotor mFR;
//     private DcMotor mLL;
//     private DcMotor mLR;
//     private Servo s1;
//     private Servo s2;
//     private Servo s3;
//     private Gyroscope imu_1;
//     private Gyroscope imu;
//     private double powerMultiplier;
//     private double s1pos;
//     private double s2pos;
// 
//     @Override
//     public void runOpMode() {
//         control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
//         expansion_Hub_1 = hardwareMap.get(Blinker.class, "Expansion Hub 1");
//         mBL = hardwareMap.get(DcMotor.class, "MBL");
//         mBR = hardwareMap.get(DcMotor.class, "MBR");
//         mFL = hardwareMap.get(DcMotor.class, "MFL");
//         mFR = hardwareMap.get(DcMotor.class, "MFR");
//         mLL = hardwareMap.get(DcMotor.class, "MLL");
//         mLR = hardwareMap.get(DcMotor.class, "MLR");
//         s1 = hardwareMap.get(Servo.class, "S1");
//         s2 = hardwareMap.get(Servo.class, "S2");
//         s3 = hardwareMap.get(Servo.class, "S3");
//         imu_1 = hardwareMap.get(Gyroscope.class, "imu 1");
//         imu = hardwareMap.get(Gyroscope.class, "imu");
//         powerMultiplier = 0.75;
//         s1pos = 0.3;
//         s2pos = 0.53;
//         
//         telemetry.addData("Status", "Initialized");
//         telemetry.update();
//         // Wait for the game to start (driver presses PLAY)
//         mFL.setDirection(DcMotor.Direction.FORWARD);
//         mFR.setDirection(DcMotor.Direction.REVERSE);
//         mBL.setDirection(DcMotor.Direction.REVERSE);
//         mBR.setDirection(DcMotor.Direction.FORWARD);
//         s1.setPosition(s1pos);
//         s2.setPosition(s2pos);
//         waitForStart();
// 
//         // run until the end of the match (driver presses STOP)
//         while (opModeIsActive()) {
//             telemetry.addData("Status", "Running");
//             if (gamepad1.left_stick_y < 0) {
//                 powerMultiplier = gamepad1.left_stick_y * gamepad1.left_stick_y;
//                 mFL.setPower(1 * powerMultiplier);
//                 mFR.setPower(1 * powerMultiplier);
//                 mBL.setPower(1 * powerMultiplier);
//                 mBR.setPower(1 * powerMultiplier);
//             } else if (gamepad1.left_stick_y > 0) {
//                 powerMultiplier = gamepad1.left_stick_y * gamepad1.left_stick_y;
//                 mFL.setPower(-1 * powerMultiplier);
//                 mFR.setPower(-1 * powerMultiplier);
//                 mBL.setPower(-1 * powerMultiplier);
//                 mBR.setPower(-1 * powerMultiplier);
//             } if (gamepad1.right_stick_x > 0) {
//                 powerMultiplier = gamepad1.right_stick_x * gamepad1.right_stick_x;
//                 mFL.setPower(1 * powerMultiplier / 1.5);
//                 mFR.setPower(-1 * powerMultiplier / 1.5);
//                 mBL.setPower(1 * powerMultiplier / 1.5);
//                 mBR.setPower(-1 * powerMultiplier / 1.5);
//             } else if (gamepad1.right_stick_x < 0) {
//                 powerMultiplier = gamepad1.right_stick_x * gamepad1.right_stick_x;
//                 mFL.setPower(-1 * powerMultiplier / 1.5);
//                 mFR.setPower(1 * powerMultiplier / 1.5);
//                 mBL.setPower(-1 * powerMultiplier / 1.5);
//                 mBR.setPower(1 * powerMultiplier / 1.5);
//             } if (gamepad1.right_bumper) {
//                 powerMultiplier = 0.75;
//                 mFL.setPower(1 * powerMultiplier / 1.5);
//                 mFR.setPower(-1 * powerMultiplier / 1.5);
//                 mBL.setPower(-1 * powerMultiplier / 1.5);
//                 mBR.setPower(1 * powerMultiplier / 1.5);
//             } else if (gamepad1.left_bumper) {
//                 powerMultiplier = 0.75;
//                 mFL.setPower(-1 * powerMultiplier / 1.5);
//                 mFR.setPower(1 * powerMultiplier / 1.5);
//                 mBL.setPower(1 * powerMultiplier / 1.5);
//                 mBR.setPower(-1 * powerMultiplier / 1.5);
//             } else {
//                 mFL.setPower(0);
//                 mFR.setPower(0);
//                 mBL.setPower(0);
//                 mBR.setPower(0);
//             }
//             
//             if (gamepad2.right_bumper) {
//                 if (s1.getPosition() > 0.03 && s1.getPosition() == s1pos) {
//                     s1pos -= 0.0125;
//                     sleep(100);
//                 } 
//             } else if (gamepad2.left_bumper) {
//                 if (s1.getPosition() < 0.3 && s1.getPosition() == s1pos) {
//                     s1pos += 0.0125;
//                     sleep(100);
//                 }
//             } 
//             
//             if (gamepad2.dpad_up) {
//                 if (s2.getPosition() < 0.78 && s2.getPosition() == s2pos) {
//                     s2pos += 0.0125;
//                     sleep(100);
//                 }
//             } else if (gamepad2.dpad_down) {
//                 if (s2.getPosition() > 0.53 && s2.getPosition() == s2pos) {
//                     s2pos -= 0.0125;
//                     sleep(100);
//                 }
//             }
//             
//             if (gamepad2.triangle) {
//                 s3.setPosition(1);                
//             } else if (gamepad2.cross) {
//                 s3.setPosition(0);
//             } else if (s3.getPosition() != 0.5) {
//                 s3.setPosition(0.5);
//             }
//             s1.setPosition(s1pos);
//             s2.setPosition(s2pos);
//             telemetry.update();
// 
//         }
//     }
// }
// 