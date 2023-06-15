package batalhaNaval;

public class Mapa {
    public char[][] tabelaAtaques = new char[10][10];
    public char[][] tabelaVisao = new char[10][10];
    public char[][] tabelaBarcos = new char[10][10];

    public void preencherMapa(){
        for (int i = 0 ; i < 10; i++){
            for (int j = 0 ; j < 10; j++){
                tabelaBarcos[i][j] = 'A';
                tabelaAtaques[i][j] = ' ';
                tabelaVisao[i][j] = ' ';
            }
        }
    }

    public boolean colocarBarco(int tamanho, int x, int y, boolean isVertical){
        if (tamanho > 4 || tamanho < 1)
            return false;

        boolean isValid = true;

        for(int i=0 ; i<tamanho ; i++){
            if (isVertical){
                if(!isPlaceble(x+i,y))
                    isValid = false;
            }else{
                if(!isPlaceble(x,y+i))
                    isValid = false;
            }
        }

        if(isValid){
            for(int i=0 ; i<tamanho ; i++){
                if (isVertical) {
                    tabelaBarcos[x + i][y] = 'B';
                }else{
                    tabelaBarcos[x][y + i] = 'B';
                }
            }
            return true;
        }
        return false;
    }

    public boolean isPlaceble(int x, int y){
        if(x>=0 && x<=10 && y>=0 && y<=10){
            return tabelaBarcos[x][y] == 'A';
        }
        return false;
    }


}
