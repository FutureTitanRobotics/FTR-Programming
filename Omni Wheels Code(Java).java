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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; //make a new code 
import com.qualcomm.robotcore.eventloop.opmode.TeleOp; //make said new code usable with a controller
import com.qualcomm.robotcore.hardware.DcMotor; //make said code usable with motors
import com.qualcomm.robotcore.util.ElapsedTime; //make a timer 
import com.qualcomm.robotcore.util.Range; 

@TeleOp(name="Omni Linear", group="Linear Opmode")
//@Disabled
public class OmniLinear extends LinearOpMode {

   // Declare OpMode members.
   private ElapsedTime runtime = new ElapsedTime();

   DcMotor lmotor; //add four motors called lmotor(front left),rmotor(front right), fmotor(back left), bmotor(back right)
   DcMotor rmotor;
   DcMotor fmotor;
   DcMotor bmotor;


   @Override
   public void runOpMode() { //start code
       lmotor = hardwareMap.dcMotor.get("left"); //import the motors we showed earlier
       rmotor = hardwareMap.dcMotor.get("right");
       fmotor = hardwareMap.dcMotor.get("front");
       bmotor = hardwareMap.dcMotor.get("back");

       lmotor.setPower(0); //make sure all motors are stopped
       rmotor.setPower(0);
       fmotor.setPower(0);
       bmotor.setPower(0);

       telemetry.addData("Status", "Initialized"); //show on the phone that the code has started
       telemetry.update();

       // Wait for the game to start (driver presses PLAY)
        waitForStart();
       runtime.reset();
           while(opModeIsActive()) {
               //Make forward and backward movement the up and down on the right joystick of the controller.
               double fb = gamepad1.right_stick_y;
              //Make forward and backward movement the up and down on the right joystick of the controller.                
    double lr = gamepad1.right_stick_x;
               //Turn or rotate
               double turn = gamepad1.left_stick_x;

               if(turn != 0) {
                   lmotor.setPower(turn); //make the speed on the motor equal to the power on the joystick to spin left or right
                    rmotor.setPower(turn);
                   fmotor.setPower(turn);
                   bmotor.setPower(turn);
               }
               else {
                   lmotor.setPower(0); //if the joystick is not being moved, stop all motors
                    rmotor.setPower(0);
                   fmotor.setPower(0);
                   bmotor.setPower(0);
               }
               if(fb != 0) {
                   lmotor.setPower(fb); //if you want to go forward or backwards, move the motors this way
                   rmotor.setPower(-fb);
               }
               else {
                   lmotor.setPower(0); //if the joystick is not being moved, stop all motors
                   rmotor.setPower(0);
                   fmotor.setPower(0);
                   bmotor.setPower(0);
               }
               if(lr != 0) {
                   fmotor.setPower(lr); //if you want to go left or right, move the motors this way
                   bmotor.setPower(-lr);
               }
               else {
                   lmotor.setPower(0); //if the joystick is not being moved, stop all motors (again)
                   rmotor.setPower(0);
                   fmotor.setPower(0);
                   bmotor.setPower(0);
               }
           }

           lmotor.setPower(0); //all motors stopped at the end of the game
           rmotor.setPower(0);
           fmotor.setPower(0);
           bmotor.setPower(0);

       // Show the elapsed game time.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
           telemetry.update();
       }
   }