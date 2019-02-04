package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Darlan
 */
public final class Grafo {

    //Atributos para matriz de adjacencia
    private int numVertice;
    private int numAresta;
    private final int grafo[][];

    //Determina a quantidade de vértices com base no arquivo
    public Grafo(int n, int m) {
        this.grafo = new int[n][n];
        this.setNum(n, m);
        this.inicializa();
    }

    public void inicializa() {
        //inicilizo a matriz com valor 0
        for (int i = 0; i < numVertice; i++) {
            for (int j = 0; j < numVertice; j++) {
                grafo[i][j] = 0;
            }
        }
    }

    private void setNum(int n, int m) {
        if (n > 0 && m > 0) {
            this.numVertice = n;
            this.numAresta = m;
        }
    }

    /*método que irá adicionar arestas na matriz
    a aresta x para a y  tem o mesmo custo que y para x*/
    public void addAresta(int v1, int v2, int custo) {
        grafo[v1][v2] = custo;
        grafo[v2][v1] = custo;
    }

    //metodo usado para retornar o custo das arestas
    public int getCusto(int v1, int v2) {
        return grafo[v1][v2];
    }

    //Retorna os vizinhos de uma aresta passada como parametro
    public List<Integer> getVizinhos(int v1) {
        List<Integer> vizinhos = new ArrayList<>();
        //Percorre a linha referente ao grafo na matriz de adjacencia 
        for (int i = 0; i < grafo[v1].length; i++) {
            if (grafo[v1][i] > 0) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    public void Dijkstra(int origem, int destino) {
        /*Inicializando lista dos não vizitados
        set auxiliar com os nós não visitados*/
        Set<Integer> naoVisitados = new HashSet<>();
        for (int i = 0; i < numVertice; i++) {
            naoVisitados.add(i);
        }

        /*System.out.println("Inicializando lista dos custos
        vetor auxiliar para armazenar o custo do caminho de cada nó*/
        int[] custo = new int[numVertice];
        //inicializando o vetor com custo infinito 
        for (int i = 0; i < numVertice; i++) {
            custo[i] = 9999999;
        }
        //altera o custo para o nó de origem passando 0 
        custo[origem] = 0;

        /*Inicializando lista dos antecessores
        vetor que armazena o nó antecessor de menor custo */
        int[] antecessor = new int[numVertice];
        for (int i = 0; i < numVertice; i++) {
            antecessor[i] = -1;
        }

        //Percorre o grafo enquanto a lista dos nós não visitados não esta vazia 
        while (!naoVisitados.isEmpty()) {
            //começa com vertice de menor custo que neste caso seria a origem que é 0 e vai analisando seus vizinhos 
            int atual = maisProximo(custo, naoVisitados);
            //remove da lista o vértice que já foi visitado 
            naoVisitados.remove(atual);
            //analisa e altera o custo para todos os vizinhos
            for (int vizinho : getVizinhos(atual)) {
                int totalCusto = custo[atual] + getCusto(atual, vizinho);
                if (totalCusto < custo[vizinho]) {
                    custo[vizinho] = totalCusto;
                    antecessor[vizinho] = atual;
                }
            }
            //verifica se o vértice analisado é o destino 
            if (atual == destino) {
                System.out.println("A melhor rota foi traçada!!!");
                //se for a melhor rota será mostrado o caminho da solução 
                List<Integer> caminho = new ArrayList<>();
                caminho.add(atual);
                while (antecessor[atual] != -1) {
                    caminho.add(antecessor[atual]);
                    atual = antecessor[atual];
                }
                Collections.reverse(caminho);
                System.out.println("A melhor rota é: ");
                caminho.forEach((i) -> {
                    System.out.println(i);
                    //Mostra o custo do caminho ao longo dos vértices
                    //custo[i] += custo[i];
                    //System.out.println("O custo do caminho é: " + custo[6]);
                });
                System.exit(0);
            }
        }
    }

    /*método para pegar o vértice mais próximo 
    Usado para selecionar o proximo nó que será analisado*/
    private int maisProximo(int[] custo, Set<Integer> naoVisitados) {
        int minCusto = 9999999, minimo = 0;
        for (int i : naoVisitados) {
            if (custo[i] < minCusto) {
                minCusto = custo[i];
                minimo = i;
            }
        }
        return minimo;
    }
}
