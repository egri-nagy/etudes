;; rewriting https://clojuredocs.org/clojure.core/lazy-seq#example-542692d3c026201cdc326ff1
;; Q: why the stack overflow?
(defn lazy-trialdivision
  ([] (clojuredocs-sieve (iterate inc 2))) ; lazy list 2,3,4,5,....
  ([[prime & nums] l] ; destructuring list of numbers, 1st is a prime
   (cons prime
         (lazy-seq
          (lazy-trialdivision
           (filter #(not= 0 (mod % prime)) nums))))))
