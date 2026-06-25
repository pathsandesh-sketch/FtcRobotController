package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "2 gamepad bot")
public class two_gamepad_bot extends OpMode {

    private final DriveSubsystem drive = new DriveSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();
    private final ShooterSubsystem shooter = new ShooterSubsystem();
    private final LiftSubsystem lift = new LiftSubsystem();
    private final RampSubsystem ramp = new RampSubsystem();

    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        lift.init(hardwareMap);
        ramp.init(hardwareMap);

        telemetry.addLine("IMU Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {

        // ---- Drive (gamepad1) ----
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        if (gamepad1.dpad_down) {
            drive.resetYaw();
        }

        drive.drive(y, x, rx);

        // ---- Ramp (gamepad2) ----
        if (gamepad2.b) {
            ramp.setPosition(0.22);
        }

        if (gamepad2.y) {
            ramp.setPosition(0);
        }

        // ---- Intake (gamepad1 triggers) ----
        intake.setPower(-gamepad1.left_trigger + gamepad1.right_trigger);

        // ---- Shooter (gamepad2) ----
        if (gamepad2.a) {
            shooter.setPower(1);
        }

        if (gamepad2.x) {
            shooter.setPower(0.75);
        }

        if (gamepad2.y) {
            shooter.setPower(0);
        }

        // ---- Lift (gamepad1) ----
        if (gamepad1.a) {
            lift.setPosition(0);
        }

        if (gamepad1.x) {
            lift.setPosition(0.5);
        }

        // ---- Telemetry ----
        telemetry.addData("Heading (deg)", drive.getHeadingDegrees());
        telemetry.addData("Intake Speed", intake.getPower());
        telemetry.addData("Ramp Left position", ramp.getLeftPosition());
        telemetry.addData("Ramp Right position", ramp.getRightPosition());
        telemetry.addData("Lift Left", lift.getLeftPosition());
        telemetry.addData("Lift Right", lift.getRightPosition());
        telemetry.addData("Shooter 1", shooter.getPower());
        telemetry.addData("Shooter 2", shooter.getPower2());
        telemetry.update();
    }
}