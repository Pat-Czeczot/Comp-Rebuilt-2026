package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterFullPower extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Shooter shooter;


  public ShooterFullPower(Shooter subsystem1) {
    shooter = subsystem1;
    addRequirements(subsystem1);
}
@Override
  public void initialize() {
    shooter.setSpeed(-1 * Constants.ShooterFullPowerMult);
    }

  @Override
  public void execute() {
    shooter.setSpeed(-1 * Constants.ShooterFullPowerMult);
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