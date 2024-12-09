import java.sql.SQLException;
import java.util.Scanner;
class Main {
    public static void main(String[] args) throws SQLException {

        Scanner entrada = new Scanner(System.in);
        int menu = 0;


        while(menu != 3){
            System.out.println("Bem-vindo ao jogo de Batalha Naval!");
            System.out.println("1 - Registrar");
            System.out.println("2 - Mostrar Jogadores Cadastrados");
            System.out.println("3 - Login");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            menu = entrada.nextInt();

            if(menu == 1) {
                //Cadastro
                Jogador.cadastrarJogador();
            }else if (menu == 2){
                //Mostrar Jogadores
                Jogador.mostrarJogadores();

            }else if (menu == 3){
                //Login
                Jogador.loginJogador();

            }else if (menu == 4){
                //Sair
                System.out.println("Até breve");
                System.out.println("Saindo ...");

            }else{
                System.out.println("Opção invalida!");
            }





        }
    }
}
