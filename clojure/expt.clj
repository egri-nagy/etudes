;; from SICP
(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (#(*' % %) (fast-expt b (/ n 2)))
        :else (*' b (fast-expt b (- n 1)))))

(fast-expt 2 32)
