package dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darlan
 */
public class Principal {

    private static Grafo grafo;

    //Método para fazer a leitura do arquivogra
    public static boolean readFile() {
        // API de leitura de arquivo
        FileReader arquivo = null;

        Scanner ler = new Scanner(System.in);
        System.out.println("-------------------DIJKSTRA-------------------\n");
        System.out.printf("Informe o nome de arquivo para entrada dos dados:\n");
        String nome = ler.nextLine();

        try {
            arquivo = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arquivo);
            // lê a primeira linha do arquivo
            String linha = lerArq.readLine();

            String[] pares = linha.split(" ");
            int n = Integer.parseInt(pares[0]);
            int m = Integer.parseInt(pares[1]);

            grafo = new Grafo(n, m);
            // lê da segunda linha do arquivo
            linha = lerArq.readLine();

            System.out.printf("\nConteúdo do arquivo:\n");
            while (linha != null) {
                System.out.printf("%s\n", linha);
                pares = linha.split(" ");
                n = Integer.parseInt(pares[0]);
                m = Integer.parseInt(pares[1]);
                int peso = Integer.parseInt(pares[2].trim());
                grafo.addAresta(n, m, peso);
                // lê até a última linha do arquivo
                linha = lerArq.readLine();
            }
            arquivo.close();
            return true;
        } catch (IOException e) {
            //trata as exceções que ocorrerem na abertura do arquivo
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        } finally {
            //fecha o arquivo
            if (arquivo != null) {
                try {
                    arquivo.close();
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("\nLeitura feita com sucesso!");
            }
        }
        return false;
    }

    //Método patra determinar a origem e o destino de busca no arquivo 
    public static void main(String[] args) {
        /*
        Adiciona vértices e seus respectivos custos
        grafoteste.addAresta(0, 1, 10);
        grafoteste.addAresta(0, 2, 5);
        grafoteste.addAresta(1, 3, 1);
        grafoteste.addAresta(2, 1, 3);
        grafoteste.addAresta(2, 3, 8);
        grafoteste.addAresta(2, 4, 2);
        grafoteste.addAresta(3, 4, 4);
        grafoteste.addAresta(3, 5, 4);
        grafoteste.addAresta(4, 6, 5);
         */

        //Inicializo a leitura de arquivo diretamente pois o método é static
        readFile();
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o vértice de origem da rota:");
        int origem = Integer.parseInt(ler.nextLine().trim());

        System.out.println("Digite o vértice de destino para a rota:");
        int destino = Integer.parseInt(ler.nextLine().trim());

        //Passa os valores para o algoritmo determinar a menor rota
        grafo.Dijkstra(origem, destino);

    }
}
