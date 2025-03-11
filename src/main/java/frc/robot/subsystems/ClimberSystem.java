package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.LimiteEncoderClimber;


public class ClimberSystem extends SubsystemBase {

  private static final double diametroTambor = 0.05; // metros (5 cm)
  private static final double relacao = 64.0; // Redução 64:1
  private static final int ticksPerRotation = 42;
    
  private static final double convercaoMetros = (Math.PI * diametroTambor) / (relacao * ticksPerRotation);


  public SparkMax ClimberMotor = new SparkMax(Constants.ConstanteSistemaClimber.SistemaClimberMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig Config = new SparkMaxConfig();

  public EstadoClimber estadoAtual = EstadoClimber.PARADO;

  

  public ClimberSystem() {
    Config.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    ClimberMotor.configure(Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  } 
 
@Override
  public void periodic() {
    if(estadoAtual == EstadoClimber.CLIMBING &&limiteMaxAtingido() || estadoAtual == EstadoClimber.RECLIMBING && limiteMinAtingido()){
      ClimberMotor.set(estadoAtual.velocidade);
    } else{
      ClimberMotor.stopMotor();
    }
    
    //Verifica periodicamente se o Limite máximo foi atingido
    limiteMaxAtingido();
    //Verifica periodicamente se o limite minimo foi atingido
    limiteMinAtingido();
  }
  
    public void SetcurrentState(EstadoClimber state){
        this.estadoAtual = state;
    }

    //Configuração do Encoder
    public double posicaoEncoder(){
      return ClimberMotor.getEncoder().getPosition() * convercaoMetros;
    }

    public void resetEncoder(){
      ClimberMotor.getEncoder().setPosition(0);
    }
    

    public boolean limiteMaxAtingido(){
      if (posicaoEncoder() * 100> LimiteEncoderClimber.limiteMaxClimber){
        return false;
      } else{
        return true;
      }
    }
    
    public boolean limiteMinAtingido(){
      if (posicaoEncoder() *100 < LimiteEncoderClimber.limiteMinimo){//Multiplicação feita para converter o valor negativo em positivo
        return false;
      } else{
        return true;
      }
    }  
  }
