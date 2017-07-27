package thearith.com.tictactoe.cross.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thearith on 7/27/17.
 */

public class ArrayUtils {

    public static <T> List<T> flatten(List<List<T>> list) {
        if(list != null) {
            List<T> flatList = new ArrayList<>(list.size() * list.size());
            for(List<T> small : list) {
                Collections.copy(flatList, small);
            }

            return flatList;
        }

        return null;
    }


    public static <T> List<T> initArray(T defaultValue, int size) {
        List<T> list = new ArrayList<>();
        for(int i=0; i<size; i++) {
            list.add(defaultValue);
        }

        return list;
    }

    public static <T> List<T> copyArray(List<T> list) {
        List<T> copy = new ArrayList<>();
        Collections.copy(copy, list);
        return copy;
    }

    public static <T> List<List<T>> init2DArray(T defaultValue, int size) {
        List<List<T>> list = new ArrayList<>();
        for(int i=0; i<size; i++) {
            List<T> innerList = initArray(defaultValue, size);
            list.add(innerList);
        }

        return list;
    }

    public static <T> List<List<T>> copy2DArray(List<List<T>> list) {
        if(list != null) {
            List<List<T>> copy = new ArrayList<>();
            for(List<T> innerList : list) {
                List<T> copyInnerList = new ArrayList<>();
                Collections.copy(copyInnerList, innerList);
                copy.add(copyInnerList);
            }

            return copy;

        }

        return null;
    }

}
