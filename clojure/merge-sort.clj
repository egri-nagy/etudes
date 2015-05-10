(ns merge-sort)

(defn cut-in-half
  "cutting a collection into (almost) equal halves"
  [coll]
  (let [n (count coll)]
    (if (even? n)
      (partition-all (/ n 2) coll)
      (partition-all (/ (inc n) 2) coll))))

(defn merge-sort
  [coll]
  (if (or (empty? coll) (= 1 (count coll)))
    coll
    (let [[l1 l2] (cut-in-half coll)]
      (loop [r [] l1 (merge-sort l1) l2 (merge-sort l2)]
        (cond (empty? l1) (into r l2)
              (empty? l2) (into r l1)
              :else (if (> 0 (compare (first l1) (first l2)))
                      (recur (conj r (first l1)) (rest l1) l2)
                      (recur (conj r (first l2)) l1 (rest l2))))))))
