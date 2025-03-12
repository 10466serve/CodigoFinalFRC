// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DescerAlgaEstado;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.EstadoTracao;
import frc.robot.Constants.PuxarAlgaEstado;
//import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.Manipulator.ClimberJoy;
import frc.robot.commands.Manipulator.SetMechanismState;
import frc.robot.subsystems.DriveTrainSystem;
import frc.robot.subsystems.SistemaDescerAlga;
import frc.robot.subsystems.SistemaPuxarAlga;
import frc.robot.subsystems.ClimberSystem;
import frc.robot.subsystems.CoralScoreSystem;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  public static final DriveTrainSystem sistemaTracao = new DriveTrainSystem();
  public static final CoralScoreSystem sistemaCoral = new CoralScoreSystem();
  public static final SistemaDescerAlga sistemaDescerAlga = new SistemaDescerAlga();
  public static final SistemaPuxarAlga sistemaPuxarAlga = new SistemaPuxarAlga();
  public static final ClimberSystem sistemaClimber = new ClimberSystem();
  
    CommandXboxController joystick1 = new CommandXboxController(0);
    static CommandXboxController joystick2 = new CommandXboxController(1);
    public static final ClimberJoy controleClimber = new ClimberJoy(sistemaClimber, joystick2);
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
    defaultcommands();
  }

  private void configureBindings() {
  
  //Controle do driver da tração
  joystick1.button(1).whileTrue(new SetMechanismState(EstadoTracao.PARADO));
  joystick1.button(5).whileTrue(new SetMechanismState(EstadoTracao.MID));
  joystick1.button(6).whileTrue(new SetMechanismState(EstadoTracao.FULL));
  
  //Climber
  joystick2.leftTrigger().whileTrue(new SetMechanismState(EstadoClimber.RECLIMBING)).onFalse(new SetMechanismState(EstadoClimber.PARADO));
  joystick2.button(5).whileTrue(new SetMechanismState(EstadoClimber.CLIMBING)).onFalse(new SetMechanismState(EstadoClimber.PARADO));

  //Braço\alga
  joystick2.button(6).whileTrue(new SetMechanismState(DescerAlgaEstado.SOBE)).onFalse(new SetMechanismState(DescerAlgaEstado.PARADO));
  joystick2.rightTrigger().whileTrue(new SetMechanismState(DescerAlgaEstado.DESCE)).onFalse(new SetMechanismState(DescerAlgaEstado.PARADO));

  //Coral
  joystick2.button(4).whileTrue(new SetMechanismState(EstadoCoral.ATIVADO)).onFalse(new SetMechanismState(EstadoCoral.PARADO));
  joystick2.button(2).whileTrue(new SetMechanismState(EstadoCoral.CONTRARIO)).onFalse(new SetMechanismState(EstadoCoral.PARADO));

  //Puxar Alga
  joystick2.button(3).whileTrue(new SetMechanismState(PuxarAlgaEstado.PUXA)).onFalse(new SetMechanismState(PuxarAlgaEstado.PARADO));
  joystick2.button(1).whileTrue(new SetMechanismState(PuxarAlgaEstado.SOLTA)).onFalse(new SetMechanismState(PuxarAlgaEstado.PARADO));
  //CABO

  
  
  }
  
  private void defaultcommands(){
    sistemaTracao.setDefaultCommand(new DriveWithJoystick(sistemaTracao, joystick1));
    sistemaCoral.setDefaultCommand(null);
  }
}
