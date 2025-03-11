// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoTracao;

public class DriveTrainSystem extends SubsystemBase {
  public EstadoTracao currentState = EstadoTracao.PARADO;

  SparkMax motorDireitaFrente = new SparkMax(Constants.ConstantesTracao.IDmotorDireitaFrente, MotorType.kBrushed);
  SparkMax motorDireitaTras = new SparkMax(Constants.ConstantesTracao.IDmotorDiretaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaFrente = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaTras = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaFrente, MotorType.kBrushed);

  SparkMaxConfig configMotorDireita = new SparkMaxConfig();
  SparkMaxConfig configMotorEsquerda = new SparkMaxConfig();

  MotorControllerGroup agrupamentoMotoresEsquerda = new MotorControllerGroup(motorEsquerdaTras, motorEsquerdaFrente);
  MotorControllerGroup agrupamenoMotoresDireita = new MotorControllerGroup(motorDireitaFrente, motorDireitaTras);


  DifferentialDrive differentialDrive = new DifferentialDrive(agrupamentoMotoresEsquerda, agrupamenoMotoresDireita);

  public DriveTrainSystem() {
    configMotorDireita
    .inverted(true)
    .smartCurrentLimit(60)
    .idleMode(IdleMode.kBrake);

    configMotorEsquerda
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60);

    motorDireitaFrente.configure(configMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorDireitaTras.configure(configMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaTras.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaFrente.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void arcadeMode(double drive, double turn){
    differentialDrive.arcadeDrive(drive, turn);
  }

  public void tankmode(double left, double right){
    differentialDrive.tankDrive(left, right);
  }
  public void stop(){
    differentialDrive.stopMotor(); 
  }

  public void SetCurrentState(EstadoTracao state){
    this.currentState = state;
  }
}
