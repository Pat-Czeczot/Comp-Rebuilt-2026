// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AlignToReefTagRelative;
//import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
/* Commands imports */
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.FeederIn;
import frc.robot.commands.FeederOut;
import frc.robot.commands.ShooterIn;
import frc.robot.commands.ShooterOut;
import frc.robot.commands.RollersIn;
import frc.robot.commands.RollersOut;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.ClimberDown;
/* Subsystems imports */
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Rollers;
import frc.robot.subsystems.Climber;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  /* Controllers */
  //XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  private final CommandXboxController driver = new CommandXboxController(0);
  private final CommandXboxController operator = new CommandXboxController(1);
  

  /* Subsystems */
  public final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public final Intake intake = new Intake();
  public final Feeder feeder = new Feeder();
  public final Shooter shooter = new Shooter();
  public final Rollers rollers = new Rollers();
  public final Climber climber = new Climber();


  /* Auto */


  public final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    m_robotDrive.zeroHeading();
    configureBindings();

    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(driver.getLeftY()*1, OIConstants.kDriveDeadband), //left stick 
                -MathUtil.applyDeadband(driver.getLeftX()*1, OIConstants.kDriveDeadband), //left stick 
                -MathUtil.applyDeadband(driver.getRightX()*1, OIConstants.kDriveDeadband), //right stick
                true),
            m_robotDrive)
            );

        driver.povLeft().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(0, 0.06, 0, false)
        ));
        driver.povRight().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(0, -0.06, 0, false)
        ));
        driver.povUp().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(0.06, 0, 0, false)
        ));
        driver.povDown().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(-0.06, 0, 0, false)
        ));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
  /* Controles */

    driver.x().whileTrue(new AlignToReefTagRelative(true, m_robotDrive));
    driver.y().whileTrue(new InstantCommand(() -> m_robotDrive.zeroHeading(), m_robotDrive)); //Change this slightly to reset gyro when driving


    operator.leftTrigger().whileTrue(new IntakeIn(intake)); 
    operator.rightTrigger().whileTrue(new IntakeOut(intake));

    driver.leftBumper().whileTrue(new FeederIn(feeder)); 
    driver.rightBumper().whileTrue(new FeederOut(feeder));

    driver.leftTrigger().whileTrue(new ShooterIn(shooter)); 
    driver.rightTrigger().whileTrue(new ShooterOut(shooter));

    driver.a().whileTrue(new RollersIn(rollers)); 
    driver.b().whileTrue(new RollersOut(rollers));

    operator.povUp().whileTrue(new ClimberUp(climber)); 
    operator.povDown().whileTrue(new ClimberDown(climber));






  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
