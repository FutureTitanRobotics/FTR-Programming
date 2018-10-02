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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;// Add things so we can use them later
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TankWheels", group="Linear Opmode")
//@Disabled
public class TankWheels extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor lmotor = null;// Let our motors (l and r for left and right) access the code
    private DcMotor rmotor = null;

    @Override
    public void runOpMode() {// Start the code ("INIT" is pressed)
        telemetry.addData("Status", "Initialized");// Show text on the phone that we've pressed the "INIT" button
        telemetry.update();

        // create 2 new motors (l and r for left and right)
        DcMotor lmotor = hardwareMap.get(DcMotor.class, "left_motor");
        DcMotor rmotor = hardwareMap.get(DcMotor.class, "right_motor");

        // the right motor has been reversed because when building, it is flipped over relative to the left one.
        lmotor.setDirection(DcMotor.Direction.FORWARD);
        rmotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Make forward and backward movement the up and down on the right joystick of the controller.
            double fb = gamepad1.right_stick_y;
                    //Turn or rotate with the left joystick
            double turn = gamepad1.right_stick_x;

            if(turn > 0.1) {
                lmotor.setPower(-turn); //make the speed on the motor equal to the power on the joystick to spin left or right
                rmotor.setPower(turn);
            }
            else if(turn < -0.1) {
                lmotor.setPower(turn); //make the speed on the motor equal to the power on the joystick to spin left or right
                rmotor.setPower(-turn);
            }
            else if(fb > 0.1 || fb < -0.1) {
                lmotor.setPower(fb); //if you want to go forward or backwards, move the motors the same way as the joystick says
                rmotor.setPower(fb);
            }
            else {
                lmotor.setPower(0); // If the joystick is not being moved, stop all motors
                rmotor.setPower(0);
            }
        }

        lmotor.setPower(0); // Stop all motors at the end of the game
        rmotor.setPower(0);

        // Show the elapsed game time at the end of the match
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
