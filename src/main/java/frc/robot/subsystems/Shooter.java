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

  public Shooter() {
    shooter1 = new SparkFlex(Constants.Shooter1ID, MotorType.kBrushless);
    shooter1.setInverted(true);
    shooter2 = new SparkFlex(Constants.Shooter2ID, MotorType.kBrushless);
    shooter2.setInverted(true);
    encoder = shooter1.getExternalEncoder();
    encoder = shooter2.getExternalEncoder();

    pid = new PIDController(0.01, 0, 0);

  }
    public void setSpeed(double speed){
      shooter1.set(speed);
      shooter2.set(-speed);

      setSpeed(MathUtil.clamp(pid.calculate(encoder.getPosition(), speed), 0.8, 0.9));

  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}