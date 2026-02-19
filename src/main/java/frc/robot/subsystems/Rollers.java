package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Rollers extends SubsystemBase {
    
  SparkFlex rollers;

  public Rollers() {
    rollers = new SparkFlex(Constants.RollersID, MotorType.kBrushless);
    rollers.setInverted(true);
    
  }
    public void setSpeed(double speed){
      rollers.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}