import java.io.*;
import java.util.Scanner;

public class InterfaceTexto {

    /*
    * Fazemos a instanciação do Scanner e da class cifraDeCesar e logo após é feito a inicialização pelo Construtor
    * */
    private Scanner in;
    private CifraDeCesar cifraDeCesar;
    /*
    * Fiz o programa pelo intellij idea e todos os arquivos que serão disponibilizados estavam na pasta "src",
    * talvez dependendo do ide o path_entrada deverá ser alterado.
    * */
    private static final String PATH_ENTRADA = "src\\cifraDeCesarEntrada.txt";

    public InterfaceTexto() {
        in = new Scanner(System.in);
        cifraDeCesar = new CifraDeCesar();
    }

    /*
    * O método de renderizar faz o uso de todos os métodos implementados pela InterfaceTexto
    * */
    public void renderizar() {
        // Salva tudo que foi encontrado no arquivo em uma String palavraChave
        String palavraChave = this.leArquivo();
        // Imprime a palavra que foi lida atraves do leArquivo()
        System.out.println(palavraChave);
        // Chama o método que irá salvar se o programa deve encriptar ou decriptar
        int info = this.oQueDesejaFazer();
        if (info != -1) {
            // Método que define a chave primária e emite o resultado passando o info como parametro
            this.coloqueSuaChaveSimetrica();
            this.emitirResultados(info);
        }
        sln("Programa encerrado!");
    }


    /*
    * Mostra o cabecalho e salva em uma variavel
    * se caso a informação for válida
    * */
    private int oQueDesejaFazer() {
        this.cabecalho();
        sln("Selecione uma opção: ");
        sln("Digite 0 para encriptar");
        sln("Digite 1 para decriptar");

        int opcao = in.nextInt();

        if (opcao > 1 || opcao < 0) {
            sln("Informação Inválida");
            return -1;
        }

        return opcao;
    }

    /*
    * Verifica qual a opção e salva a palavra resultado na String "palavraAposResultado"
    * E com isso já consegue exibir todos os resultados especificados no pdf
    * (a) Tamanho da mensagem de entrada, considerando apenas os caracteres v´alidos.
    * (b) Tamanho da mensagem encriptada/decriptada.
    * (c) Mensagem encriptada/decriptada.
    * */
    private void emitirResultados(int opcao) {
        String palavra = this.leArquivo();
        String palavraAposProcesso;
        if (opcao == 0)
            palavraAposProcesso = cifraDeCesar.encriptar(palavra);
        else
            palavraAposProcesso = cifraDeCesar.decriptar(palavra);
        sln("\nResultados: ");
        sln("\tTamanho da mensagem de entrada (Apenas caracteres válidos): " + cifraDeCesar.tamanhoDaMensagem(palavra));
        sln("\tTamanho da palavra encriptada/decriptada: " + cifraDeCesar.tamanhoDaMensagemSemFiltros());
        sln("\tPalavra resultado: " + palavraAposProcesso + "\n");
    }

    // Le um número inteiro e salva como o valor do deslocamento/chave simetrica
    private void coloqueSuaChaveSimetrica() {
        sln("\nDigite sua chave simétrica (deslocamento k): ");

        int chaveK = in.nextInt();

        if (chaveK > 25) {
            sln("Sua chave é inválida!");
            sln("Ela foi redefinada para k = 2");
        } else {
            cifraDeCesar.setK(chaveK);
        }
    }

    private void cabecalho() {
        sln("###################");
        sln("#      Cifra      #");
        sln("#       de        #");
        sln("#      Cesar      #");
        sln("###################\n");
    }

    private void sln(String s) {
        System.out.println(s);
    }

    /*
    * E inicializado uma variavel inicialemente vazia
    * Após isso é instanciado um leitor com o path definido no inicio da classe
    * e após isso ele verifica se caracter por caracter se ele tem valor diferente de -1
    * porém se caso retornar -1 quer dizer que não há mais caracteres.
    * Por padrão o java em algo de busca o (-1) quer dizer que não foi encontrado algo dentro
    * do método de pesquisa
    * */
    private String leArquivo() {
        String stringFromTxt = "";
        try {
            FileReader leitor = new FileReader(PATH_ENTRADA);
            int character;
            while ( (character = leitor.read()) != -1) {
                stringFromTxt += (char) character;
            }
            /*
            * Após o método read tiver o -1 como resposta o método while é encerrado
            * e o leitor é encerrado
            * */
            leitor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retorna tudo que foi possivel ler atraves do arquivo txt
        return stringFromTxt;
    }
}
