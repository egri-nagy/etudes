;; Playing with lazily evaluated infinite lists.
;; Recipe: construct your list recursively, then call lazy-seq on that.

;; a function that return a lazy list of the natural numbers
(defn naturals
  ([] (naturals 0))
  ([n] (lazy-seq (cons n (naturals (inc n))))))

;; it is a function, so you have to call it to get the list
(take 42 (naturals))
