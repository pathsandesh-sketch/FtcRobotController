package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Handles the ramp servos.
 */
public class RampSubsystem {

    private Servo leftRamp;
    private Servo rightRamp;

    public void init(HardwareMap hardwareMap) {
        leftRamp = hardwareMap.get(Servo.class, "leftRamp");
        rightRamp = hardwareMap.get(Servo.class, "rightRamp");

        leftRamp.setDirection(Servo.Direction.REVERSE);
        rightRamp.setDirection(Servo.Direction.FORWARD);

        setPosition(0);
    }

    public void setPosition(double position) {
        leftRamp.setPosition(position);
        rightRamp.setPosition(position);
    }

    public double getLeftPosition() {
        return leftRamp.getPosition();
    }

    public double getRightPosition() {
        return rightRamp.getPosition();
    }
}