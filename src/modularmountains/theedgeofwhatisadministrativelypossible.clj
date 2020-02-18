(ns modularmountains.theedgeofwhatisadministrativelypossible

  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]
   [modularmountains.osc :as o]
   ))


(def data (atom {:size 100 :rot 0.5 :glitch 0 :xsize 5 :ysize 5 :zsize 5}) )
;(def font modularmountains.core/debugfont)

(def currentseed (atom 0))
(def currentcenteroffset (atom 6))
(def currentbaseradius (atom 60))
(def currentamount (atom 100))
(def currentsteps (atom 40))

;(osc-handle o/OSCs "/ch2attack" (fn [msg] (reset! currentbaseradius (rand-int 1000))))
;(osc-handle o/OSCs "/ch1" (fn [msg] (reset! currentsteps (nth (get msg :args) 0))))
;


(defn arcSeq [seed steps deviation fillrate x y width height]
  (if-not (= seed 0)
    (q/random-seed seed))
  (let [step (/ 6.28 steps)
        arcseq (atom  [])]
    (dotimes [n steps]
      (if (< (q/random 1.0) fillrate)
        (swap! arcseq conj [(* n step) (+ (* n step) (q/random deviation))])))
    (dotimes [n (count @arcseq)]
      (q/with-translation [x y 0]
        (q/arc x y width height (first (nth @arcseq n))  (last (nth @arcseq n)) )))
    )
  )



(defn draw []
  (q/background 0)
  (q/stroke 255 255 255 120)
  (q/fill 255 255 25 0 )


;(println (get @data :glitch))
  (q/stroke-weight 1)
  (q/with-translation [(/  (q/width) 2) (/ (q/height) 2) 0 ]
    (q/with-rotation [(mm/pirad) 0 0 1]
      (q/with-translation [0 0 -500]
                                        ;(q/box 100)
        ;(arcSeq 1 20 0.5 0.9 0.2 0 1000 1000)


        (let [baseradius   @o/ch2attack
              interradius  1
              amount       @currentamount
              steps        @currentsteps
              fillrate     0.05
                                        ;seed @currentseed
              seed         40
              centeroffset @currentcenteroffset
              centerY      100
              centerX      100]


          (dotimes [n amount]
;            (q/random-seed n)
            (q/with-rotation [(q/random 0 6.28) 0 0 1]
              (arcSeq (+ seed  n) (+ steps n)  0.2  fillrate (- (/ centeroffset  2)(q/random centeroffset) ) (- (/ centeroffset  2)(q/random centeroffset) )  (+ baseradius (* n interradius))  (+ baseradius (* n interradius))  )

              (arcSeq (+ seed  n) (+ steps n)  0.2  fillrate (- (/ centeroffset  2)(q/random centeroffset) ) (- (/ centeroffset  2)(q/random centeroffset) ) (* 1.3 (+ baseradius (* n interradius)))  (* 3 (+ baseradius (* n interradius))))
              )
            ))
        )))
  )


(defn updater []

  )
