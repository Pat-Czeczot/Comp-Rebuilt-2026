package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    
  SparkFlex feeder1;
  SparkFlex feeder2;

  public Feeder() {
    feeder1 = new SparkFlex(Constants.Feeder1ID, MotorType.kBrushless);
    feeder1.setInverted(false);
    feeder2 = new SparkFlex(Constants.Feeder2ID, MotorType.kBrushless);
    feeder2.setInverted(true);
  }
    public void setSpeed(double speed){
      feeder1.set(speed);
      feeder2.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}