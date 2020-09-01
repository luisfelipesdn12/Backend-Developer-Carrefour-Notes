public class SincAsinc {
    public static void main(String[] args) {
        GeradorPDF geradorPDF = new GeradorPDF();
        Barra barra = new Barra(geradorPDF);
        geradorPDF.start();
        barra.start();
    }
}

class GeradorPDF extends Thread {
    /**
     * (Classe exemplo e não literal)
     * Espera 5 segundo e printa um log.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nPDF Gerado!");
    }
}

class Barra extends Thread {
    /**
     * Recebe a Thread do Gerador de PDF
     * como parâmetro do construtoe e usa
     * o método .isAlive() para parar de
     * rodar quando o PDF já foi gerado.
     */
    Thread geradorPDF;

    public Barra(Thread geradorPDF) {
        this.geradorPDF = geradorPDF;
        System.out.print("Loading");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(500);

                if (!geradorPDF.isAlive()) {
                    break;
                } else {System.out.print(".");}
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}