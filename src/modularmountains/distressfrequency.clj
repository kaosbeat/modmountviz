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
                 :alpha 120}) )
;(def font modularmountains.core/debugfont)
(def xlist (atom []))
(def ylist (atom []))
(def zlist (atom []))

(swap! data assoc :depth 12)

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
  ( let [c1    (q/map-range  (get @c/cc :c1) 0 127 0 16 )
       c2    (q/map-range  (get @c/cc :c2) 0 127 0 1 )
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
       k1    (q/map-range (@c/nano :k1) 0 127 0 40)
       b1a   (@c/nano :b1a)
       b1b   (@c/nano :b1b)

       s2    (q/map-range (@c/nano :s2) 0 127 0 1)
       k2    (q/map-range (@c/nano :k2) 0 127 0 1)
       b2a   (@c/nano :b2a)
       b2b   (@c/nano :b2b)

       s3    (q/map-range (@c/nano :s3) 0 127 1 250)
       k3    (q/map-range (@c/nano :k3) 0 127 0 2500)
       b3a   (@c/nano :b3a)
       b3b   (@c/nano :b3b)

       s4    (q/map-range (@c/nano :s4) 0 127 1 50)
       k4    (q/map-range (@c/nano :k4) 0 127 0.1 50)
       b4a   (@c/nano :b4a)
       b4b   (@c/nano :b4b)
       ]




   (if (= b2a 1)
     (swap! data assoc :depth (* 200 (* k2 c2)))
     (swap! data assoc :depth (* 200 k2)))

   (if (= b3a 1)

     (swap! data assoc :rot (mm/piradfast)))

   (if (= b4a 1)
      (if  (< 12 c10)
        (swap! data assoc :rot (do  (mm/multirad (- 127 c10)) (mm/pirad)))))

   (if (< 5 c1)
     (swap! data assoc :seed (rand-int 100)))

   (if (= b1a 1)
     (if (< 5 c5)
       (swap! data assoc :strokeweight (+ 1 (rand-int 40))))
     (swap! data assoc :strokeweight k1))

 (swap! data assoc :alpha s1)
   (if (< 0 c7)
     (swap! data assoc :r c7))
   (if (< 0 c8)
     (swap! data assoc :b c8))

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
         noise        env-l
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
         size         900
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





         ))))

  (q/with-translation [1300 650 500]
    (datadebug  20 50 10 data))
  )
