package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.HighFeeder;

public class HighFeederOut extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private HighFeeder highfeeder;

  public HighFeederOut(HighFeeder subsystem1) {
    highfeeder = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    highfeeder.setSpeed(1 * Constants.HighFeederMult);
    }

  @Override
  public void execute() {
    highfeeder.setSpeed(1 * Constants.HighFeederMult);
  }
  @Override
  public void end(boolean interrupted) {
    highfeeder.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}