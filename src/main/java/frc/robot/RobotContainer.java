package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.FeederIn;
import frc.robot.commands.FeederOut;
import frc.robot.commands.HighFeederIn;
import frc.robot.commands.HighFeederOut;
import frc.robot.commands.IntakeArmDown;
import frc.robot.commands.IntakeArmUp;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.RollersIn;
import frc.robot.commands.RollersOut;
import frc.robot.commands.ShooterFullPower;
import frc.robot.commands.ShooterOut;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Feeder;
/* Subsystems imports */
import frc.robot.subsystems.HighFeeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.Rollers;
import frc.robot.subsystems.Shooter;


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
  public final HighFeeder highfeeder = new HighFeeder();
  public final IntakeArm intakearm = new IntakeArm();


  /* Auto */
  private final SendableChooser<Command> chooser;
  public Pose2d startingPose;


  //public final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem(); //not needed
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    m_robotDrive.zeroHeading();
    configureBindings();

    Map<String,Command> namedCommands = new HashMap<String,Command>();

    //Left Side Auto
    namedCommands.put("StartShooter", new ShooterOut(shooter).withTimeout(1.5));
    namedCommands.put("RunShooter", new ShooterOut(shooter).withTimeout(4));
    namedCommands.put("RunFeeder", new FeederOut(feeder).withTimeout(4));
    namedCommands.put("RunRollers", new RollersIn(rollers).withTimeout(4));
    namedCommands.put("RunIntake", new IntakeIn(intake).withTimeout(4));
    namedCommands.put("RunIntakeFeeder", new FeederIn(feeder).withTimeout(2));
    namedCommands.put("RunIntakeRollers", new RollersOut(rollers).withTimeout(2));
    namedCommands.put("RunIntakeIntake", new IntakeIn(intake).withTimeout(2));
    namedCommands.put("RunFeeder2", new FeederOut(feeder).withTimeout(2.25));
    namedCommands.put("RunRollers2", new RollersIn(rollers).withTimeout(2.25));
    namedCommands.put("RunIntake2", new IntakeIn(intake).withTimeout(2.25));
    namedCommands.put("RunShooter2", new ShooterOut(shooter).withTimeout(2.25));

    namedCommands.put("RunFeeder3", new FeederOut(feeder).withTimeout(1));
    namedCommands.put("RunRollers3", new RollersIn(rollers).withTimeout(1));
    namedCommands.put("RunIntake3", new IntakeIn(intake).withTimeout(1));

    namedCommands.put("RunShooterLong", new ShooterOut(shooter).withTimeout(8));
    namedCommands.put("RunFeederLong", new FeederOut(feeder).withTimeout(8));
    namedCommands.put("RunRollersLong", new RollersIn(rollers).withTimeout(8));
    namedCommands.put("RunIntakeLong", new IntakeIn(intake).withTimeout(8));


    namedCommands.put("DrivetrainSlow", new RunCommand(
        () -> m_robotDrive.drive(0.01, 0, 0, false)
        ).withTimeout(3));




    //Right Side Auto
    namedCommands.put("RunShooter3", new ShooterOut(shooter).withTimeout(5));
    namedCommands.put("RunFeeder3", new FeederOut(feeder).withTimeout(5));
    namedCommands.put("RunRollers3", new RollersIn(rollers).withTimeout(5));
    namedCommands.put("RunIntake3", new IntakeIn(intake).withTimeout(5));


    NamedCommands.registerCommands(namedCommands);
    chooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto:", chooser);
    
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                 MathUtil.applyDeadband(driver.getLeftY()*1, OIConstants.kDriveDeadband), //left stick 
                 MathUtil.applyDeadband(driver.getLeftX()*1, OIConstants.kDriveDeadband), //left stick 
                 MathUtil.applyDeadband(driver.getRightX()*-1, OIConstants.kDriveDeadband), //right stick
                true),
            m_robotDrive)
            );

        driver.povLeft().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(0, -0.06, 0, false)
        ));
        driver.povRight().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(0, 0.06, 0, false)
        ));
        driver.povUp().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(-0.06, 0, 0, false)
        ));
        driver.povDown().whileTrue(new RunCommand(
        () -> m_robotDrive.drive(0.06, 0, 0, false)
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

    //Limelight
    //driver.a().whileTrue(new AlignToReefTagRelative(true, m_robotDrive));

    //Gyro
    driver.y().whileTrue(new InstantCommand(() -> m_robotDrive.zeroHeading(), m_robotDrive)); //Change this slightly to reset gyro when driving

    //Intake to Hopper:
    driver.rightTrigger().whileTrue(new IntakeIn(intake));
    
    //Raise or Lower Intake
    driver.rightBumper().whileTrue(new IntakeArmUp(intakearm));
    driver.leftBumper().whileTrue(new IntakeArmDown(intakearm));
    
    //Shooter
    operator.rightTrigger().whileTrue(new ShooterOut(shooter));

    //Shooter Full Power
    operator.rightBumper().whileTrue(new ShooterFullPower(shooter));

    //Feed to shooter
    operator.leftTrigger().whileTrue(new FeederOut(feeder));
    operator.leftTrigger().whileTrue(new RollersIn(rollers));
    operator.leftTrigger().whileTrue(new HighFeederOut(highfeeder));

    //Shooter Jam Fix
    operator.leftBumper().whileTrue(new FeederIn(feeder));
    operator.leftBumper().whileTrue(new HighFeederIn(highfeeder));

    //Manual roller control
    operator.povUp().whileTrue(new RollersIn(rollers));
    operator.povDown().whileTrue(new RollersOut(rollers));

  
   

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem); //not needed
    m_robotDrive.zeroHeading();
    m_robotDrive.resetOdometry(m_robotDrive.getPose());
    return chooser.getSelected();
    
  }
}
