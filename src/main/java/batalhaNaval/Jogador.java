package batalhaNaval;

import java.util.Random;
import java.util.Scanner;

public class Jogador {
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);
    Mapa jogador = new Mapa();
    public String nome;

    public Jogador() {
        // Configura o mapa
        jogador.preencherMapa();
    }


    public void preencher(int tamanho, boolean isManual){

        // Método para receber os valores e colocar um barco no mapa

        int valorX = -1, valorY = -1;
        boolean isVertical = false;

        // Se estiver no modo manual, pedirá ao usuário as informações necessárias.
        // Repete a coleta de valores até serem inseridos valores válidos.
        // No modo automático randomizará os valores.

        if(isManual){
            do{
                System.out.println("=== Valores do barco de tamanho "+tamanho+" ===");
                System.out.println("Digite a direção do barco: [1] Vertical [0] Horizontal");
                isVertical = 1 == scan.nextInt();
                System.out.println("Insira a posição da linha inicial do barco:");
                valorX = charToIntCoord(scan.next().toUpperCase().charAt(0));
                System.out.println("Insira a posição da coluna inicial do barco:");
                valorY = scan.nextInt();
            }while(jogador.colocarBarco(tamanho,valorX,valorY,isVertical));
        }else{
            do{
                isVertical = rand.nextBoolean();
                valorX = rand.nextInt(10);
                valorY = rand.nextInt(10);
            }while(jogador.colocarBarco(tamanho,valorX,valorY,isVertical));
        }
    }
    public int[] bombear(boolean isPvp) {

        // Recebe do jogador ou randomiza as coordenas do ataque, retorna como array.

        int valorX;
        int valorY;
        int[] coords = new int[2];
        if (isPvp){
            do {
                System.out.println("Insira a posição da linha a ser bombeada:");
                valorX = charToIntCoord(scan.next().toUpperCase().charAt(0));
            } while (valorX < 1 || valorX > 10);
            coords[0] = valorX;
            do {
                System.out.println("Insira a posição da coluna a ser bombeada:");
                valorY = scan.nextInt();
            } while (valorY < 1 || valorY > 10);
            coords[1] = valorY;
        }else{
            coords[0] = rand.nextInt(10);
            coords[1] = rand.nextInt(10);
        }
        return coords;
    }
    public int charToIntCoord(char coordChar){

        // Recebe uma coordenada tipo char e retorna uma coodenada tipo int equivalente.

        return coordChar-64;
    }
    public void atacar(Jogador atacado, boolean isPvp){

        // Método que recebe as coordenadas bombeadas e verifica se já foram atacadas.
        // Passa as coordenadas para o adversário e recebe a char atacada.

        int[] coords;

        do{
            coords = this.bombear(isPvp);
        }while(jogador.tabelaVisao[coords[0]][coords[1]] == ' ');

        this.jogador.receberVisao(coords, atacado.jogador.receberAtaque(coords));
    }
    public boolean verificarDerrota(){
        return jogador.barcosRestantes <= 1;
    }
}
