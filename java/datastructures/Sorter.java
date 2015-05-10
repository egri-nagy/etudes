package datastructures;

import java.util.Arrays;

//methods for sorting arrays of Comparables
public class Sorter{
    public static Comparable[] heapSort(Comparable[] coll){
        Comparable[] sorted = new Comparable[coll.length];
        Heap heap = new Heap();
        Arrays.stream(coll).forEach(x->heap.insert(x));
        for (int i = coll.length-1; i >= 0; i--){
            sorted[i] = heap.extract_max();
        }
        return sorted;
    }

    public static void main (String[] args){
        Arrays.stream(heapSort(new String[]{"xx", "blah", "hey", "yo"}))
            .forEach(System.out::println);
    }
}
