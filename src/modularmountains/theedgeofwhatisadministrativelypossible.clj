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
(let [c1    (q/map-range  (get @c/cc :c1) 0 127 1 20 )
       c2    (q/map-range  (get @c/cc :c2) 0 127 3 1)
       c3    (q/map-range  (get @c/cc :c3) 0 127 0.99 0.1)
       c4    (q/map-range  (get @c/cc :c4) 0 127 0 127 )
       c5    (q/map-range  (get @c/cc :c5) 0 127 0 16 )
       c6    (q/map-range  (get @c/cc :c6) 0 127 0 16 )
       c7    (q/map-range  (get @c/cc :c7) 0 127 0 255 )
       c8    (q/map-range  (get @c/cc :c8) 0 127 0 255 )
       c9    (q/map-range  (get @c/cc :c9) 0 127 0 16 )
       c10   (q/map-range  (get @c/cc :c10) 0 127 0 16 )
       c11   (q/map-range  (get @c/cc :c11) 0 127 0 16 )
       c12   (q/map-range  (get @c/cc :c12) 0 127 0 16 )
       c13   (q/map-range  (get @c/cc :c13) 0 127 0 16 )
       c14   (q/map-range  (get @c/cc :c14) 0 127 0 16 )
       c15   (q/map-range  (get @c/cc :c15) 0 127 0 16 )
       c16   (q/map-range  (get @c/cc :c16) 0 127 0 16 )

       env-l (q/map-range (@c/audio-l :env) 0 127 0 1)
       env-r (q/map-range (@c/audio-r :env) 0 127 0 1)


       s1    (q/map-range (@c/nano :s1) 0 127 10 255)
       k1    (q/map-range (@c/nano :k1) 0 127 1 40)
       b1a   (@c/nano :b1a)
       b1b   (@c/nano :b1b)

       s2    (q/map-range (@c/nano :s2) 0 127 0 127)
       k2    (q/map-range (@c/nano :k2) 0 127 1 3)
       b2a   (@c/nano :b2a)
       b2b   (@c/nano :b2b)

       s3    (q/map-range (@c/nano :s3) 0 127 1 250)
       k3    (q/map-range (@c/nano :k3) 0 127 0 6.28)
       b3a   (@c/nano :b3a)
       b3b   (@c/nano :b3b)

       s4    (q/map-range (@c/nano :s4) 0 127 1 50)
       k4    (q/map-range (@c/nano :k4) 0 127 0.1 50)
       b4a   (@c/nano :b4a)
       b4b   (@c/nano :b4b)
      ]

(if (= 1 b1a)
      (swap! data assoc :strokeweight (* c1 k1))
      (swap! data assoc :strokeweight  k1)
      )
(swap! data assoc :alpha s1)

(if (= 1 b2a)
  (swap! data assoc :ellipsoid c2)
  (swap! data assoc :ellipsoid k2))

(if (= 1 b3a)
  (swap! data assoc :rot c3)
  (swap! data assoc :rot k3))

(if (= 1 b4a )
  (swap! data assoc :rot c3)
  )
(if (= 1 b1b)
  (if (> 4 c9)
    (swap! data assoc :seed (rand-int 100)))

  (swap! data assoc :seed s2))
                                       ;
                                        ; (q/background 0)
  (q/stroke 255 255 255 (@data :alpha))
  (q/fill 255 255 25 0 )


                                        ;(println (get @data :glitch))
  (q/stroke-weight (@data :strokeweight))
  (q/with-translation [(/  (q/width) 2) (/ (q/height) 2) 0 ]
    (q/with-rotation [(@data :rot) 0 0 1]
      (q/with-translation [0 0 -500]
                                        ;(q/box 100)
                                        ;(arcSeq 1 20 0.5 0.9 0.2 0 1000 1000)


        (let [baseradius   c8
              interradius  10
              amount       @currentamount
              steps        @currentsteps
              fillrate     0.2
                                        ;seed @currentseed
              seed         (@data :seed)
              centeroffset @currentcenteroffset
              centerY      100
              centerX      100]


          (dotimes [n amount]
                                        ;            (q/random-seed n)
            (q/with-rotation [(q/random 0 6.28) 0 0 0]
              (arcSeq (+ seed  n) (+ steps n)  0.2  fillrate (- (/ centeroffset  2)(q/random centeroffset) ) (- (/ centeroffset  2)(q/random centeroffset) )  (+ baseradius (* n interradius))  (+ baseradius (* n interradius))  )

              (arcSeq (+ seed  n) (+ steps n)  0.2  fillrate (- (/ centeroffset  2)(q/random centeroffset) ) (- (/ centeroffset  2)(q/random centeroffset) ) (* (@data :ellipsoid) (+ baseradius (* n interradius)))  (* 3 (+ baseradius (* n interradius))))
              )
            ))
        ))))
  (datadebug  1400 50 20 data)
  )
