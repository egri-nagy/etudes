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
        T max = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        max_heapify(1);
        return max;
    }

    private void max_heapify(int root){
        int rootidx = indx(root);
        T rootval = list.get(rootidx);
        int largestidx = rootidx;
        T largestval = rootval;

        int leftidx = indx(left(root));
        int rightidx = indx(right(root));
        int[] childindices = {leftidx, rightidx};
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
            list.set(rootidx, largestval);
            list.set(largestidx, rootval);
            max_heapify(largestidx+1); //index correction
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
        heap.insert(3);
        heap.insert(333);
        heap.insert(2);
        System.out.println(heap);
        System.out.println(heap.extract_max());
    }
}
