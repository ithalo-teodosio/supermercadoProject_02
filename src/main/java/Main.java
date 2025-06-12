import service.SupermercadoService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SupermercadoService supermercadoService = new SupermercadoService();
        supermercadoService.iniciarAtendimento(scan);
        scan.close();
    }
}