package frc.robot.commands;

import java.io.Console;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.DriveSubsystem;

public class AlignToReefTagRelative extends Command {
  private PIDController xController, yController, rotController;
  private boolean isRightScore;
  private Timer dontSeeTagTimer, stopTimer;
  private DriveSubsystem driveBase;
  private double tagID = -1; 

 //isRightScore - each side of the reef has two scoring pipes this boolean is used to choose if you want to go to the right pipe if it is true or the left pipe if its false 
  public AlignToReefTagRelative(boolean isRightScore, DriveSubsystem drivebase) {
    xController = new PIDController(Constants.X_REEF_ALIGNMENT_P, 0.0, 0);  // Vertical movement
    yController = new PIDController(Constants.Y_REEF_ALIGNMENT_P, 0.0, 0);  // Horitontal movement
    rotController = new PIDController(Constants.ROT_REEF_ALIGNMENT_P, 0.0, 0);  // Rotation
    this.isRightScore = isRightScore;
    this.driveBase = drivebase;
    addRequirements(drivebase);

    // Let the current pipeline control the LEDs
    LimelightHelpers.setLEDMode_PipelineControl("");

    // Force the LEDs on
    //LimelightHelpers.setLEDMode_ForceOn("");

  }


public void robotInit() {
  // Force Limelight to use Pipeline 1 (AprilTags)
  LimelightHelpers.setPipelineIndex("",1);

  NetworkTableInstance.getDefault()
    .getTable("")
    .getEntry("")
    .setNumber(1);
}


  @Override 
  public void initialize() {
    this.stopTimer = new Timer();
    this.stopTimer.start();
    this.dontSeeTagTimer = new Timer();
    this.dontSeeTagTimer.start();

    rotController.setSetpoint(Constants.ROT_SETPOINT_REEF_ALIGNMENT);
    rotController.setTolerance(Constants.ROT_TOLERANCE_REEF_ALIGNMENT);

    xController.setSetpoint(Constants.X_SETPOINT_REEF_ALIGNMENT);
    xController.setTolerance(Constants.X_TOLERANCE_REEF_ALIGNMENT);

    yController.setSetpoint(isRightScore ? Constants.Y_SETPOINT_REEF_ALIGNMENT : -Constants.Y_SETPOINT_REEF_ALIGNMENT);
    yController.setTolerance(Constants.Y_TOLERANCE_REEF_ALIGNMENT);

    tagID = LimelightHelpers.getFiducialID("");  }

  @Override
  public void execute() {
    System.out.println(LimelightHelpers.getTV(""));
    if (LimelightHelpers.getTV("") && LimelightHelpers.getFiducialID("") == tagID) {
      System.out.println(LimelightHelpers.getFiducialID(""));
      this.dontSeeTagTimer.reset();

      double[] postions = LimelightHelpers.getBotPose_TargetSpace("");
      SmartDashboard.putNumber("x", postions[2]); 

      double xSpeed = xController.calculate(postions[2]); //switched from 2
      SmartDashboard.putNumber("xspee", xSpeed);
      double ySpeed = -yController.calculate(postions[0]); //switched fromm 0
      double rotValue = -rotController.calculate(postions[4]);// switched from 4

      driveBase.drive(xSpeed, ySpeed, rotValue, false); 

      if (!rotController.atSetpoint() ||
          !yController.atSetpoint() ||
          !xController.atSetpoint()) { 
        stopTimer.reset();
      } 
    } else {
      driveBase.drive(0, 0, 0, false); 
    }

    SmartDashboard.putNumber("poseValidTimer", stopTimer.get());
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.drive(0, 0, 0, false); 
  }

  @Override
  public boolean isFinished() {
    // Requires the robot to stay in the correct position for 0.3 seconds, as long as it gets a tag in the camera
    return this.dontSeeTagTimer.hasElapsed(Constants.DONT_SEE_TAG_WAIT_TIME) ||
        stopTimer.hasElapsed(Constants.POSE_VALIDATION_TIME);
  } 
}