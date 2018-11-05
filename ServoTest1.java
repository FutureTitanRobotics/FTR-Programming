package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.quallcom.robotcore.hardware.Servo;

@TeleOp(name"Servo Test 1", group="Linear Opmode")
@Disabled
public class "Servo Test 1" extends LinearOpMode { 

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor lmotor;
    DcMotor rmotor;
    Servo armservo;
    
    @Override
    public void runOpMode() { // Start the code ("INIT" is pressed)
        telemetry.addData("Status", "Initialized");// Show text on the phone that we've pressed the "INIT" button
        telemetry.update();
        
        lmotor = hardwareMap.get(DcMotor.class, "left_drive");
        rmotor = hardwareMap.get(DcMotor.class, "right_drive");
        armservo = hardwareMap.servo.get("armservo");
        
        double position = 1;
        double servopos = 1;
        
         lmotor.setDirection(DcMotor.Direction.REVERSE);
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Make forward and backward movement the up and down on the right joystick of the controller.
            double left = gamepad1.left_stick_y;
            //Turn or rotate with the left joystick
            double right = gamepad1.right_stick_y;
            
            if (left < 0.1 || left > -0.1) {//set the left motor to the power of the left joystick
                lmotor.setPower(left);
            }
            if (right < 0.1 || right > -0.1) {//set the right motor to the power of the righ joystick
                rmotor.setPower(right);
            }
            else {
                lmotor.setPower(0); // If the joysticks are not being moved, stop all motors
                rmotor.setPower(0);
            }
            
            else if (gamepad2.b) {
                armservo.setPosition(10)
            }
            else if (!gamepad2.a && !gamepad2.b) {
                armservo.setPosition(0)
            }
        }

        // Show the elapsed game time at the end of the match
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
