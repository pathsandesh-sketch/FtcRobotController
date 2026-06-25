package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
//org/firstinspires/ftc/teamcode/drivetrain.java
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "2 gamepad bot")
public class two_gamepad_bot_0 extends OpMode {

    private DcMotor intake1;
    private Servo Rservo1;
    private Servo Rservo2;
    private Servo servo1;
    private Servo servo2;
    private Servo Hservo;
    private DcMotor shooter1;
    private DcMotor shooter2;
    private IMU imu;
    private DcMotor frontLeft;
    private DcMotor intake2;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);


       servo1 = hardwareMap.get(Servo.class, "leftLift");
        servo2 = hardwareMap.get(Servo.class, "rightLift");

        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);

        intake1 = hardwareMap.get(DcMotor.class, "rightIntake");
        intake2 = hardwareMap.get(DcMotor.class, "leftIntake");

        shooter1 = hardwareMap.get(DcMotor.class, "leftShooter");
        shooter2 = hardwareMap.get(DcMotor.class, "rightShooter");
        Hservo = hardwareMap.get(Servo.class, "hoodServo");

        shooter1.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter2.setDirection(DcMotorSimple.Direction.REVERSE);

        Rservo1 = hardwareMap.get(Servo.class,"leftRamp");
        Rservo2 = hardwareMap.get(Servo.class, "rightRamp");

        intake1.setDirection(DcMotorSimple.Direction.FORWARD);
        intake2.setDirection(DcMotorSimple.Direction.REVERSE);

        Rservo1.setDirection(Servo.Direction.REVERSE);
        Rservo2.setDirection(Servo.Direction.FORWARD);

        Rservo1.setPosition(0);
        Rservo2.setPosition(0);

        servo1.setPosition(0);
        servo2.setPosition(0);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );

        imu.initialize(parameters);

        telemetry.addLine("IMU Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        if (gamepad1.dpad_down) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        //rotX = rotX * 1.1;


        double denominator = Math.max(
                Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx),
                1
        );

        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;


        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);


        if(gamepad2.b){
            Rservo1.setPosition(0.22);
            Rservo2.setPosition(0.22);
        }

        if(gamepad2.y){
            Rservo1.setPosition(0);
            Rservo2.setPosition(0);
        }

        intake1.setPower(-gamepad1.left_trigger + gamepad1.right_trigger);
        intake2.setPower(-gamepad1.left_trigger + gamepad1.right_trigger);
//HEllo
        if(gamepad2.a){
            shooter1.setPower(1);
            shooter2.setPower(1);
        }

        if(gamepad2.x){
            shooter1.setPower(0.75);
            shooter2.setPower(0.75);
        }
        if(gamepad2.y){
            shooter1.setPower(0);
            shooter2.setPower(0);
        }


        telemetry.addData("intake1 Speed", intake1.getPower());

        telemetry.addData("Servo position", Rservo1.getPosition());

        if(gamepad1.a){
           servo1.setPosition(0);
           servo2.setPosition(0);
        }

        if(gamepad1.x){
          servo1.setPosition(0.5);
          servo2.setPosition(0.5);

        }

        telemetry.addData("Heading (deg)", Math.toDegrees(botHeading));
        telemetry.addData("intake1 Speed", intake1.getPower());
        telemetry.addData("RServo1 position", Rservo1.getPosition());
        telemetry.addData("Rservo2 position", Rservo2.getPosition());
        telemetry.addData("Servo1", servo1.getPosition());
        telemetry.addData("Servo2", servo2.getPosition());
        telemetry.addData("Shooter 1", shooter1.getPower());
        telemetry.addData("Shooter 2", shooter2.getPower());
        telemetry.update();
    }
}
