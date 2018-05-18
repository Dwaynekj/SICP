;; Non tail recursive pow
(defn pow [x y] 
  (cond 
    (= y 0) 
    1 
    :else 
    (* x (pow x (- y 1)))))

;; Tail recursive pow
(defn pow2 
  ([x y] (pow2 x y 1) )
  ([x y n]
   (if
    (= y 0) 
    n   
    (recur (x) (dec y) (* n x)))))



