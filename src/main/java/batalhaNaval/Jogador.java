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

        int valorX, valorY;
        boolean isVertical;

        // Se estiver no modo manual, pedirá ao usuário as informações necessárias.
        // Repete a coleta de valores até serem inseridos valores válidos.
        // No modo automático randomizará os valores.

        if (isManual){
            System.out.println("Seu mapa:");
            this.jogador.printMapa();
        }

        if(isManual){
            do{
                System.out.println("=== Valores do barco de tamanho "+tamanho+" ===");
                System.out.println("Digite a direção do barco: [0] Vertical [1] Horizontal");
                isVertical = 0 == scan.nextInt();
                System.out.println("Insira a posição da linha inicial do barco:");
                valorX = scan.nextInt();
                System.out.println("Insira a posição da coluna inicial do barco:");
                valorY = charToIntCoord(scan.next().toUpperCase().charAt(0));
            }while(jogador.colocarBarco(tamanho,valorX,valorY,isVertical));
        }else{
            do{
                isVertical = rand.nextBoolean();
                valorX = rand.nextInt(10);
                valorY = rand.nextInt(10);
            }while(jogador.colocarBarco(tamanho,valorX,valorY,isVertical));
        }
    }
    public int[] bombear(boolean auto) {

        // Recebe do jogador ou randomiza as coordenas do ataque, retorna como array.

        int valorX;
        int valorY;
        int[] coords = new int[2];
        if (!auto){
            do {
                System.out.println("Insira a posição da linha a ser bombeada:");
                valorX = scan.nextInt();
            } while (valorX < 0 || valorX > 9);
            coords[0] = valorX;
            do {
                System.out.println("Insira a posição da coluna a ser bombeada:");
                valorY = charToIntCoord(scan.next().toUpperCase().charAt(0));
            } while (valorY < 0 || valorY > 9);
            coords[1] = valorY;
        }else{
            coords[0] = rand.nextInt(10);
            coords[1] = rand.nextInt(10);
        }
        return coords;
    }
    public int charToIntCoord(char coordChar){

        // Recebe uma coordenada tipo char e retorna uma coodenada tipo int equivalente.

        return coordChar-65;
    }
    public void atacar(Jogador atacado, boolean auto){

        // Método que recebe as coordenadas bombeadas e verifica se já foram atacadas.
        // Passa as coordenadas para o adversário e recebe a char atacada.

        System.out.println("Rodada de "+this.nome);

        int[] coords;

        do{
            if(!auto)
            {
                System.out.println("Sua visão do mapa do oponente:");
                this.jogador.printVisao();
            }
            coords = this.bombear(auto);
        }while(jogador.tabelaVisao[coords[0]][coords[1]] != ' ');

        char local = atacado.jogador.receberAtaque(coords);

        this.jogador.receberVisao(coords, local);

        if (local == 'B')
            this.atacar(atacado, auto);
    }
    public boolean verificarDerrota(){
        return jogador.barcosRestantes < 1;
    }
}
