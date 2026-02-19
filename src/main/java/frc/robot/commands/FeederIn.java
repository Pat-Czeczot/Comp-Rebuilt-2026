package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.Command;

public class FeederIn extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Feeder feeder;


  public FeederIn(Feeder subsystem1) {
    feeder = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    feeder.setSpeed(-1 * Constants.FeederMult);
    }

  @Override
  public void execute() {
    feeder.setSpeed(-1 * Constants.FeederMult);
  }
  @Override
  public void end(boolean interrupted) {
    feeder.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}