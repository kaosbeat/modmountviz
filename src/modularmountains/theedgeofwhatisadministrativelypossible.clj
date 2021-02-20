(ns modularmountains.theedgeofwhatisadministrativelypossible

  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]
   [modularmountains.core :as c]
   [modularmountains.osc :as o]
   ))


(def data (atom {:size 100
                 :rot 0.5
                 :glitch 0
                 :xsize 5 :ysize 5 :zsize 5
                 :innerradius 10
                 :strokeweight 3
                 :alpha 255
                 :ellipsoid 1.0
                 }) )
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


(defn pushrandomdata [data seed]
  (q/random-seed seed)
  (let [dim  (/ (@c/opz1 :c1) 8) ]
    (swap! data conj {:size 200
                 :rot (q/random 100)
                 :glitch 10
                 :xsize 5 :ysize 5 :zsize 5
                 :innerradius 3
                 :strokeweight (q/random 1)
                 :alpha 250
                 :ellipsoid 1.0
                 }  ))

  )

(defn datadebug [x y ts data]
                                        ;(q/text-font font)
  (q/text-size ts)
  (q/with-translation [x y 0]

    (dotimes [n (count @data)]
      (q/fill 255)
      (q/stroke 255 0 255)
      ;(q/text (str (mm/pirad)) 0 20)
                                        ;(q/text "bklad" 200 300)
      (q/text (name (nth (map first @data) n))  0 (* 1.2 (* n ts)) )
      (q/text (str (float (nth (map second @data) n))) (* ts 6)  (* 1.2 (* n ts)) )
      ;(q/text (str @inputdata) 0 0 )
      )
    (q/text "The Edge ..." 10 -10)
                                        ;    (println "blah")


                                        ;      (with-open [fd (clojure.java.io/writer "foo.txt")] (binding [*out* fd] (println (str "data " (get @data :size))) (println (str (mm/pirad)))))
    )

  )

(defn draw []
  (pushrandomdata data (@c/channelseeds :edge))

  (q/stroke 255 2 5 (@data :alpha))
  (q/fill 255 255 25 0 )


                                        ;(println (get @data :glitch))

  (q/stroke-weight (@data :strokeweight))
  (q/with-translation [(/  (q/width) 2) (/ (q/height) 2) 0 ]
    (q/with-rotation [(@data :rot) 0 1 1]
      (q/with-translation [0 0 -500]
                                        ;(q/box 100)
                                        ;(arcSeq 1 20 0.5 0.9 0.2 0 1000 1000)


        (let [baseradius   80
              interradius  10
              amount       @currentamount
              steps        @currentsteps
              fillrate     (/ (q/random 100) 1000)
                                        ;seed @currentseed
              seed         (@data :seed)
              centeroffset @currentcenteroffset
              centerY      100
              centerX      10]


          (dotimes [n amount]
                                        ;            (q/random-seed n)
            (q/with-rotation [(q/random 0 6.28) 0 0 0]
              (arcSeq (+ seed  n) (+ steps n)  0.2  fillrate (- (/ centeroffset  2)(q/random centeroffset) ) (- (/ centeroffset  2)(q/random centeroffset) )  (+ baseradius (* n interradius))  (+ baseradius (* n interradius))  )

                                        ;
              (q/stroke 25 (q/random 255) 25 (@data :alpha))
              (arcSeq (+ seed  n) (+ steps n)  0.2  fillrate (- (/ centeroffset  2)(q/random centeroffset) ) (- (/ centeroffset  2)(q/random centeroffset) ) (* (@data :ellipsoid) (+ baseradius (* n interradius)))  (* 3 (+ baseradius (* n interradius))))
              )
            ))
        )))
  (datadebug  1400 50 20 data)
  )
