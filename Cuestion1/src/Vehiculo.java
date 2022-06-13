public  abstract class Vehiculo {
    public String matricula;
    public String marca;
    public int motor;

    public Vehiculo(String matricula, String marca, int motor) {
        this.matricula=matricula;
        this.marca=marca;
        this.motor=motor;
    }
    public void Circular(){
        System.out.println("El auto esta circulando");
    }
}


