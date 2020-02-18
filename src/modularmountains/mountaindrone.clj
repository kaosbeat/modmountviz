(ns modularmountains.mountaindrone
  (:require
   [quil.core :as q]
   [modularmountains.modulators :as mm]))


(def data (atom {:size 100 :rot 0.5 :glitch 0 :xsize 5 :ysize 5 :zsize 5}) )
;(def font modularmountains.core/debugfont)
(def xlist (atom []))
(def ylist (atom []))
(def zlist (atom []))


(def inputdata (atom []))
(def modulationdata (atom []))
(def points (atom []))

(defn modulatedata [listatom seed len range base scale ]
  (q/noise-seed seed)
  (q/noise-detail 6 0.6)
  (dotimes [n len]
    (let [p (/ (* scale (q/noise (+ base n))) len)]
      (swap! listatom conj p))))


(defn plotpointseq [seq]
  (dotimes [n  (-  (count seq) 1)]
    (q/line (nth seq n) (nth seq (+ n 1)))
    )
  )

(defn drawline [indata moddata miny minx]
  (let [spacingA 700
        spacingB 0.7
        spacingC 1500]

    (dotimes [yi (count moddata)]
      (let [yoffset (* spacingA (Math/pow spacingB yi))
            pnts (atom [])]
        (dotimes [xi (count indata)]
          (let [x (* xi (/ spacingC (+ yi 1)))
                y (+ yoffset (* (nth indata xi) (nth moddata yi)))]
            (swap! pnts conj [x y]))
          )
        (plotpointseq @pnts)
        )
      )
    (dotimes [xi (count indata)]
      (let [pnts (atom [])]
        (dotimes [yi (count moddata)]
          (let [yoffset (* spacingA (Math/pow spacingB yi))
                x (* xi (/ spacingC (+ yi 1)))
                y (+ yoffset (* (nth indata xi) (nth moddata yi)))]
            (swap! pnts conj [x y]))
          )
        (plotpointseq @pnts)
        )
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
      (q/text (str (float (nth (map second @data) n))) (* ts 4)  (* 1.2 (* n ts)) )

      )
    (q/text "Distress Frequency" 0 -10)
                                        ;    (println "blah")


                                        ;      (with-open [fd (clojure.java.io/writer "foo.txt")] (binding [*out* fd] (println (str "data " (get @data :size))) (println (str (mm/pirad)))))
    )

  )

(defn draw []


  (reset! inputdata [])
  (reset! modulationdata [])
  (modulatedata inputdata 14 15 1 3 9000 )
  (modulatedata modulationdata 140 90 10 10 300)
;(println inputdata)
                                        ;  (println (:size data))
  (q/background 20)
  (q/stroke 255 340 0)
  (q/fill 255 255 25 20 )

;(println (get @data :glitch))


  (q/with-translation [-4500 -2500 -5000]
    (q/stroke-weight 1)
    (drawline @inputdata @modulationdata 0 0))

  (q/with-translation [500 280 500]
   (datadebug  20 50 10 data)
    )
  )
