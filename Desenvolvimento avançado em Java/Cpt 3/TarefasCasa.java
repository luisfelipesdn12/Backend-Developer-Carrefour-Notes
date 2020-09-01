import java.util.List;
import java.util.stream.Stream;

public class TarefasCasa {
    public static void main(String[] args) {
        Comodo quarto = new Comodo(List.of(
            new Atividade("Arrumar a cama"),
            new Atividade("Tirar pó")
        ));
    }
}

class Atividade {
    String name;

    public Atividade(String name) {
        this.name = name;
    }

    public void fazer() {System.out.println("Atividade " + this.name + " realizada!");}
}

class Comodo {
   List<Atividade> atividades;
   
   public Comodo(List atividade) {
       this.atividades = atividade;
   }

   public List getAtividades() {return atividades;}
}
