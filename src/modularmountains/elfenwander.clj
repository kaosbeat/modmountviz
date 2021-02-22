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
                 :strokeweight 1
                 :xsize 5 :ysize 5 :zsize 15
                 :cubesize 500
                 :interval 100
                 :seed 23
                 :ttl 100}) )
                                        ;(def font modularmountains.core/debugfont)


(defn pushrandomdata [data seed]
  (q/random-seed seed)
  (let [dim  (/ (@c/opz1 :c1) 8) ]
    (swap! data conj {
                      :size (q/random 1 (* (@c/opz1 :c1) 200))
                      :rot (q/random 0 3.14)
                      :rotseed (q/random  10)
                      :glitch 1
                      :alpha 120
                      :strokeweight (* 0.1 (q/random 10 (* (@c/opz1 :c1) 1)))
                      :xsize 13 :ysize 10 :zsize 3
                      :cubesize (q/random 100 (*  (@c/opz1 :c3) 20))
                      :interval 400
                      } ))
 ;(println @data)

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
    (q/text "Elfen Wander" 10 -10)
                                        ;    (println "blah")


                                        ;      (with-open [fd (clojure.java.io/writer "foo.txt")] (binding [*out* fd] (println (str "data " (get @data :size))) (println (str (mm/pirad)))))
    )

  )



(defn draw []

                                        ;update stuff
 ;(swap! data update-in [:ttl] dec)
 ;(println (@data :ttl))

                                        ;  (println (:size data))
  ;(pushrandomdata data (@c/channelseeds :elf))
;  (swap! data assoc :rot (do (mm/multirad (@c/opz1 :c4)) (mm/pirad)))
  (q/stroke 255)
  (q/fill 25 10 128 (@data :alpha))
;(println (get @data :glitch))
  (let [strokeweight (@data :strokeweight)
        seed (@data :glitch)
        rot (@data :rot)
        rotx 60
        rotmod 0;(mm/hundredsteps)
        xsize (@data :xsize)
        ysize (@data :ysize)
        zsize (@data :zsize)
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
               ( q/with-translation [(* x sizeinterval) (+ 100  (* y sizeinterval )) (+ 0 (* z sizeinterval))]
                (if (< (/ (q/random 100 ) 100) sqtresh)
                  (q/with-rotation [rot 0  1 0]
                    (q/fill 234)

                    (q/box (q/random 0 (@data :cubesize)))
                   ; (q/random 0 (@data :cubesize))
                   ; (q/random 0 (@data :cubesize))

                    )))
               )))))))



  (datadebug  1400 50 20 data)

  )
