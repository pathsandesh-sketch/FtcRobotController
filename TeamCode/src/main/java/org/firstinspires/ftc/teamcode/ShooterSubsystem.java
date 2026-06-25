package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Handles the shooter motors and hood servo.
 */
public class ShooterSubsystem {

    private DcMotor shooter1;
    private DcMotor shooter2;
    private Servo hoodServo;

    public void init(HardwareMap hardwareMap) {
        shooter1 = hardwareMap.get(DcMotor.class, "leftShooter");
        shooter2 = hardwareMap.get(DcMotor.class, "rightShooter");
        hoodServo = hardwareMap.get(Servo.class, "hoodServo");

        shooter1.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power) {
        shooter1.setPower(power);
        shooter2.setPower(power);
    }

    public double getPower() {
        return shooter1.getPower();
    }

    public double getPower2() {
        return shooter2.getPower();
    }

    /** Set the hood servo position (0.0 - 1.0). Not wired up to a control yet. */
    public void setHoodPosition(double position) {
        hoodServo.setPosition(position);
    }
}