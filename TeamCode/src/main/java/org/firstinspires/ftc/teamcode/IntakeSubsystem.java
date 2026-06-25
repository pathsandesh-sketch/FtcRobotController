package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Handles the intake motors.
 */
public class IntakeSubsystem {

    private DcMotor intake1;
    private DcMotor intake2;

    public void init(HardwareMap hardwareMap) {
        intake1 = hardwareMap.get(DcMotor.class, "rightIntake");
        intake2 = hardwareMap.get(DcMotor.class, "leftIntake");

        intake1.setDirection(DcMotorSimple.Direction.FORWARD);
        intake2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power) {
        intake1.setPower(power);
        intake2.setPower(power);
    }

    public double getPower() {
        return intake1.getPower();
    }
}