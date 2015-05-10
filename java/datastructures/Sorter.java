package datastructures;

import java.util.Arrays;

//methods for sorting arrays of Comparables
public class Sorter{
    ///HEAPSORT/////////////////////////////////////////////////
    public static Comparable[] heapSort(Comparable[] coll){
        Comparable[] sorted = new Comparable[coll.length];
        Heap heap = new Heap();
        Arrays.stream(coll).forEach(x->heap.insert(x));
        for (int i = coll.length-1; i >= 0; i--){
            sorted[i] = heap.extract_max();
        }
        return sorted;
    }

    //.QUICKSORT//////////////////////////////////////////////
    public static void quickSort(Comparable[] coll){
        quickSort(coll, 0, coll.length-1);
    }
    public static void quickSort(Comparable[] coll, int p, int r){
        if (p >= r) return;
        int q = partition(coll, p, r);
        quickSort(coll, p, q-1);
        quickSort(coll, q+1, r);
    }
    private static int partition(Comparable[] coll, int p, int r){
        Comparable pivot = coll[r];
        int i = p-1;
        for (int j = p; j < r; j++){
            if (coll[j].compareTo(pivot) < 1){
                i++;
                swap(coll, i,j);
            }
        }
        swap(coll, i+1, r); //putting the pivot in the 'middle'
        return i + 1;
    }
    //just a classic swap in the list
    private static void swap(Comparable[] coll, int idx1, int idx2){
        Comparable val1 = coll[idx1];
        coll[idx1] = coll[idx2];
        coll[idx2] = val1;
    }


    ///MERGE SORT////////////////////////////////////////////////////
    // p - start, q - finish
    public static Comparable[] mergeSort(Comparable[] coll, int p, int q){
        if ((q-p)==1) { return new Comparable[]{coll[p]};}
        int mid = (q-p)/2;
        Comparable[] l1 = mergeSort(coll,p, p+mid);
        Comparable[] l2 = mergeSort(coll,p+mid, q);
        //now merging
        Comparable[] result = new Comparable[l1.length + l2.length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < result.length; i++){
            if (j == l1.length) { //we've exhausted l1
                System.arraycopy(l2, k, result, i, result.length-i);break;
            }
            else if (k == l2.length){ //we've exhausted l2
                System.arraycopy(l1, j, result, i, result.length-i);break;
            }
            else{
                if (l1[j].compareTo(l2[k])<=0){
                    result[i] = l1[j];
                    j++;
                }
                else{
                    result[i] = l2[k];
                    k++;
                }
            }
        }
        return result;
    }



    public static void main (String[] args){
        String[] words = new String[]{"xx", "blah", "hey", "yo"};
        //quickSort(words);
        //Arrays.stream(words).forEach(System.out::println);
        Arrays.stream(mergeSort(words,0, words.length)).forEach(System.out::println);
    }
}
