(ns expresso-2d.core
  (:use midje.sweet)
  (:use [numeric.expresso.core]))


(fact "I can find where two lines intersect"
  (solve '[x y]
    (ex (= y (* x 4/3)))         ;; y = 4/3 x
    (ex (= y (+ 8 (* x -4/3))))  ;; y = 8 - 4/3 x
    ) => '#{{x 3N, y 4N}}        ;; this one's right
  )


(fact "I can tell when two lines don't intersect"
  (solve '[x y]
    (ex (= y (* x 4/3)))       ;; y = 4/3 x
    (ex (= y (+ 1 (* x 4/3)))) ;; y = 1 + 4/3 x
    ) => '#{{}}                ;; this one's right, 
                               ;; but it's the same damned answer as the ones below
                               ;; which are wrong
  )


(fact "I can find where a circle and line intersect"
  (solve '[x y]
    (ex (= y (* x 4/3)))                           ;; y = 4/3 x
    (ex (= 25 (+ (** (- x 0) 2) (** (- y 0) 2))))  ;; x^2 + y^2 = 25
    ) =not=> #{{}}                                 ;; NO; the right answer is 
                                                   ;; #{{x 3 y 4} {x -3 y -4}}
  )


(fact "I can find where two circles intersect"
  (solve '[x y]
    (ex (= 25 (+ (* (- x 6) (- x 6)) (* (- y 0) (- y 0)))))  ;; (x-6)^2 + y^2 = 25
    (ex (= 25 (+ (** (- x 0) 2) (** (- y 0) 2))))            ;; x^2 + y^2 = 25
    ) =not=> #{{}}                                           ;; NO; the right answer is 
                                                             ;; #{{x 3 y 4}{x 3 y -4}}
  )
