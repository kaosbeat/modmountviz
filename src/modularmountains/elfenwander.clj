(ns modularmountains.elfenwander
  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]
   [modularmountains.core :as c]))


(def data (atom {:size 200 :rot 0.5 :glitch 0 :alpha 10 :xsize 15 :ysize 15 :zsize 15}) )
;(def font modularmountains.core/debugfont)



(defn draw []

                                        ;update stuff
  (let [c1 (get @c/cc :c1)
        c2 (get @c/cc :c2)
        c7 (get @c/cc :c7)
        b1a (@c/nano :b1a)
        s1 (q/map-range (@c/nano :s1) 0 127 0 255)
        s2 (q/map-range (@c/nano :s2) 0 127 1 15)
        ]
    (if (= 1 b1a)
        (if-not (= 0 c1)
          (swap! data assoc :rot (/ (rand-int 628) 100))))
    (if-not (= 0.0 c2)
      (swap! data assoc :glitch (* 1 c2)))
    (swap! data assoc :alpha s1)
    (swap! data assoc :xsize s2)
    (swap! data assoc :ysize s2)
    (swap! data assoc :zsize s2)

    )
                                        ;  (println (:size data))
 ; (q/background 20)
  (q/stroke 255)
  (q/fill 55 180 128 (@data :alpha))
;(println (get @data :glitch))
  (let [strokeweight (* (/ (get @c/cc :c1) 127) (get @c/nano :k1))
        seed 20
        rot (get @data :rot)
        rotmod 0;(mm/hundredsteps)
        xsize (get @data :xsize)
        ysize (get @data :ysize)
        zsize (get @data :zsize)
        size (get @data :size)
        sizeinterval (* 0.9 size)
        mmsize 5
        sqtresh 1
        xoffset (/ (- (q/width)(* xsize sizeinterval))2 )
        yoffset (/ (- (q/height)(* ysize sizeinterval))2 )
        zoffset (- (/ (- 0 (* zsize sizeinterval))2 ) 1500)
        ]

    (q/random-seed seed)
    (q/stroke-weight strokeweight)

    ( q/with-translation [xoffset yoffset  zoffset  ]
     (q/with-rotation [rot 1 1 1 ]
       (dotimes [x (+ 0 xsize)]
         (dotimes [y (+ 0 ysize)]
           (dotimes [z (+ 0 zsize)]
             ( q/with-translation [(* x sizeinterval) (+ 0  (* y sizeinterval )) (+ 0 (* z sizeinterval))]
              (if (< (/ (q/random 100 ) 100) sqtresh)
                (q/with-rotation [rot 0 0 0]
                  (q/with-rotation [rotmod 0 0 0])

                  (q/box (q/random 50 150)
                         (q/random 50 150)
                         (q/random 50 150))

                    )))
             ))))))

  (q/with-translation [500 280 -5000]
   ; (datadebug  20 50 10 data)
    )
  )
