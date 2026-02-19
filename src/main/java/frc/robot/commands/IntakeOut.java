package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeOut extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Intake intake;


  public IntakeOut(Intake subsystem1) {
    intake = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    intake.setSpeed(1 * Constants.IntakeMult);
    }

  @Override
  public void execute() {
    intake.setSpeed(1 * Constants.IntakeMult);
  }
  @Override
  public void end(boolean interrupted) {
    intake.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}