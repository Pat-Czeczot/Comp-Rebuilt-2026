package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimberUp extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Climber climber;


  public ClimberUp(Climber subsystem1) {
    climber = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    climber.setSpeed(1 * Constants.ClimberMult);
    }

  @Override
  public void execute() {
    climber.setSpeed(1 * Constants.ClimberMult);
  }
  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}