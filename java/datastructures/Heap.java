package datastructures;

import java.util.List;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>>{
    private  List<T> list;

    public Heap(){
        list = new ArrayList<T>();
    }

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
            list.set(pidx, kval);
            list.set(kidx, pval);
            bubble_up(p);
        }
    }

    public T extract_max(){
        return null;
    }

    private void max_heapify(int start){
        int leftidx = indx(left(start));
        int rightidx = indx(right(start));
        int startidx = indx(start);
        T leftval = list.get(leftidx);
        T rightval = list.get(rightidx);
        T val = list.get(startidx);
        if ((val.compareTo(leftval) >= 0) && (val.compareTo(rightval)>=0)) {
            return;
        }
        if (leftval.compareTo(rightval) >= 0) {
            list.set(startidx, leftval);
            list.set(leftidx, val);
            max_heapify(left(start));
        }
        else{
            list.set(startidx, rightval);
            list.set(rightidx, val);
            max_heapify(right(start));
        }
    }

    private static int left(int i) {return 2*i;}
    private static int right(int i) {return (2*i)+1;}
    private static int parent(int i) {return (int) (i/ 2);}
    private static int indx(int i) {return i-1;} //dodging 0-based indexing

    public String toString(){
        return list.toString();
    }

    public static void main(String[] args){
        Heap<Integer> heap = new Heap<Integer>();
        heap.insert(42);
        heap.insert(6);
        heap.insert(7);
        heap.insert(152);
        System.out.println(Heap.left(1));
        System.out.println(Heap.parent(2));
        System.out.println(Heap.parent(3));
        System.out.println(heap);
    }
}
