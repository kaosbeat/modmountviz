(ns modularmountains.distressfrequency
  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]
   [modularmountains.core :as c]))


(def data (atom {:size 100
                 :rot 0.5
                 :glitch 0
                 :noise 10
                 :xsize 3
                 :ysize 3
                 :zsize 3
                 :depth 10
                 :seed 10
                 :strokeweight 1
                 :r 255
                 :g 255
                 :b 255
                 :alpha 120
                 }) )
;(def font modularmountains.core/debugfont)
(def xlist (atom []))
(def ylist (atom []))
(def zlist (atom []))

(swap! data assoc :depth 12)

(defn pushrandomdata [data seed]
  (q/random-seed seed)
  (let [dim  (/ (@c/opz1 :c1) 8) ]
    (swap! data conj {
                      :size (* 10 (q/random 100))
                 :rot (q/random 1000)
                 :glitch 10
                 :noise 10
                 :xsize 40
                 :ysize 3
                 :zsize 3
                 :depth (* (q/random 10) (@c/opz2 :c2))
                 :seed 100
                 :strokeweight 10
                 :r 255
                 :g 255
                 :b 25
                 :alpha 120
                 }  ))

  )





(defn datadebug [x y ts data]
  ;(q/text-font font)
  (q/text-size ts)
  (q/with-translation [x y 0]

    (dotimes [n (count @data)]
      (q/fill 255)
      (q/stroke 255)
      ;(q/text "bklad" 200 300)
      (q/text (name (nth (map first @data) n))  0 (* 1.2 (* n ts)) )
      (q/text (str (float (nth (map second @data) n))) (* ts 4)  (* 1.2 (* n ts)) )

      )
    (q/text "Distress Frequency" 0 -10)

    )
  )

(defn draw []

    (pushrandomdata data (@c/channelseeds :freq))

  (reset! xlist [0])
  (reset! ylist [0])
  (reset! zlist [0])
                                        ;  (q/color-mode :rgb)
                                        ;  (println (:size data))
                                        ;(q/background 20)

  (q/fill 255 255 25 20 )

                                        ;(println (get @data :glitch))

  (let [
        xoffset      (/ (q/width) 2)
        yoffset      (/ (q/height) 2)
        zoffset     -500
        seed         (@data :seed)
        noise        15
        noiseX       (rand-int (* 5 noise))
        noiseY       (rand-int (* 5 noise))
        noiseZ       (rand-int (* 5 noise))
        rot          (@data :rot)
        rotx         1
        roty         1
        rotz         1
        rotmod       (mm/pirad)
        xsize        (get @data :xsize)
        ysize        (get @data :ysize)
        zsize        (get @data :zsize)
        size         (get @data :size)
        mmsize       (mm/hundredsteps)
        sqtresh      0.1
        dx           0
        iy           0
        dy           0
        iz           0
        dz           0

        ]

    (q/random-seed seed)
    (q/stroke-weight (@data :strokeweight))
    (q/stroke (@data :r) (@data :g) (@data :b) (@data :alpha))
    (q/with-translation [xoffset yoffset  zoffset]

      (q/with-rotation [rot rotx roty rotz ]

        (dotimes [n (@data  :depth)]
          (let  [b (int (q/random 0 2))
                 d (int (q/random 0 2))
                 h (int (q/random 0 2))
                 dx (if (= b 1) (q/random 0 size) 0)
                 dy (if (= d 1) (q/random 0 size) 0)
                 dz (if (= h 1) (q/random 0 size) 0)
                 ]
                                        ;(println dx)

            (if (= dx 0) (swap! xlist conj (peek @xlist))  (swap! xlist conj dx))
            (if (= dy 0) (swap! ylist conj (peek @ylist))  (swap! ylist conj dy))
            (if (= dz 0) (swap! zlist conj (peek @zlist))  (swap! zlist conj dz))
                                        ; (println zlist)
            )
                                        ;(dotimes [n (- depth 0)]) ; (println (nth @xlist (+ n)))

          (q/with-translation [(- 0 (/ size 2)) (- 0  (/ size 2)) (- 0  (/ size 2)) ]
            (q/line (+ noiseX  (nth @xlist n)) (+ noiseY (nth @ylist n)) (+ noiseZ  (nth @zlist n)) (+ noiseX (nth @xlist (+ n 1))) (+ noiseY  (nth @ylist (+ 1 n))) (+ noiseZ (nth @zlist (+ n 1))))

            ))
                                        ; (q/line 0.0 10 100 100)
                                        ; (q/line 0 0 0  (nth [20 220 131 3344 243 219 99 33 0] n ) 1000 -1000)





        )))

  (q/with-translation [1300 650 500]
    (datadebug  20 50 10 data)))
