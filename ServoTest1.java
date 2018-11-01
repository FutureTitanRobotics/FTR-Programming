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

    @Override
    public void runOpMode() { // Start the code ("INIT" is pressed)
        telemetry.addData("Status", "Initialized");// Show text on the phone that we've pressed the "INIT" button
        telemetry.update();
        
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

        // Show the elapsed game time at the end of the match
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
