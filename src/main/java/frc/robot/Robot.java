// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.fasterxml.jackson.annotation.JsonTypeInfo.None;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    

    // Resetando o valor dos encoders
    // RobotContainer.sistemaClimber.resetEncoder();
    // RobotContainer.sistemaDescerAlga.resetEncoder();
    //RESETAR O ENCODER DA TRAÇÃO

  }
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Encoder Climber", RobotContainer.sistemaClimber.posicaoEncoder()*100);
    SmartDashboard.putNumber("Encoder Descer Alga", RobotContainer.sistemaDescerAlga.posicaoEncoder()*100);

    SmartDashboard.putBoolean("O limite de subida do climber nao foi atingido", RobotContainer.sistemaClimber.limiteMaxAtingido());
    SmartDashboard.putBoolean("O limite de descida do climber nao foi atingido", RobotContainer.sistemaClimber.limiteMinAtingido());

    SmartDashboard.putBoolean("Limite de subida da alga nao foi atingido", RobotContainer.sistemaDescerAlga.limiteSubida());    
    SmartDashboard.putBoolean("Limite de descida da alga nao foi atigindo", RobotContainer.sistemaDescerAlga.limiteDescida());    
    SmartDashboard.putNumber("Tempo de Partida", DriverStation.getMatchTime()*60);
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    RobotContainer.sistemaClimber.ClimberMotor.stopMotor();
    RobotContainer.sistemaTracao.stop();
    RobotContainer.sistemaDescerAlga.DesceAlgaMotor.stopMotor();
    RobotContainer.sistemaPuxarAlga.PuxarAlgaMotor.stopMotor();
    RobotContainer.sistemaCoral.coralMotor.stopMotor();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if(m_autoSelected == kCustomAuto){
      SmartDashboard.putString("Custom auto", "Seta do 12");
      System.out.println("Custom auto");
    }else if (m_autoSelected == kDefaultAuto) {
      SmartDashboard.putString("Default Auto", "autonomo ativado");
      System.out.println("Default Auto");
    }
  }

  @Override
  public void teleopInit() {
    

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
