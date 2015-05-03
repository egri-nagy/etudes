/*
 * Generic implementation of the heap data structure.
 * Favoring readability instead of low level optimization.
 * A heap can store Comparable elements.
 */
package datastructures;

import java.util.List;
import java.util.ArrayList;


public class Heap<T extends Comparable<T>>{
    private  List<T> list; //the binary tree elements are stored in a list

    //helper functions for calculating indices of binary tree elements
    private static int left(int i) {return 2*i;}
    private static int right(int i) {return (2*i)+1;}
    private static int parent(int i) {return (int) (i/ 2);}
    private static int indx(int i) {return i-1;} //dodging 0-based indexing
    //just a classic swap in the list
    private void swap(int idx1, int idx2){
        T val1 = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, val1);
    }

    //constructing an empty heap
    public Heap(){
        list = new ArrayList<T>();
    }

    ///INSERT///////////////////////////////////////////////////////////////////
    public void insert(T t){
        list.add(t);
        int idx = indx(list.size());
        bubble_up(list.size());
    }

    private void bubble_up(int k){
        if (k == 1) return;
        int p = parent(k);
        int pidx = indx(p);
        int kidx = indx(k);
        T pval = list.get(pidx);
        T kval = list.get(kidx);
        if (kval.compareTo(pval)>0){
            swap(pidx,kidx);
            bubble_up(p);
        }
    }

    ///EXTRACT//////////////////////////////////////////////////////////////////
    public T extract_max(){
        T max = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        max_heapify(1);
        return max;
    }

    private void max_heapify(int root){
        int rootidx = indx(root);
        T rootval = list.get(rootidx);
        int largestidx = rootidx; //assuming first that the root is the largest
        T largestval = rootval;

        //checking children, update largest if needed
        int[] childindices = {indx(left(root)), indx(right(root))};
        for (int childidx : childindices){
            if (childidx < list.size()) {
                T childval = list.get(childidx);
                if (childval.compareTo(largestval) > 0){
                    largestidx = childidx;
                    largestval = childval;
                }
            }
        }
        if (largestidx != rootidx){
            swap(largestidx, rootidx);
            max_heapify(largestidx+1); //index correction
        }
    }

    public String toString(){
        return list.toString();
    }

    //better to import the class into Clojure REPL and play with Heap there
    public static void main(String[] args){
        Heap<Integer> heap = new Heap<Integer>();
        heap.insert(42);
        heap.insert(6);
        heap.insert(7);
        heap.insert(152);
        heap.insert(3);
        heap.insert(333);
        heap.insert(2);
        System.out.println(heap);
        System.out.println(heap.extract_max());
        System.out.println(heap.extract_max());
        System.out.println(heap.extract_max());
    }
}
