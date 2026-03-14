package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HighFeeder extends SubsystemBase {
    
  SparkFlex highfeeder;

  public HighFeeder() {
    highfeeder = new SparkFlex(Constants.HighFeederID, MotorType.kBrushless);
    highfeeder.setInverted(true);
    
  }
    public void setSpeed(double speed){
      highfeeder.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}