package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DescerAlgaEstado;
import frc.robot.Constants.LimiteEncoderDescerAlga;;

public class SistemaDescerAlga extends SubsystemBase {

  private static final double diametroTambor = 0.05; // metros (5 cm)
  private static final double relacao = 64.0; // Redução 64:1
  private static final int ticksPerRotation = 42;
    
  private static final double convercaoMetros = (Math.PI * diametroTambor) / (relacao * ticksPerRotation);

  public SparkMax DesceAlgaMotor = new SparkMax(Constants.ConstanteSistemaDescerAlga.DesceAlgaMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig configSparkMotor = new SparkMaxConfig();

  public DescerAlgaEstado estadoAtual = DescerAlgaEstado.PARADO;

  public SistemaDescerAlga() {
    configSparkMotor.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
  
      DesceAlgaMotor.configure(configSparkMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  } 

  @Override
  public void periodic() {
    if(estadoAtual == DescerAlgaEstado.SOBE && limiteSubida()||/*operador "OU" ||*/ estadoAtual == DescerAlgaEstado.DESCE){
        DesceAlgaMotor.set(estadoAtual.velocidade);
    } else{
        DesceAlgaMotor.stopMotor();
    }

    limiteSubida();
    limiteDescida();
  }

  public void SetCurrentState(DescerAlgaEstado estado){
    this.estadoAtual = estado;
  }

  //Configuração do Encoder
  public double posicaoEncoder(){
    return DesceAlgaMotor.getEncoder().getPosition() * convercaoMetros;
  }

  public void resetEncoder(){
    DesceAlgaMotor.getEncoder().setPosition(0);
  }
  
  public boolean limiteSubida(){
    if (-posicaoEncoder()/ 100< LimiteEncoderDescerAlga.limiteSubida){
      return false;
    } else{
      return true;
    }
  }
  
  public boolean limiteDescida(){
    if (-posicaoEncoder() /100 > LimiteEncoderDescerAlga.limiteDescida){
      SmartDashboard.putNumber("Posicao encoder desce alga negaticado", posicaoEncoder() /100);
      return false;
    } else{
      return true;
    }
  }
}