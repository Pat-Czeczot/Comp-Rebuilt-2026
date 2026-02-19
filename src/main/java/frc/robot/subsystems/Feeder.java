package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    
  SparkFlex feeder;

  public Feeder() {
    feeder = new SparkFlex(Constants.FeederID, MotorType.kBrushless);
    feeder.setInverted(true);
    
  }
    public void setSpeed(double speed){
      feeder.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}