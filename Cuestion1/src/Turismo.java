public class Turismo extends Vehiculo{
    public int idTurismo;
    public int ruedas;
    public int puertas;

    public Turismo(String matricula, String marca, int motor, int idTurismo, int ruedas, int puertas) {
        super(matricula,marca,motor);
        this.idTurismo = idTurismo;
        this.ruedas = ruedas;
        this.puertas = puertas;
    }

    @Override
    public void Circular() {
        System.out.println("El turismo "+idTurismo+" con matrícula "+matricula+" está circulando.");
    }
    public void Circular(String ciudad){
        System.out.println("El turismo "+idTurismo+" con matrícula "+matricula+" está circulando en "+ciudad+".");
    }
}


