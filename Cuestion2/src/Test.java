import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int posicion=0;
        float puntos = 0f;
        ArrayList<String> pregres = new ArrayList<>();
        ArrayList<Integer> random = new ArrayList<>();
        try {
            Scanner sc = new Scanner(Test.class.getResourceAsStream("Preguntas.txt"));
            Scanner lc = new Scanner(System.in);
            while (sc.hasNext()){
                pregres.add(sc.nextLine());
                if(posicion%2 == 0)
                    random.add(posicion);
                posicion++;
            }
            Collections.shuffle(random);
            for (Integer i : random) {
                posicion = i;
                String pregunta = pregres.get(posicion);
                String posrespuesta = pregres.get(posicion + 1);
                System.out.println(pregunta);
                String respuesta = lc.nextLine();
                if (respuesta.equalsIgnoreCase(posrespuesta)) {
                    System.out.println("Has acertado");
                    puntos++;
                }else{
                    System.out.println("Has fallado");
                    puntos -= 0.5;
                }
            }
            System.out.println("Has sacado: "+puntos+" puntos.");
            if(puntos >= 5)
                System.out.println("Enhorabuena, has aprobado");
            else
                System.out.println("Lo siento, has suspendido");
        } catch (NullPointerException ne) {
            System.out.println("No se puede encontrar el archivo");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
