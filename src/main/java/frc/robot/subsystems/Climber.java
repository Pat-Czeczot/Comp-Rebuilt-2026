package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    
  SparkFlex climber;

  public Climber() {
    climber = new SparkFlex(Constants.ClimberID, MotorType.kBrushless);
    climber.setInverted(true);
    
  }
    public void setSpeed(double speed){
      climber.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}