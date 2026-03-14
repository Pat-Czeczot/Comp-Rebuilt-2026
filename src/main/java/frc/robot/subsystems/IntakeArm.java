package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase {
    
  SparkMax intakearm;

  public IntakeArm() {
    intakearm = new SparkMax(Constants.IntakeArmID, MotorType.kBrushless);
    intakearm.setInverted(true);
    
  }
    public void setSpeed(double speed){
      intakearm.set(speed);
  }
  @Override
  public void periodic() {
  }
  @Override
  public void simulationPeriodic() {
  }
}