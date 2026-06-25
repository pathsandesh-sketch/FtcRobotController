package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Handles the lift servos.
 */
public class LiftSubsystem {

    private Servo leftLift;
    private Servo rightLift;

    public void init(HardwareMap hardwareMap) {
        leftLift = hardwareMap.get(Servo.class, "leftLift");
        rightLift = hardwareMap.get(Servo.class, "rightLift");

        leftLift.setDirection(Servo.Direction.FORWARD);
        rightLift.setDirection(Servo.Direction.REVERSE);

        setPosition(0);
    }

    public void setPosition(double position) {
        leftLift.setPosition(position);
        rightLift.setPosition(position);
    }

    public double getLeftPosition() {
        return leftLift.getPosition();
    }

    public double getRightPosition() {
        return rightLift.getPosition();
    }
}