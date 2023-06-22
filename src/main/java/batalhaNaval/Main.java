package batalhaNaval;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Jogador jogador1 = new Jogador();
        Jogador jogador2 = new Jogador();
        boolean isPvp;
        boolean isManual;

        System.out.println("Escolha o modo de jogo: \n[1] Jogador x Jogador\n[2] Jogador x CPU");
        isPvp = 1 == scan.nextInt();

        System.out.println("Insira o nome do jogador 1:");
        jogador1.nome = scan.next();

        if (isPvp){
            System.out.println("Insira o nome do jogador 2:");
            jogador2.nome = scan.next();
        }else {
            jogador2.nome = "Computador";
        }

        System.out.println("Jogador 1: Escolha o modo de preenchimento: \n[1] Automático \n[2] Manual");
        isManual = 2 == scan.nextInt();

        jogador1.preencher(4, isManual);
        jogador1.preencher(3, isManual);
        jogador1.preencher(3, isManual);
        jogador1.preencher(2, isManual);
        jogador1.preencher(2, isManual);
        jogador1.preencher(2, isManual);
        jogador1.preencher(1, isManual);
        jogador1.preencher(1, isManual);
        jogador1.preencher(1, isManual);
        jogador1.preencher(1, isManual);

        if(isPvp) {
            System.out.println("Jogador 2: Escolha o modo de preenchimento: \n[1] Automático \n[2] Manual");
            isManual = 2 == scan.nextInt();
        }else
            isManual = false;

        jogador2.preencher(4, isManual);
        jogador2.preencher(3, isManual);
        jogador2.preencher(3, isManual);
        jogador2.preencher(2, isManual);
        jogador2.preencher(2, isManual);
        jogador2.preencher(2, isManual);
        jogador2.preencher(1, isManual);
        jogador2.preencher(1, isManual);
        jogador2.preencher(1, isManual);
        jogador2.preencher(1, isManual);

        Jogador vencedor = jogo(jogador1, jogador2, isPvp);

        System.out.println("\n\nFIM DO JOGO!!");
        System.out.println("\n"+vencedor.nome+" venceu!!");

        jogador1.jogador.printMapa();
        jogador2.jogador.printMapa();






    }
    static public Jogador jogo(Jogador p1, Jogador p2, boolean isPvp){
        Random rand = new Random();
        boolean continuar = true;

        if(rand.nextBoolean())
        {
            p1.atacar(p2,false);
        }
        do{
            p2.atacar(p1, !isPvp);
            continuar = !p2.verificarDerrota();
            if (continuar)
            {
                p1.atacar(p2, false);
                continuar = !p1.verificarDerrota();
            }
        }while(continuar);

        if(p1.verificarDerrota())
            return p2;
        else
            return p1;

    }

}
