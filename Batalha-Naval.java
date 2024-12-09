import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    private static final int tamanhoTabela = 10; //DEFINE O TAMANHO MAX      *******
    private char[][] tabuleiro; //CONFERIR ACERTOS E ERROS
    private char[][] tabuleiroExibicao; //MOSTRAR AO JOGADOR
    private int acertos;
    private int tentativas;
    private long tempoInicio;

    // Inicia o jogo
    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);
        tempoInicio = System.currentTimeMillis(); // Começa a contar o tempo do jogo

        System.out.println(" ~ = Oceano\n X = Navio\n O = Água\n\n\n");
        mostrarTabuleiro();

        while (!jogoAcabou()) { //Enquanto o jogo não acabar, retorna aqui
            System.out.println("Informe as coordenadas para ataque (linha e coluna):");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            // Valida se as coordenadas estão dentro do tabuleiro
            if (linha < 0 || linha >= tamanhoTabela || coluna < 0 || coluna >= tamanhoTabela) {
                System.out.println("Coordenadas inválidas. Tente novamente.");
                continue;
            }

            // Realiza o ataque e mostra o resultado
            if (atacar(linha, coluna)) {
                System.out.println("Acertou um navio!");
            } else {
                System.out.println("Água!");
            }

            // Exibe o tabuleiro após o ataque
            mostrarTabuleiro();
        }

        // Exibe o resultado final do jogo
        mostrarResultado();
    }

    // Tabuleiros
    public BatalhaNaval() {
        this.tabuleiro = new char[tamanhoTabela][tamanhoTabela];
        this.tabuleiroExibicao = new char[tamanhoTabela][tamanhoTabela];
        inicializarTabuleiro();
        acertos = 0; //define como 0
        tentativas = 0; //define como 0
    }

    // Inicializa o tabuleiro com água e posiciona os navios aleatoriamente
    private void inicializarTabuleiro() {
        for (int i = 0; i < tamanhoTabela; i++) {
            for (int j = 0; j < tamanhoTabela; j++) {
                tabuleiro[i][j] = '~'; // Oceano
                tabuleiroExibicao[i][j] = '~'; // Tabuleiro para exibição
            }
        }
        posicionarNavios();
    }

    // Posiciona X (escolher numero) navios aleatoriamente no tabuleiro
    private void posicionarNavios() {
        Random rand = new Random();
        int navios = 5; // número de navios      *******
        while (navios > 0) {
            int x = rand.nextInt(tamanhoTabela);
            int y = rand.nextInt(tamanhoTabela);
            if (tabuleiro[x][y] == '~') {
                tabuleiro[x][y] = 'N'; // Navio
                navios--;
            }
        }
    }

    // Função para atacar o tabuleiro em uma posição específica
    public boolean atacar(int x, int y) {
        tentativas++;
        if (tabuleiro[x][y] == 'N') {
            tabuleiroExibicao[x][y] = 'X'; // Acerto
            acertos++;
            return true;
        } else {
            tabuleiroExibicao[x][y] = 'O'; // Água
            return false;
        }
    }

    // Exibe o tabuleiro para o jogador (somente com acertos e erros, não revela os navios)
    public void mostrarTabuleiro() {
        System.out.println("Tabuleiro:");

        // Exibe as colunas numeradas
        System.out.print("  ");
        for (int i = 0; i < tamanhoTabela; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Exibe as linhas numeradas
        for (int i = 0; i < tamanhoTabela; i++) {
            System.out.print(i + " "); // Exibe o número da linha
            for (int j = 0; j < tamanhoTabela; j++) {
                System.out.print(tabuleiroExibicao[i][j] + " "); // Exibe o conteúdo da posição
            }
            System.out.println();
        }
    }

    // Verifica se o jogo acabou (quando todos os navios foram afundados)
    public boolean jogoAcabou() {
        return acertos == 5; // O jogo acaba quando todos os navios forem afundados    *******
    }

    // Exibe o resultado final do jogo
    public void mostrarResultado() {
        long tempoTotal = (System.currentTimeMillis() - tempoInicio) / 1000; // tempo em segundos
        System.out.println("Jogo concluído!");
        System.out.println("Acertos: " + acertos);
        System.out.println("Tentativas: " + tentativas);
        System.out.println("Tempo de jogo: " + tempoTotal + " segundos");
    }


}
