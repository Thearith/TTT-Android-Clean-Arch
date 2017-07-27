package thearith.com.tictactoe.cross.utils;

import java.util.ArrayList;
import java.util.List;

import thearith.com.tictactoe.cross.model.PlayerType;

/**
 * Created by Thearith on 7/27/17.
 */

public class ArrayUtils {

    public static <T extends Object> T[] flatten(T[][] array) {
        if(array != null) {
            List<T> list = new ArrayList<T>(array.length * array.length);
            for(T[] row : array) {
                for(T elem : row) {
                    list.add(elem);
                }
            }

            return (T[]) list.toArray();
        }

        return null;
    }


    public static PlayerType[] initArray(PlayerType defaultValue, int size) {
        PlayerType[] array = new PlayerType[size*size];
        for(int i=0; i<size*size; i++) {
            array[i] = defaultValue;
        }

        return array;
    }

    public static PlayerType[][] init2DArray(PlayerType defaultValue, int size) {
        PlayerType[][] scores = new PlayerType[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                scores[i][j] = defaultValue;
            }
        }

        return scores;
    }


    public static PlayerType[][] copy2DArray(PlayerType[][] array) {
        int size = array.length;
        PlayerType[][] scores = new PlayerType[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                scores[i][j] = array[i][j];
            }
        }

        return scores;
    }

}
