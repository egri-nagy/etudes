(import java.util.UUID)
(import datastructures.Sorter)

(def uuids (into-array
              (for [x (range 1 1000000)]
                (str (UUID/randomUUID)))))

(pprint "Heapsort")
(time (def heapsorted (Sorter/heapSort uuids)))
(pprint "Mergesort")
(time (def mergesorted (Sorter/mergeSort uuids)))
;;quicksort is in place
(pprint "Quicksort")
(time (Sorter/quickSort uuids))
;;as arrays they are different, but as sequences they should equal
(= (seq heapsorted ) (seq mergesorted) (seq uuids) (sort uuids))
