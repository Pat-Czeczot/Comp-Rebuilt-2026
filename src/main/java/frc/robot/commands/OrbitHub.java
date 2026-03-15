package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import limelight.Limelight;
import limelight.networktables.LimelightResults;
import limelight.networktables.target.AprilTagFiducial;

import java.util.Optional;

import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class OrbitHub extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private DriveSubsystem m_robotDrive;
  private Limelight limelight;
  private CommandXboxController controller;
  private PIDController distancePID = new PIDController(0.5, 0, 0);
  private PIDController anglePID = new PIDController(0.05, 0, 0);
  private final double cameraHeight = 0.762; //meters
  private final double aprilTagHeight = 1.12395; //meters
  private final double cameraAngle = 0; //degrees
  private final double radius = 2; //meters

  public OrbitHub(DriveSubsystem m_robotDrive, Limelight limelight, CommandXboxController controller) {
    this.m_robotDrive = m_robotDrive;
    this.limelight = limelight;
    this.controller = controller;
    addRequirements(m_robotDrive);
}
@Override
  public void initialize() {
    m_robotDrive.drive(0, 0, 0, true);
    //led lighting for correct orientation
    }

  @Override
  public void execute() {
    Optional<LimelightResults> results = limelight.getLatestResults();
    double ySpeed;
    if (controller.b().getAsBoolean()) { //left strafe
      ySpeed = 0.3;
    }
    else if (controller.a().getAsBoolean()) { //right strafe
      ySpeed = -0.3;
    }
    else {
      ySpeed = 0;
    }

    if (!results.isPresent()) {
      m_robotDrive.drive(0, 0, 0, true);
    }
    else {
      AprilTagFiducial tag = results.get().targets_Fiducials[0];
      double distance = (aprilTagHeight - cameraHeight) / Math.tan(Math.toRadians(cameraAngle + tag.ty));
      double xSpeed = MathUtil.clamp(distancePID.calculate(distance, radius), -1, 1);
      double rotation = MathUtil.clamp(anglePID.calculate(tag.tx, 0), -1, 1);
      
      m_robotDrive.drive(xSpeed, ySpeed, rotation, false);
      //if distance and rotation within tolerance, turn on led (0.1m and 2 deg?)
    }
  }
  @Override
  public void end(boolean interrupted) {
    m_robotDrive.drive(0, 0, 0, true);
    
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}