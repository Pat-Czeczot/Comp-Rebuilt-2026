package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
  SparkFlex intake;

  public Intake() {
    intake = new SparkFlex(Constants.IntakeID, MotorType.kBrushless);
    intake.setInverted(true);
    
  }
    public void setSpeed(double speed){
      intake.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}