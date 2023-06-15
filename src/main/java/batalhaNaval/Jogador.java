package batalhaNaval;

import java.util.Random;
import java.util.Scanner;

public class Jogador {
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    static Mapa jogador = new Mapa();

    static {
        jogador.preencherMapa();
    }

    public String nome;
    public void preencher(int tamanho, boolean isManual){
        int valorX = -1, valorY = -1;
        boolean isVertical = false;
        if(isManual){
            do{
                System.out.println("Digite a direção do barco: [1] Vertical [0] Horizontal");
                isVertical = 1 == scan.nextInt();
                System.out.println("Insira a posição da linha inicial do barco:");
                valorX = scan.nextInt();
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
}
