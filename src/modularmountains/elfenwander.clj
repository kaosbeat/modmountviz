(ns modularmountains.elfenwander
  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]
   [modularmountains.core :as c]))


(def data (atom {:size 200
                 :rot 0.5
                 :rotseed 1
                 :glitch 0
                 :alpha 10
                 :strokeweight 10
                 :xsize 15 :ysize 15 :zsize 15
                 :cubesize 50
                 :interval 100}) )
;(def font modularmountains.core/debugfont)

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
    (q/text "Elfen Wander" 10 -10)
                                        ;    (println "blah")


                                        ;      (with-open [fd (clojure.java.io/writer "foo.txt")] (binding [*out* fd] (println (str "data " (get @data :size))) (println (str (mm/pirad)))))
    )

  )

(defn draw []

                                        ;update stuff
  (let[c1    (q/map-range  (get @c/cc :c1) 0 127 0 16 )
       c2    (q/map-range  (get @c/cc :c2) 0 127 0 16 )
       c3    (q/map-range  (get @c/cc :c3) 0 127 0.99 0.1)
       c4    (q/map-range  (get @c/cc :c4) 0 127 0 127 )
       c5    (q/map-range  (get @c/cc :c5) 0 127 0 16 )
       c6    (q/map-range  (get @c/cc :c6) 0 127 0 16 )
       c7    (q/map-range  (get @c/cc :c7) 0 127 0 16 )
       c8    (q/map-range  (get @c/cc :c8) 0 127 0 16 )
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


       s1    (q/map-range (@c/nano :s1) 0 127 10 120)
       k1    (q/map-range (@c/nano :k1) 0 127 0 16)
       b1a   (@c/nano :b1a)
       b1b   (@c/nano :b1b)

       s2    (q/map-range (@c/nano :s2) 0 127 1 15)
       k2    (q/map-range (@c/nano :k2) 0 127 0 250)  ;detail
       b2a   (@c/nano :b2a)
       b2b   (@c/nano :b2b)

       s3    (q/map-range (@c/nano :s3) 0 127 1 250)
       k3    (q/map-range (@c/nano :k3) 0 127 0 2500) ;spacingB
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
    (if (= 1 b2a)
      (if (< 12 c2)
        (swap! data assoc :glitch (rand-int 100))))

    (if (= b3a 1)
      (swap! data assoc :cubesize (* s3  (* env-l 10)))
      (swap! data assoc :cubesize (* 10 s3)))

    (if (= b4a 1)
      (if  (< 5 c4)
        (swap! data assoc :rot (do (mm/multirad c4) (mm/pirad)))))

    (if (= b4b 127)
      (swap! data assoc :rotseed c5)
      )

    (swap! data assoc :alpha s1)
    (swap! data assoc :xsize s2)

    (swap! data assoc :interval k3)

    )
                                        ;  (println (:size data))
 ; (q/background 20)
  (q/stroke 255)
  (q/fill 55 180 128 (@data :alpha))
;(println (get @data :glitch))
  (let [strokeweight (@data :strokeweight)
        seed (@data :glitch)
        rot (@data :rot)
        rotx ()
        rotmod 0;(mm/hundredsteps)
        xsize (@data :xsize)
        ysize (@data :xsize)
        zsize (@data :xsize)
        size (@data :size)
        sizeinterval (@data :interval)
        mmsize 5
        sqtresh 1
        xoffset (/ (- (q/width)(* xsize sizeinterval))2 )
        yoffset (/ (- (q/height)(* ysize sizeinterval))2 )
        zoffset (- (/ (- 0 (* zsize sizeinterval))2 ) 500)
        ]



    (q/stroke-weight strokeweight)

    ( q/with-translation [xoffset yoffset  zoffset  ]

     (let [rotseed (q/random-seed (@data :rotseed))
           rotx (q/random 19)
           roty (q/random 19)
           rotz   (q/random 19) ]
       ;(println rotx roty rotz)
       (q/with-rotation [rot rotx roty rotz ]
         (q/random-seed seed)
         (dotimes [x (+ 0 xsize)]
           (dotimes [y (+ 0 ysize)]
             (dotimes [z (+ 0 zsize)]
               ( q/with-translation [(* x sizeinterval) (+ 0  (* y sizeinterval )) (+ 0 (* z sizeinterval))]
                (if (< (/ (q/random 100 ) 100) sqtresh)
                  (q/with-rotation [rot 0 0 0]
                    (q/with-rotation [rotmod 0 0 0])

                    (q/box (q/random 0 (@data :cubesize))
                           (q/random 0 (@data :cubesize))
                           (q/random 0 (@data :cubesize)))

                    )))
               )))))))



  (datadebug  1400 50 20 data)

  )
