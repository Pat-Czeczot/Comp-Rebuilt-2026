package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
  SparkFlex shooter1;
  SparkFlex shooter2;

  public Shooter() {
    shooter1 = new SparkFlex(Constants.Shooter1ID, MotorType.kBrushless);
    shooter1.setInverted(true);
    shooter2 = new SparkFlex(Constants.Shooter2ID, MotorType.kBrushless);
    shooter2.setInverted(true);
  }
    public void setSpeed(double speed){
      shooter1.set(speed);
      shooter2.set(-speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}