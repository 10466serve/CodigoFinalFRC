package frc.robot;

public final class Constants {
  public static class ConstantesTracao {
    public static int IDmotorDireitaFrente = 13;
    public static int IDmotorDiretaTras = 3;
    public static int IDmotorEsquerdaFrente = 12;
    public static int IDmotorEsquerdaTras = 11;
  } 

  public static class JoysticsDeControle {
    //ID dos axes
    //Semelhante ao LabVIEW
    public static int valorX = 1;//Analógico Esquerdo(X)
    public static int valorY = 4;//Analógigo direito(Y)
    public static int valorY2 = 4;
  }
  
  public static class ConstanteSistemaCoral {
    public static int MotorID = 2;
  }

  public static class ConstanteSistemaClimber {
    public static int SistemaClimberMotorsID = 6;
  }
  
  public static class ConstanteSistemaDescerAlga {
    public static int DesceAlgaMotorsID = 9;  
    }
  
  public static class ConstanteSistemaPuxarAlga {
    public static int SistemaPuxarAlgaMotorsID = 10;
  }

  public static class LimiteEncoderClimber {
    //Define o limite de rotação dos encoders
    public static double limiteMinimo = 0.0;
    public static double limiteMaxClimber = 1.3;
  }

  public static class LimiteEncoderDescerAlga {
    //Define o limite de rotação dos encoders
    public static double limiteSubida = 0.0;
    public static double limiteDescida = 0.1;
  }

    
  //Métodos enums -> Estados dos mecanismos
  public static enum EstadoClimber {
    PARADO(0), CLIMBING(0.50), RECLIMBING(-0.50);
    public final double velocidade;
    
    private EstadoClimber(double velocidade){
      this.velocidade = velocidade;
    }
  }
  
  public static enum EstadoCoral {
    PARADO(0), ATIVADO(0.60), CONTRARIO(-0.60);
    public final double velocidade;
    
    private EstadoCoral(double velocidade){
      this.velocidade = velocidade;
    }
  }

  public static enum DescerAlgaEstado {
    PARADO(0), SOBE(0.25), DESCE(-0.25);
    public final double velocidade;
    
    private DescerAlgaEstado(double velocidade){
      this.velocidade = velocidade;
    }
  }

  public static enum PuxarAlgaEstado{
    //Positivo Puxa
    //Negativo Solta
    PARADO(0), PUXA(0.5), SOLTA(-0.5);
    public final double velocidade;

    private PuxarAlgaEstado(double velocidade){
      this.velocidade = velocidade;
    }
  }

  public static enum EstadoTracao {
    PARADO(0), MID(0.7), FULL(1);
    public final double velocidade;
    
    private EstadoTracao(double velocidade){
      this.velocidade = velocidade; 
    }
  }
}