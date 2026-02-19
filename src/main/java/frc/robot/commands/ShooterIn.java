package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShooterIn extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Shooter shooter;

  public ShooterIn(Shooter subsystem1) {
    shooter = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    shooter.setSpeed(1 * Constants.ShootersMult);
    }

  @Override
  public void execute() {
    shooter.setSpeed(1 * Constants.ShootersMult);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.setSpeed(0);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}