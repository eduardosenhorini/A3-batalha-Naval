import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;


class Jogador {

    private static int idJogador;
    private static String login = "";
    private static String senha = "";

    private static ArrayList<Jogador> listaJogadores =
            new ArrayList<Jogador>();

    public static ArrayList<Jogador> getListaJogadores() {
        return listaJogadores;
    }

    public Jogador() {
    }

    public Jogador(String login, String senha) {
        this.idJogador = listaJogadores.size();
        this.login = login;
        this.senha = senha;

    }

    public static void cadastrarJogador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o login: ");
        login = scanner.nextLine();

        System.out.print("Digite a senha: ");
        senha = scanner.nextLine();

        Jogador novoJogador = new Jogador(login, senha);

        String sql = "INSERT INTO jogador (login, senha)" +
                "VALUES ('" + login + "', '" + senha + "');";

        boolean salvo = ConexaoBD.salvar(sql);

        if (salvo) {
            System.out.println("\nCadastro realizado com sucesso!\n");
        } else {
            System.out.println("\nProblemas ao realizar cadastro!\n");
        }

        listaJogadores.add(novoJogador);
    }

    public static void mostrarJogadores() throws SQLException {

        String sql = "SELECT * FROM jogador;";
        ResultSet resultado = ConexaoBD.buscar(sql);
        listaJogadores = new ArrayList<Jogador>();
        while (resultado.next()) {
            Jogador jogadorEncontrado = new Jogador();
            jogadorEncontrado.idJogador = resultado.getInt("idJogador");
            jogadorEncontrado.login = resultado.getString("login");
            jogadorEncontrado.senha = resultado.getString("Senha");
            listaJogadores.add(jogadorEncontrado);
        }

        System.out.print("\n\nJOGADORES CADASTRADOS: ");
        System.out.println(listaJogadores.size());
        System.out.println("ID \t\t Login \t\t Senha");

        for (int i = 0; i < listaJogadores.size(); i++) {
            System.out.println(listaJogadores.get(i).idJogador +
                    "\t\t" + listaJogadores.get(i).login +
                    "\t\t" + listaJogadores.get(i).senha);
        }
        System.out.println("\n\n");
    }

        public static boolean loginJogador() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o login: ");
            String loginJogador = scanner.nextLine();

            System.out.print("Digite a senha: ");
            String senhaJogador = scanner.nextLine();

            // Verifica se login e senha estao corretas e incia o jogo
            if (loginJogador.equals(login) && senhaJogador.equals(senha)) {
                System.out.println("Login bem-sucedido!");

                System.out.println("Bem-vindo ao jogo de Batalha Naval!");
                System.out.println("Iniciando o jogo...");

                // Iniciar o jogo
                BatalhaNaval jogo = new BatalhaNaval();
                jogo.iniciarJogo();
                ;
                return true;
            } else {
                System.out.println("\nUsuário ou senha inválidos.");
                System.out.println("\n\n");
                return false;


            }
        }
    }
