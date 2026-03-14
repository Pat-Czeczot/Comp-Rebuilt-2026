package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeArm;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeArmUp extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private IntakeArm intakearm;

  public IntakeArmUp(IntakeArm subsystem1) {
    intakearm = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    intakearm.setSpeed(1 * Constants.IntakeArmMult);
    }

  @Override
  public void execute() {
    intakearm.setSpeed(1 * Constants.IntakeArmMult);
  }
  @Override
  public void end(boolean interrupted) {
    intakearm.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}