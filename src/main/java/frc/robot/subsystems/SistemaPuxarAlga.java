package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PuxarAlgaEstado;

public class SistemaPuxarAlga extends SubsystemBase {

  public SparkMax PuxarAlgaMotor = new SparkMax(Constants.ConstanteSistemaPuxarAlga.SistemaPuxarAlgaMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig ConfigMotor = new SparkMaxConfig();
  public PuxarAlgaEstado estadoAtual = PuxarAlgaEstado.PARADO;


  public SistemaPuxarAlga() {
    ConfigMotor.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
  }

  @Override
  public void periodic() {
    if(estadoAtual == PuxarAlgaEstado.PUXA ||/*operador "OU" ||*/ estadoAtual == PuxarAlgaEstado.SOLTA){
      PuxarAlgaMotor.set(estadoAtual.velocidade);
    }else{
      PuxarAlgaMotor.stopMotor();
    }
  }
  
  public void SetCurrentState(PuxarAlgaEstado estado){
    this.estadoAtual = estado;
  }

  //Configuração do Encoder
  public double posicaoEncoder(){
    return PuxarAlgaMotor.getEncoder().getPosition();
  }
  
  public void resetEncoder(){
    PuxarAlgaMotor.getEncoder().setPosition(0);
  }
}