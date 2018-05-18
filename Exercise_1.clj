;;Second Edition

;;1.1.1
;10 -> 10
;(+ 5 3 4) ->12
;(- 9 1) -> 8
;(/ 6 2) -> 3
;(+ (* 2 4) (- 4 6)) -> 6
;(define a 3) -> nil
;(define b (+ a 1)) -> nil
;(+ a b (* a b)) -> 19
;(= a b) -> false
;(if (and (> b a) (< b (* a b)))
;    b
;    a) -> 4 (b)
;(cond ((= a 4) 6)
;      ((= b 4) (+ 6 7 a))
;      (else 25)) -> 16
;(+ 2 (if (> b a) b a)) -> 6
;(* (cond ((> a b) a)
;         ((< a b) b)
;        (else -1))
;   (+ a 1)) -> 16

;;1.1.2
(/
 (+
  5
  4 
  (- 2
     (- 3 (+ 6 (/ 4 5)))))
  (* 3 (- 6 2) (- 2 7)))

;;1.1.3
(defn square [x] (* x x))


(defn mysort [a b c]
  (cond (and(> a b) (> b c)) (+ (square b) (square a))
        (and(> a b) (> c b)) (+ (square c) (square a))
        :else (+ (square b) (square c))))

(and (> 2 1) (> 3 2))
(mysort 5 2 3)

;; Answer I found online
(define (square x)
   (* x x))

(define (max x y)
   (if (> x y) x y))

(define (min x y)
   (if (< x y) x y))

(define (sum-of-highest-squares x y z)
     (+ (square (max x y))
       (square (max (min x y) z))))
;; End of Answer I found online


;1.1.4
(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))
;; Description: Means that you are adding
;; the absolute value of b to a
;; Interpreter: The if expression must first be evaluated by the interpreter
;; so that correct operator can be applied to a and b. If the b is a negtive integer it will result in a - operator and vice versa.


;1.1.5
(defn p [] (p)) ;Warned this is a recursive call
(defn test [x y]
  (if (= x ) 0 y))
;;Applicative order (test 0 (p))
;;(test 0 (p)) the operand will be evaluated first
;; (p) will infinitely recurse and will never be realized

;;Normal-Order (test 0 (p))
;;(test 0 (p))
;; The operands are not evaluated if they are not needed
;; The conditional in the body will always be evaluated to true and retun 0,
;; thus p never needs to be evaluated


;1.1.6
;; Because of applicative order evaluation on the interpreter parts the else-clause
;; will be evaluated first inside of the conditional which will lead to a recursive call of the same function

;1.1.7
(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn average [x y]
  (/ (+ x y) 2))

(defn good-enough? [guess x]
  (< (Math/abs (- (square guess) x)) 0.001))

(defn square [x]
  (* x x))

(defn mysqrt [x] (sqrt-iter 1.0 x))

;; Not effective for finding square roots of small numbers
;; Arbitrary precision is lost for numbers smaller than 3 decimal places
(* (mysqrt 0.0000002) (mysqrt 0.0000002))

;; Real computers have limited precision
(* (mysqrt 20000000) (mysqrt 20000000))

;;
(defn good-enough? [guess-old guess-new]
  (< (Math/abs (- guess-old guess-new)) 0.001))

(defn sqrt-iter [old-guess new-guess x]
  (if (good-enough? old-guess new-guess)
    new-guess
    (sqrt-iter new-guess (improve new-guess x) x)))

(defn mysqrt [x] (sqrt-iter 0 1.0 x))

(* (mysqrt 0.0000002) (mysqrt 0.0000002))

(* (mysqrt 20000000) (mysqrt 20000000))

;;Answer according to M
;; For very small numbers the absolute difference will ALWAYS be less than 0.001 so no many iterations are performed to refine that answer
;; For very large numbers the absolute difference will NEVER be less than 0.001 thus a non-halting program
;; Can be fixed by dividing the absolute difference by the original number to rooted

;;1.1.8
(defn cube-iter [guess x]
  (if (good-enough? guess x)
    guess
    (cube-iter (improve x guess) x)))

(defn improve [x y]
  ( / (+ (/ x (square y) (* 2 y))) 3))

(defn good-enough? [guess x]
  (< (Math/abs (- (cube guess) x)) 0.001))

(defn cube [x]
  (* x x x))

(defn mycube [x] (cube-iter 1.0 x))

;;??
;; Tail Recursive Factorial
(defn factorial [x]
  (loop x 1))

(defn loop [x, a]
  (if (= x 0) a (loop (dec x) (* a x))))

(factorial 4)

;;1.2.9
(defn + [a b]
  (if (= a 0)
    b
    (inc (+ (dec a) b))))

(+ 4 5)
(inc (+ 3 5))
(inc (inc (+ 2 5)))
(inc (inc (inc (+ 1 5))))
(inc (inc (inc (inc (+ 0 5)))))
(inc (inc (inc (inc 5))))
(inc (inc (inc 6)))
(inc (inc 7))
(inc 8)
(9)
9
;;recursive procedure. Recursive process

(defn + [a b]
  (if (= a 0)
    b
    (+ (dec a) (inc b))))

(+ 4 5)
(+ 3 6)
(+ 2 7)
(+ 1 8)
(+ 0 9)
(9)
9
;;recursive procedure. Iterative process


;;1.2.10
(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1) (A x (- y 1)))))

(A 1 10) ;=> 2^10
(A 0 (A 1 9))
(A 0 (A 0 (A 1 8)))
...
(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))
(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))

(A 2 4) ;=> 
(A 1 (A 2 3))
(A 1 (A 1 (A 2 2)))
(A 1 (A 1 (A 1 (2 1))))
(A 1 (A 1 (A 1 2)))
(A 1 (A 1 (A 0 (A 1 1))))
(A 1 (A 1 (A 0 2)))
(A 1 (A 1 4))
;;wtf!


(A 3 3) ;=> 
;;wtf!

;;1.2.11
;;Recursive Process
(defn f[n]
  (if (< n 3) 
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

;;iterative process
;;Whats the intuition??
(defn f[n]
  (if (< n 3) 
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

;;1.2.12
(defn pascalTri[n]
  (if 
    (or
      (= n 1)
      (= n 2) 
      (= n 3))
    1)
  ()
  )

