;; rewriting https://clojuredocs.org/clojure.core/lazy-seq#example-542692d3c026201cdc326ff1
;; Q: why the stack overflow?
(defn lazy-trialdivision
  ([] (lazy-trialdivision (iterate inc 2))) ; lazy list 2,3,4,5,....
  ([[prime & nums]] ;destructuring list of numbers, 1st is a prime
   (cons prime
         (lazy-seq
          (lazy-trialdivision (filter #(not= 0 (mod % prime)) nums))))))

;; simplified from http://stackoverflow.com/questions/960980/fast-prime-number-generation-in-clojure
;; removed optimization for readability
(defn java-sieve [n]
  "returns a BitSet with bits set for each prime up to n"
  (let [bs (java.util.BitSet. n)]
    (.flip bs 2 n)
    (doseq [p (range 2 (Math/sqrt n))]
      (if (.get bs p)
        (doseq [q (range (* p p) n p)] (.clear bs q))))
    bs))
