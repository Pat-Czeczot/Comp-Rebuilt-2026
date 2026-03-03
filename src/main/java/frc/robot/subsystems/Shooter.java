package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  SparkFlex shooter1;
  SparkFlex shooter2;
  private PIDController pid;
  private RelativeEncoder encoder;
  private double targetSpeed = 0.0; // store the target so periodic can run the pid

  public Shooter() {
    shooter1 = new SparkFlex(Constants.Shooter1ID, MotorType.kBrushless);
    shooter1.setInverted(true);
    shooter2 = new SparkFlex(Constants.Shooter2ID, MotorType.kBrushless);
    shooter2.setInverted(true);

    encoder = shooter1.getExternalEncoder(); // only assign once using shooter1s encoder

    pid = new PIDController(0.01, 0, 0);
  }

  // setspeed now just stores the target
  public void setSpeed(double speed) {
    targetSpeed = speed;
  }

  @Override
  public void periodic() {
    double currentRPM = encoder.getVelocity();

    // pid calculates correction based on actual RPM vs the target RPM
    double pidOutput = pid.calculate(currentRPM, targetSpeed);

    // clamp the output to a safe range
    double clampedOutput = MathUtil.clamp(pidOutput, -1.0, 1.0);

    shooter1.set(clampedOutput);
    shooter2.set(-clampedOutput);
  }

  @Override
  public void simulationPeriodic() {
  }
}