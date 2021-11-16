// Todos os resultados esperados foram gerados com esse site: https://cryptii.com/pipes/caesar-cipher

public class Teste {

    private static CifraDeCesar cf;

    // Todos os testes mantém o esse mesmo padrão abaixo

    // teste Criptografa ou Descriptografa e valor da chave simetrica
        // inicialização da cifra de cesar

        // palavra que deverá ser a entrada
        // palavra esperada como resultado
        // chave simetrica

        // Atribuindo o valor da chave simetrica

        // Verifica se o resultado é o mesmo do esperado
        // Imprime no terminal se o resultado foi positivo ou negativo

        // Imprime a palavra antes e depois

    public static void testeCripografaK14() {
        cf = new CifraDeCesar();

        String palavraAntes = "luccafabro";
        String palavraCriptografada = "ziqqotopfc".toUpperCase();
        int k = 14;

        cf.setK(k);

        if (cf.encriptar(palavraAntes).equals(palavraCriptografada))
            System.out.println("RESULTADO CORRETO:");
        else
            System.out.println("RESULTADO INCORRETO:");

        System.out.println("\tPalavra antes -> " + palavraAntes);
        System.out.println("\tPalavra depois -> " + palavraCriptografada);
    }

    public static void testeDescriptografaK14() {
        cf = new CifraDeCesar();

        String palavraAntes = "ZIQQOTOPFC";
        String palavraDescriptografada = "luccafabro".toUpperCase();
        int k = 14;

        cf.setK(k);

        if (cf.decriptar(palavraAntes).equals(palavraDescriptografada))
            System.out.println("RESULTADO CORRETO:");
        else
            System.out.println("RESULTADO INCORRETO:");

        System.out.println("\tPalavra antes -> " + palavraAntes);
        System.out.println("\tPalavra depois -> " + palavraDescriptografada);
    }

    public static void testeCripografaK2() {
        cf = new CifraDeCesar();

        String palavraAntes = "ESSE EXERCICIO-PROGRAMA VAI SER MUITO LEGAL.";
        String palavraCriptografada = "GUUGYDTYGZGTEKEKQYJHYRTQITCOCYDTYXCKYDTYUGTYDTYOWKVQYDTYNGICNYRVY";
        int k = 2;

        cf.setK(k);

        if (cf.encriptar(palavraAntes).equals(palavraCriptografada))
            System.out.println("RESULTADO CORRETO:");
        else
            System.out.println("RESULTADO INCORRETO:");

        System.out.println("\tPalavra antes -> " + palavraAntes);
        System.out.println("\tPalavra depois -> " + palavraCriptografada);
    }

    public static void testeDescriptografaK2() {
        cf = new CifraDeCesar();

        String palavraAntes = "GUUGYDTYGZGTEKEKQYJHYRTQITCOCYDTYXCKYDTYUGTYDTYOWKVQYDTYNGICNYRVY";
        String palavraDescriptografada = "ESSE EXERCICIO-PROGRAMA VAI SER MUITO LEGAL.";
        int k = 2;

        cf.setK(k);

        if (cf.decriptar(palavraAntes).equals(palavraDescriptografada))
            System.out.println("RESULTADO CORRETO:");
        else
            System.out.println("RESULTADO INCORRETO:");

        System.out.println("\tPalavra antes -> " + palavraAntes);
        System.out.println("\tPalavra depois -> " + palavraDescriptografada);
    }

    public static void testeTamanhoDasPalavras() {
        cf = new CifraDeCesar();

        String palavraAntes = "trabalho final"; // 8 + 1 + 5
        String palavraCriptografada = "omvwvgcjRWMRadivg".toUpperCase();

        int k = 21;

        cf.setK(k);
        if (cf.encriptar(palavraAntes).equals(palavraCriptografada))
            System.out.println("RESULTADO CORRETO:");
        else
            System.out.println("RESULTADO INCORRETO:");

        System.out.println("\tPalavra antes -> " + palavraAntes);
        System.out.println("\tPalavra depois -> " + palavraCriptografada);

        /*
        * Parte do método extra dos demais onde verifica o tamanho das mensagens sem ou com filtros
        * Mas segue o mesmo padrão dos demais
        * */

        if (cf.tamanhoDaMensagemSemFiltros() == 17 && cf.tamanhoDaMensagem(palavraAntes) == 13)
            System.out.println("\nTAMANHO CORRETO:");
        else
            System.out.println("TAMANHO INCORRETO:");

        System.out.println("\tTamanho da mensagem sem filtros: " + cf.tamanhoDaMensagemSemFiltros());
        System.out.println("\tTamanho da mensagem: " + cf.tamanhoDaMensagem(palavraAntes));
    }


    /*
    * o println serve apenas para uma melhor divisão dos testes
    * */
    public static void main(String[] args) {
        testeCripografaK14();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        testeDescriptografaK14();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        testeCripografaK2();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        testeDescriptografaK2();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        testeTamanhoDasPalavras();
    }
}
