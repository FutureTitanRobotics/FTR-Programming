/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TankDriveAndArm", group="Linear Opmode")
//@Disabled
public class TankDriveAndArm extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //private DcMotor lmotor = null; // looking at the First sample programs, these are irrelevant. I don't want to remove them in case they are used later.
    //private DcMotor rmotor = null;

    DcMotor lmotor;
    DcMotor rmotor;
    DcMotor arm;
    DcMotor arm2;
    //DcMotor hook;
    Servo armservo;
    Servo armservo2;
    Servo grabber;
    Servo grabber2;
    Servo marker;

    @Override
    public void runOpMode() {// Start the code ("INIT" is pressed)
        telemetry.addData("Status", "Initialized");// Show text on the phone that we've pressed the "INIT" button
        telemetry.update();

        // create 2 new motors (l and r for left and right)
        lmotor = hardwareMap.get(DcMotor.class, "left_drive");
        rmotor = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        //hook = hardwareMap.get(DcMotor.class, "hook");
        armservo = hardwareMap.servo.get("armservo");
        armservo2 = hardwareMap.servo.get("armservo2");
        grabber = hardwareMap.servo.get("grabber");
        grabber2 = hardwareMap.servo.get("grabber2");
        marker = hardwareMap.servo.get("marker");

        double servopos = 0.0;
        double servopos2 = 0.0;

        armservo2.setDirection(Servo.Direction.REVERSE);
        grabber2.setDirection(Servo.Direction.REVERSE);

        // the right motor has been reversed because when building, it is flipped over relative to the left one.
        lmotor.setDirection(DcMotor.Direction.REVERSE);
        /**rmotor.setDirection(DcMotor.Direction.FORWARD);
         * arm.setDirection(DcMotor.Direction.FORWARD);
         */

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Make forward and backward movement the up and down on the right joystick of the controller.
            double left = gamepad1.left_stick_y;
            //Turn or rotate with the left joystick
            double right = gamepad1.right_stick_y;
            float lt = gamepad2.left_trigger;
            float rt = gamepad2.right_trigger;
            
            if (left < 0.1 || left > -0.1) { //set the left motor to the power of the left joystick
                lmotor.setPower(left * 0.75);
            }
            if (right < 0.1 || right > -0.1) { //set the right motor to the power of the righ joystick
                rmotor.setPower(right * 0.75);
            }
            else {
                lmotor.setPower(0); // If the joysticks are not being moved, stop all motors
                rmotor.setPower(0);
            }

            if (gamepad2.a) { // when the a button is pressed, move the arm forward
                arm.setPower(-0.4);
                arm2.setPower(0.4);
                arm.setPower(0);
                arm2.setPower(0);
            }
            else if (gamepad2.b) { // when the b button is pressed, move the arm backwards
                arm.setPower(0.4);
                arm2.setPower(-0.4);
                arm.setPower(0);
                arm2.setPower(0);
            }
            else if (!gamepad2.a) { // if a and b aren't being used, don't move the arms.
                arm.setPower(0);
                arm2.setPower(0);
            }
            else if (!gamepad2.b) {
                arm.setPower(0);
                arm2.setPower(0);
            }
            if (gamepad2.dpad_up) { // when the a button is pressed, move the arm forward very quickly.
                arm.setPower(0.5);
                arm2.setPower(-0.5);
                /**sleep(3);
                 * arm.setPower(0);
                 * arm2.setPower(0);
                 */
            }
            if (gamepad2.dpad_down) { // when the a button is pressed, move the arm forward very quickly.
                arm.setPower(-0.5);
                arm2.setPower(0.5);
                /**sleep(3);
                 * arm.setPower(0);
                 * arm2.setPower(0);
                 */
            }

            //always set the grabbers to the press value of the servos
            grabber.setPosition(lt);
            grabber2.setPosition(rt);
            //show the value of the grabbers rotation
            telemetry.addData("leftGrabber", grabber.getPosition());
            telemetry.addData("rightGrabber", grabber2.getPosition());

            if (servopos > 1) {
                servopos = 1;
                servopos2 = 1;
            } else if (servopos < 0) {
                servopos = 0;
                servopos2 = 0;
            }

            double step = 0.01;
            if (gamepad2.y) { //if y is pressed, make the elbow move up
                servopos += step;
                armservo.setPosition(servopos);
                servopos2 += step;
                armservo2.setPosition(servopos2);
                sleep(10);
            }
            else if (gamepad2.x) { //if x is pressed, make the elbow move down
                servopos -= step;
                armservo.setPosition(servopos);
                servopos2 -= step;
                armservo2.setPosition(servopos2);
                sleep(10);
            }

            if (gamepad2.dpad_left) {
                marker.setPosition(0.65);
                sleep(1700);
                marker.setPosition(0);
            }

/**            if (gamepad2.left_bumper) {
                hook.setPower(0.6);
                hook.setPower(0);
            }
            if (gamepad2.right_bumper) {
                hook.setPower(-0.6);
                hook.setPower(0);
            }
*/
            telemetry.addData("Servo1 ", servopos);
            telemetry.addData("Servo2 ", servopos2);
            telemetry.update();
        }

        lmotor.setPower(0.0); // Stop all motors at the end of the game
        rmotor.setPower(0.0);
        arm.setPower(0.0);

        // Show the elapsed game time at the end of the match
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}