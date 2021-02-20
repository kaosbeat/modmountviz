(ns modularmountains.mountaindrone
  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]
   [modularmountains.core :as c]))


(def data (atom {:size 1000
                 :rot 0.5
                 :glitch 0
                 :xsize 5
                 :ysize 50
                 :zsize 50
                 :xscale 10
                 :octaves 5
                 :detail 2.1
                 :spacingA 18
                 :spacingB 0.5
                 :spacingC 50
                 :strokeweight 2
                 :seed 1
                 }) )
;(def font modularmountains.core/debugfont)
(def xlist (atom []))
(def ylist (atom []))
(def zlist (atom []))




(def inputdata (atom []))
(def modulationdata (atom []))
(def points (atom []))

(defn modulatedata [listatom seed octaves detail len range base scale]
  (q/noise-seed seed)
  (q/noise-detail octaves detail)
  ;x, octaves=1, persistence=0.5, lacunarity=2.0, repeat=1024, base=0.0)

  (dotimes [n len]
    (let [p (/ (* scale (q/noise (+ base n))) len)]
      (swap! listatom conj p))))

(defn remapdata [listatom x1 x2 y1 y2]
  ;(println @listatom)
  )

(defn bounds [tree]
  (apply (juxt min max) (flatten tree)))

(defn plotpointseq [seq minx maxx miny maxy]
  ;(println minx maxx miny maxy)
  (dotimes [n (- (count seq) 1)]
    (dotimes [m (- (count (nth seq n)) 0)]
            ;(q/line (q/map-range () 0 1 0 (q/width)) (q/map-range (nth seq (+ n 1) 0 1 0 (q/height))))
            ;   (println (nth seq n))
            ; (println (first (nth seq n)))
      (let [x1 (q/map-range (first (nth (nth seq n) m)) minx maxx 0 (q/width))
            y1 (q/map-range (last (nth (nth seq n) m)) miny maxy 0 (q/height))
            x2 (q/map-range (first (nth  (nth seq (+ n 1)) m)) minx maxx 0 (q/width))
            y2 (q/map-range (last (nth  (nth seq (+ n 1)) m)) miny maxy 0 (q/height) )]
        ;(println x1 y1 x2 y2)
        (q/line x1 y1 x2 y2)))
    ))



(defn drawline [indata moddata]
  (let [pnts1 (atom [])
        pnts2 (atom [])]
    (dotimes [yi (count moddata)]
      (let [yoffset (* (@data :spacingA) (Math/pow (@data :spacingB) yi) )
            pnts (atom [])
            ]
        (dotimes [xi (count indata)]
          (let [x (* xi (/ (@data :spacingC) (+ yi 1)))
                y (+ yoffset (* (nth indata xi) (nth moddata yi)))
            ;y2 (if (> y maxy) ((- maxy (- y maxy))) (if (< y miny) (+ miny (- miny y))  y))
                ]
            (swap! pnts conj [x y]))
          )
        (swap! pnts1 conj @pnts)
            ;(println @pnts)

        )
      )
    (dotimes [xi (count indata)]
      (let [pnts (atom [])]
        (dotimes [yi (count moddata)]
          (let [yoffset (* (@data :spacingA) (Math/pow (@data :spacingB) yi))
                x (* xi (/ (@data :spacingC) (+ yi 1)))
                y (+ yoffset (* (nth indata xi) (nth moddata yi)))]
            (swap! pnts conj [x y]))
          )
        (swap! pnts2 conj @pnts)
        )


      )

    (let [;pnts (bounds (into (map last @pnts1) (map last @pnts2)))

          pntsx (bounds  (into (map first (apply concat  @pnts1)) (map first (apply concat  @pnts2))))
          pntsy (bounds  (into (map last (apply concat  @pnts1)) (map last (apply concat  @pnts2))))

          minx 0
          maxx (* (@data :xscale) (last pntsx))
          ;maxx (int (* (count indata) (/ spacingC (+ 1 (count moddata)))))
            ; maxx (/ spacingC (+ 1 (count moddata)))
          miny (first pntsy)
          maxy (last pntsy)]
;      (println (into (apply concat  @pnts1) (apply concat  @pnts2)))
     ; (println @pnts1)
    ;  (println minx maxx miny maxy)
      (plotpointseq @pnts1 minx maxx miny maxy)
      (plotpointseq @pnts2 minx maxx miny maxy)
      ))
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
    (q/text "Mountain Drone" 0 -10)
                                        ;    (println "blah")


                                        ;      (with-open [fd (clojure.java.io/writer "foo.txt")] (binding [*out* fd] (println (str "data " (get @data :size))) (println (str (mm/pirad)))))
    )

  )


(defn draw []
  ( let [c1 (q/map-range  (get @c/cc :c1) 0 127 0 16 )
         c2 (q/map-range  (get @c/cc :c2) 0 127 0 16 )
         c3 (q/map-range  (get @c/cc :c3) 0 127 0.99 0.1)
         c4 (q/map-range  (get @c/cc :c4) 0 127 0.5 16 )
         c5 (q/map-range  (get @c/cc :c5) 0 127 0 16 )
         c6 (q/map-range  (get @c/cc :c6) 0 127 0 16 )
         c7 (q/map-range  (get @c/cc :c7) 0 127 0 16 )
         c8 (q/map-range  (get @c/cc :c8) 0 127 0 16 )
         c9 (q/map-range  (get @c/cc :c9) 0 127 0 16 )
         c10 (q/map-range  (get @c/cc :c10) 0 127 0 16 )
         c11 (q/map-range  (get @c/cc :c11) 0 127 0 16 )
         c12 (q/map-range  (get @c/cc :c12) 0 127 0 16 )
         c13 (q/map-range  (get @c/cc :c13) 0 127 0 16 )
         c14 (q/map-range  (get @c/cc :c14) 0 127 0 16 )
         c15 (q/map-range  (get @c/cc :c15) 0 127 0 16 )
         c16 (q/map-range  (get @c/cc :c16) 0 127 0 16 )

         env-l (q/map-range (@c/audio-l :env) 0 127 0 1)
         env-r (q/map-range (@c/audio-r :env) 0 127 0 1)


         s1 (q/map-range (@c/nano :s1) 0 127 10 0.1)
         k1 (@c/opz1 :c1)
         b1a (@c/nano :b1a)
         b1b (@c/nano :b1b)

         s2 (q/map-range (@c/nano :s2) 0 127 1 15)
         k2 (q/map-range (@c/nano :k2) 0 127 0.1 0.99)  ;detail
         b2a (@c/nano :b2a)
         b2b (@c/nano :b2b)

         s3 (q/map-range (@c/nano :s3) 0 127 1 50)
         k3 (q/map-range (@c/nano :k3) 0 127 0.99 0.01) ;spacingB
         b3a (@c/nano :b3a)
         b3b (@c/nano :b3b)

         s4 (q/map-range (@c/nano :s4) 0 127 1 50)
         k4 (q/map-range (@c/nano :k4) 0 127 0.1 50)
         b4a (@c/nano :b4a)
         b4b (@c/nano :b4b)


         xparts 30
         yparts 50
         octaves (q/map-range (@c/nano :k8) 0 127 1 16)


         ]

;;; data mapper
   (if (= 1 b1a)
     (swap! data assoc :octaves (* 16 (* k1 env-l)))
     (swap! data assoc :octaves (* k1 16))
     )
   (if (= 1 b2a)
     (swap! data assoc :detail (* 15 (* k2 env-r)))
     (swap! data assoc :detail (* k2 5))
     )
   (if (= 1 b3a)
     (swap! data assoc :spacingB (* k3 c3))
     (swap! data assoc :spacingB (* k3 1))
     )
   (if (> c1 2)
     (swap! data assoc :seed (rand-int 100)))

   (if (= 1 b4a)
     (swap! data assoc :strokeweight c4)
     (swap! data assoc :strokeweight k4))

 ;  (swap! data assoc :spacingB k3)
   (swap! data assoc :xscale s1)
;   (swap! data assoc :strokeweight s3)

   ;(swap! data assoc :spacingB k3)


   ;; prepare for draw
   (reset! inputdata [])
   (reset! modulationdata [])
   (q/random-seed (@data :seed))
   (modulatedata inputdata k1 (@data :octaves) (@data :detail) yparts 10 (q/random 1 100) 1)
   (modulatedata modulationdata k1 (@data :octaves) (@data :detail) xparts 10 (q/random 1 250) 1)
                                        ;(println inputdata)
                                        ;  (println (:size data))
                                        ; (q/background 20)

   (q/stroke 255 340 0 255)
   (q/fill 255 255 25 20 )

                                        ;(println (get @data :glitch))
   )





  (q/stroke-weight (@data :strokeweight))
            ; (remapdata modulationdata 0 0 100 100)
            ; (remapdata inputdata 0 0 100 100)

  (drawline @modulationdata @inputdata)

  (q/with-translation [500 280 500]
    (datadebug  720 50 10 data)
    ))
