package batalhaNaval;

public class Mapa {
    public char[][] tabelaVisao = new char[10][10];
    // Matriz que mostrará a visão do jogador dessa classe ao campo do jogador inimigo.
    public char[][] tabelaBarcos = new char[10][10];
    // Matriz que guardará as posições dos barcos do jogador dessa classe.
    public int barcosRestantes = 20;

    public void preencherMapa(){

        // Método para definir todas as matrizes de mapa como água ('A') ou valor equivalente.

        for (int i = 0 ; i < 10; i++){
            for (int j = 0 ; j < 10; j++){
                tabelaBarcos[i][j] = 'A';
                tabelaVisao[i][j] = ' ';
            }
        }
    }

    public boolean colocarBarco(int tamanho, int x, int y, boolean isVertical){

        // Método para que valida e coloca um barco no mapa.

        // Validação do tamanho do barco.
        if (tamanho > 4 || tamanho < 1)
            return false;

        boolean isValid = true;

        // Chama o método isPlaceble para validar os espaços do barco.
        for(int i=0 ; i<tamanho ; i++){
            if (isVertical){
                if(isNotPlaceble(x + i, y))
                    isValid = false;
            }else{
                if(isNotPlaceble(x, y + i))
                    isValid = false;
            }
        }

        // Se for válido, coloca o barco.
        if(isValid){
            for(int i=0 ; i<tamanho ; i++){
                if (isVertical) {
                    tabelaBarcos[x + i][y] = 'B';
                }else{
                    tabelaBarcos[x][y + i] = 'B';
                }
            }
            return false;
        }
        return true;
    }

    public boolean isNotPlaceble(int x, int y){

        // Método que verifica se a posição do barco é válida.

        if(x>=0 && x<10 && y>=0 && y<10){
            return tabelaBarcos[x][y] != 'A';
        }
        return true;
    }

    public char receberAtaque(int[] coords){

        // Recebe as coordenadas bombeadas e retorna o char correspondente.
        // Caso acerte um barco, diminui a quantidade de barcos.

        char atacado = tabelaBarcos[coords[0]][coords[1]];
        if(atacado == 'B')
        {
            barcosRestantes--;
            tabelaBarcos[coords[0]][coords[1]] = '*';
        }

        return atacado;
    }

    public void receberVisao(int[] coords, char visao){

        // Recebe o char da célula que atacou e coloca na matriz.

        tabelaVisao[coords[0]][coords[1]] = visao;
    }

    public void printMapa()
    {
        char[] colunas = "ABCDEFGHIJ".toCharArray();

        System.out.print("    ");
        for (int i = 0 ; i < 10 ; i++)
            System.out.print(colunas[i]+" ");
        System.out.println("\n");

        for (int i = 0 ; i < 10 ; i++)
        {
            System.out.print(i+ "   ");
            for (int j = 0 ; j < 10 ; j++)
            {
                System.out.print(this.tabelaBarcos[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--- [LEGENDA] ---");
        System.out.println("A --> Água");
        System.out.println("B --> Barco");
        System.out.println("* --> Barco Destruído");

    }

    public void printVisao()
    {
        char[] colunas = "ABCDEFGHIJ".toCharArray();

        System.out.print("    ");
        for (int i = 0 ; i < 10 ; i++)
            System.out.print(colunas[i]+" ");
        System.out.println("\n");

        for (int i = 0 ; i < 10 ; i++)
        {
            System.out.print(i+ "   ");
            for (int j = 0 ; j < 10 ; j++)
            {
                System.out.print(this.tabelaVisao[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--- [LEGENDA] ---");
        System.out.println("A --> Água");
        System.out.println("B --> Barco");
        System.out.println("Vazio --> Não atacado");
    }
}
