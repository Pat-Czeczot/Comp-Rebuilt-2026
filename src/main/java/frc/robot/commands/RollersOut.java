package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Rollers;
import edu.wpi.first.wpilibj2.command.Command;

public class RollersOut extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Rollers rollers;


  public RollersOut(Rollers subsystem1) {
    rollers = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    rollers.setSpeed(-1 * Constants.RollersMult);
    }

  @Override
  public void execute() {
    rollers.setSpeed(-1 * Constants.RollersMult);
  }
  @Override
  public void end(boolean interrupted) {
    rollers.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}