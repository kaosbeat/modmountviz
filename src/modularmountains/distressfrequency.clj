(ns modularmountains.distressfrequency
  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]))


(def data (atom {:size 100 :rot 0.5 :glitch 0 :xsize 5 :ysize 5 :zsize 5}) )
;(def font modularmountains.core/debugfont)
(def xlist (atom []))
(def ylist (atom []))
(def zlist (atom []))



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
  (reset! xlist [0])
  (reset! ylist [0])
  (reset! zlist [0])
;  (q/color-mode :rgb)
                                        ;  (println (:size data))
  (q/background 20)
  (q/stroke 255)
  (q/fill 255 255 25 20 )

;(println (get @data :glitch))
  (let [strokeweight 10
        xoffset 50
        yoffset 1000
        zoffset -5000
        seed 1
        rot (get @data :rot)
        rotmod (mm/pirad)
        xsize (get @data :xsize)
        ysize (get @data :ysize)
        zsize (get @data :zsize)
        size 1000
        mmsize (mm/hundredsteps)
        sqtresh 0.1
        dx 0
        iy 0
        dy 0
        iz 0
        dz 0
        depth 30
        ]

    (q/random-seed seed)
    (q/stroke-weight strokeweight)
    (q/with-translation [xoffset yoffset  zoffset  ]
     (q/with-rotation [rot 1 0 0 ]
       (dotimes [n depth]
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
           ))
       (dotimes [n (- depth 2)]
        ; (println (nth @xlist (+ n)))
         (q/line (nth @xlist n) (nth @ylist n) (nth @zlist n) (nth @xlist (+ n 1))(nth @ylist (+ 1 n)) (nth @zlist (+ n 1)))
        ; (q/line 0.0 10 100 100)
        ; (q/line 0 0 0  (nth [20 220 131 3344 243 219 99 33 0] n ) 1000 -1000)

         )



       )))

  (q/with-translation [500 280 500]
    (datadebug  20 50 10 data))
  )
