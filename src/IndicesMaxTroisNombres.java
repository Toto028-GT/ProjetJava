import java.util.Arrays;

public class IndicesMaxTroisNombres {
    public static void main(String[] args) {
        int[] tableau = {10, 30, 50, 20, 40};
        int[] indicesMax = obtenirIndicesMaxTroisNombres(tableau);

        System.out.println("Indices des 3 nombres max : " + Arrays.toString(indicesMax));
    }

    public static int[] obtenirIndicesMaxTroisNombres(int[] tableau) {
        int[] indicesMax = new int[3];

        for (int i = 0; i < 3; i++) {
            indicesMax[i] = indiceMax(tableau);
            tableau[indicesMax[i]] = Integer.MIN_VALUE; // Mettre à une valeur très basse pour ne pas le réutiliser
        }

        return indicesMax;
    }

    public static int indiceMax(int[] tableau) {
        int indiceMax = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] > max) {
                max = tableau[i];
                indiceMax = i;
            }
        }

        return indiceMax;
    }
}