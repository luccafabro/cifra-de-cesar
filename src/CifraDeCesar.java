import java.util.LinkedList;
import java.util.ListIterator;

public class CifraDeCesar {
    // Definindo as duas estruturas que serão responsaveis pelo armazenamento e percorrimento de dados
    // Ambas armazenando Characteres, ou seja, (char)
    private LinkedList<Character> palavraChave;
    private ListIterator<Character> iterator;
    // Definindo o máixmo e o minimo definido pela tabela ascii
    private static final int MAXIMO = 122;
    private static final int MINIMO = 97;
    // Variavel que irá armazenar a nossa chave simetrica
    private int k;

    // Todos os possiveis casos de caracteres inválidos
    private final String CASO_1 = "WBRW";
    private final String CASO_2 = "WVRW";
    private final String CASO_3 = "WPTW";
    private final String CASO_4 = "WPVW";
    private final String CASO_5 = "WDPW";
    private final String CASO_6 = "WEXW";
    private final String CASO_7 = "WINW";
    private final String CASO_8 = "WHFW";

    public CifraDeCesar() {
        palavraChave = new LinkedList<>();
        // Inicia a Cifra de Cesar com a chave simetrica = 2, para que não haja erros em k começando como nulo
        k = 2;
    }

    /*
    * Se caso a palavra tiver algum caractere inválido, o caractere inválido
    * será substituido por algum dos 8 casos já definidos no pdf como base,
    * e para evitar de fazer todos os replaces se ao menos verificar se ele
    * tem algum caractere invalido, é feito um percorrimento pelo palavra passada
    * como parametro e analisado char por char no switch case, e se caso algum char
    * bate com algum dos caracteres dado como invalido é feito o replace, ou seja, a
    * substituição do caractere um inválido por uma serie de caracteres.
    * */
    private String criptografaCaracteresInvalidos(String aux) {
        for (int i = 0; i < aux.length(); i++) {
            switch (aux.charAt(i)) {
                case ' ':
                    aux = aux.replace(" ", CASO_1);
                    break;
                case ',':
                    aux = aux.replace(",", CASO_2);
                    break;
                case '.':
                    aux = aux.replace(".", CASO_3);
                    break;
                case ';':
                    aux = aux.replace(";", CASO_4);
                    break;
                case ':':
                    aux = aux.replace(":", CASO_5);
                    break;
                case '!':
                    aux = aux.replace("!", CASO_6);
                    break;
                case '?':
                    aux = aux.replace("?", CASO_7);
                    break;
                case '-':
                    aux = aux.replace("-", CASO_8);
                    break;
            }
        }

        // E após a analise a palavra e devolvida como todas minusculas, para ser posteriormente analisada dentro da tabela ascii
        return aux.toLowerCase();
    }

    /*
    * Esse método faz o que o criptografaCaracteresInvalidos porém ao contrario, sendo assim ele
    * verifica se o método em contem algum dos casos, e se caso contem é feito a substitução, nesse caso
    * não conseguimos fazer a comparação de char a char, pelo fato que todos os casos são String contendo quatro chars,
    * logo temos que fazer as comparações sem nenhuma estrutura de repetição e apenas com condicionais.
    * */
    private String trataCaracteresInvalidos(String aux) {
        // A palavra é forçada a ficar em letras maisculas pelo fato de que todos os casos são maiusculos
        // E outro detalhe é que assim já é preparada o dado para a saída
        String newAux = aux.toUpperCase();

        if (newAux.contains(CASO_1))
            newAux = newAux.replace(CASO_1, " ");
        if (newAux.contains(CASO_2))
            newAux = newAux.replace(CASO_2, ",");
        if (newAux.contains(CASO_3))
            newAux = newAux.replace(CASO_3, ".");
        if (newAux.contains(CASO_4))
            newAux = newAux.replace(CASO_4, ";");
        if (newAux.contains(CASO_5))
            newAux = newAux.replace(CASO_5, ":");
        if (newAux.contains(CASO_6))
            newAux = newAux.replace(CASO_6, "!");
        if (newAux.contains(CASO_7))
            newAux = newAux.replace(CASO_7, "?");
        if (newAux.contains(CASO_8))
            newAux = newAux.replace(CASO_8, "-");

        return newAux;
    }

    /*
    * Esse é o metódo responsável por encriptar as palavras
    * e recebe de parametro uma palavra
    * */
    public String encriptar(String keyWord) {
        /*
        * Definido a String que será a resposta do método
        * e já é realizado o primeiro passo que não apaga os caracteres invalidos, mas
        * sim ele criptografa eles
        * */
        String newString = "";
        String wordTreated = this.criptografaCaracteresInvalidos(keyWord);

        /*
        * Como a palavra já foi tratada e todos seus caracteres já estão como minusculos,
        * é hora de adicionar todos dentro da nossa LinkedList pelo método add
        * */
        for (int i = 0; i < wordTreated.length(); i++) {
            palavraChave.add(wordTreated.charAt(i));
        }

        /*
        * Nessa parte do código é aonde vemos a lógica da criptogria de cesar funcionando,
        * afinal todos os caracteres possuem um valor inteiro que representam seu valor
        * na tabela ascii, e é com isso que adicionaremos a chave simetrica a ele
        * */

        // Explicação desse método está descrita dentro do material extra
        /*
        * Mas basicamente verifica se mesmo com o chave primaria a palavra ainda se encaixa no
        * alfabeto, caso não se encaixe ela será jogada para o começo do alfabeto, pois dessa forma
        * nunca teremos algo que não está entre "a" e "z", pois o alfabete se torna rotativo, então
        * é como se a cabeça do alfabeto tivesse uma ligação com a cauda.
        * */
        for (Character c : palavraChave) {
            int valor = MINIMO + (this.getK() + c - MAXIMO -1);

            if (c <= MAXIMO && c >= MINIMO) {
                if ((c + this.getK()) > MAXIMO)
                    newString += (char) (valor);
                else
                    newString += (char) (c + this.getK());
            } else
                newString += c;
        }
        // Sempre o resultado é devolvido em upperCase ou seja inteiramentem em maiusculo
        return newString.toUpperCase();
    }


    /*
    * O método de decriptação é o método oposto do método de encriptação,
    * pois inves de ele fazer a soma da chave simetrica em cada char, ele
    * na realidade faz a subtração, e o percorrimento não é feito de ordem
    * comum pela estrutura e sim é feito ao contrario com auxilio do iterator
    * */
    public String decriptar(String encryptedWord) {
        String newString = "";
        encryptedWord = encryptedWord.toLowerCase();

        // Após a palavra ser transformada para lowerCase ela já pode ser armazenada na estrutura
        for (int i = 0; i < encryptedWord.length(); i++) {
            palavraChave.add(encryptedWord.charAt(i));
        }

        // Aqui o iterador já tem o valor da nossa estrutura completa
        iterator = palavraChave.listIterator(palavraChave.size());

        /*
        * E aqui com a ajuda dos métodos que o iterador possui, fazemos uma estrutura de repetição
        * repetir até que o valor que está a sua esquerda seja completamente nulo.
        * */

        /*
        * Fazemos a mesma verificação se o char está dentro do tabela ascii conforme definidos e se caso
        * já está conforme o imaginado ele faz a verificação se for retirado a quantidade da chave simetrica
        * ele passa do limite que é aceito e se caso passar do limite é feito a seguinte operação.
        * Letra "a" menos a chave primaria 1, sairia fora do intervalo, mas como na verdade ele não pode sair
        * ele vai para a cauda do alfabeto e diminui 1, ou seja o letra resultante é "z".
        * */
        while(iterator.hasPrevious()) {
            char c = iterator.previous();
            int valor = MAXIMO - (MINIMO - (c - this.getK() + 1));
            if (c <= MAXIMO && c >= MINIMO) {
                if ((c - this.getK()) < MINIMO) {
                    newString += (char) (valor);
                }
                else
                    newString += (char) (c - this.getK());
            }
        }
        /*
        * O resultado dessa estrutura de repetição é a palavra decriptada ao contrario, pois como o percorrimento
        * é feito como o contrario do convencional teremos que inverter a palavra logo em seguida
        * */


        /*
        * Após a instanciação do StringBuilder fazemos a adição da palavra resultante
        * da estrutura de repetição e logo após invertemos a String
        * */
        StringBuilder inverseString = new StringBuilder();
        inverseString.append(newString);
        inverseString.reverse();

        /*
        * Agora jogamos a palavra já invertida fazemos uma especie de casting para String
        * e passamos para o trataCaracteresInvalidos, aonde ele faz o último tratamento da palavra
        * para verificar se a palavra fonte não tinha algum caractere invalido
        * */
        String wordTreated = this.trataCaracteresInvalidos(String.valueOf(inverseString));

        return wordTreated;
    }

    /*
    * Esse método é responsável por implementar o valor da chave simetrica
    * do exercicio, e se caso for maior que 25 é definido uma chave igual 2
    * */
    public void setK(int k) {
        if (k > 25)
            this.k = 2;
        else
            this.k = k;
    }

    public int getK() {
        return k;
    }

    /*
    * Esse método retorna o tamanho da palavra em caractere sem filtro
    * */
    public int tamanhoDaMensagemSemFiltros() {
        return palavraChave.size();
    }

    /*
    * Esse método também retorna o tamanho da palavra que é passada como argumento,
    * porém é feito a retirada de todos os caracteres invalidos
    * dessa forma uma como "lu f!" não terá 2 + 1 + 2 caracteres e sim 2 + 1 apenas
    * pois o espaço em branco não é considerado e nem o ponto de exclamação
    * */
    public int tamanhoDaMensagem(String stringInteira) {
        String newAux = stringInteira.replace(" ", "");
        newAux = newAux.replace(",", "");
        newAux = newAux.replace(".", "");
        newAux = newAux.replace(";", "");
        newAux = newAux.replace(":", "");
        newAux = newAux.replace("!", "");
        newAux = newAux.replace("?", "");
        newAux = newAux.replace("-", "");

        return newAux.length();
    }
}
