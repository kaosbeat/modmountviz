(ns modularmountains.core
  (:use [overtone.live])


  ;(:require [modularmountains.elfenwander :as elf])
  )


(def cc (atom {:c1 0 :c2 0 :c3 0 :c4 0 :c5 0 :c6 0 :c7 0 :c8 0 :c9 0 :c10 0 :c11 0 :c12 0 :c13 0 :c14 0 :c15 0 :c16 0}))

(def audio-l (atom {:attack 0 :env 0 }))
(def audio-r (atom {:attack 0 :env 0 }))

(def nano (atom {:k1 0 :s1 0 :b1a 0 :b1b 0 :k2 0 :s2 0 :b2a 0 :b2b 0 :k3 0 :s3 0 :b3a 0 :b3b 0 :k4 0 :s4 0 :b4a 0 :b4b 0 :k5 0 :s5 0 :b5a 0 :b5b 0 :k6 0 :s6 0 :b6a 0 :b6b 0 :k7 0 :s7 0 :b7a 0 :b7b 0 :k8 0 :s8 0 :b8a 0 :b8b 0 :k9 0 :s9 0 :b9a 0 :b9b 0 }))


(def opz1 (atom {:c1 0 :c2 0 :c3 0 :c4 0}))
(def opz2 (atom {:c1 0 :c2 0 :c3 0 :c4 0}))


(def opz9 (atom {:c1 0 :c2 0 :c3 0 :c4 0 :c5 0 :c6 0 :c7 0 :c8 0 :c9 0 :c10 0 :c11 0 :c12 0 :c13 0 :c14 0 :c15 0 :c16 0 :c17 0 :c18 0 :c19 0 :c20 0}))



(on-event [:midi :timing-clock]
          (fn [e]
         ;   (println e)
            ;(println  (:channel e) )
            ;(print " ")
             ;(print (:note e) )
            ;(print " ")
            (+ 1 1)

            )
          ::clock-handler
          )


(on-event [:midi :midi-time-code]
          (fn [e]
            ;(println e)
            ;(println  (:channel e) )
            ;(print " ")
            ;(print (:note e) )
            ;(print " ")


            )
          ::clock-handler2
          )

(remove-event-handler ::clock-handler)
(remove-event-handler ::clock-handler2)


;;midimaps
(def midimap0 (atom []))(def midimap1 (atom []))(def midimap2 (atom []))(def midimap3 (atom []))(def midimap4 (atom []))(def midimap5 (atom []))(def midimap6 (atom []))(def midimap7 (atom []))(def midimap8 (atom []))(def midimap9 (atom []) )(def midimap10 (atom []) )
(def midimap0map (atom []))(def midimap1map (atom []))(def midimap2map (atom []))(def midimap3map (atom []))(def midimap4map (atom []))(def midimap5map (atom []))(def midimap6map (atom []))(def midimap7map (atom []))(def midimap8map (atom []))(def midimap9map (atom []))(def midimap10map (atom []))

(def channelseeds (atom {:elf 0, :drone 0, :freq 0, :edge 0 }))


(defn midiparse [midimap midimapatom midimapmap midimapmapatom channelmap channelname channelnamekey note vel ]
  (do
    (if (= false (.contains @midimap note))
      (do ;(println midimap8)
        (swap! midimap conj note)
        (reset! midimap (vec (sort @midimap)))
        (swap! midimapmap conj {:x (rand-int (q/width)) :y (rand-int (q/height)) :z (rand-int 500) })
        (reset! channelmap [{:channel channelnamekey} @midimap @midimapmap])
        ) )

    (swap! channelname assoc :note note :velocity vel)
    ))


(on-event [:midi :note-on]  ;;CHANNEL minus one!!!
          (fn [e]
              (let [note (:note e)
                    vel  (:velocity e)
                    channel (:channel e)]
;l                  (println note vel channel)
                                        ;                (println channel note vel)
                (case channel
                  9 (do
                      (println note)
                      (swap! channelseeds assoc :edge note ))
                  10 (do
                      (swap! channelseeds assoc :freq note )
                      )
                  1 (do
                      (swap! channelseeds assoc :elf note )
                      )
                 (+ 1 1)
;                 (println "unchannelled midi")
                  )

                )
              )
          ::keyboard-handler)


(on-event [:midi :pitch-bend]
          (fn [e]
            ;; (println e)
            (let [bend (:data2 e)]
              (println bend))

            )
          ::bend-handler)

(on-event [:midi :note-off]
          (fn [e]
            (let [note (:note e)
                  channel (:channel e)
                  vel 0]

             ; (println  note channel)
              (case channel

                14 (case note
                      ; 0 (swap! midibd assoc :velocity vel)
                      ; 1 (swap! midisd assoc :velocity vel)
                      ; 2 (swap! midich assoc :velocity vel)
                      ; 3 (swap! midioh assoc :velocity vel)

                      (+ 1 1)
                   ;    (println "no match")
                       )


                  (+ 1 1)
                 ; (println "unchannelled midi")
                  )
              )

            )
          ::keyboard-off-handler
          )

(on-event [:midi :control-change]
          (fn [e]
           ; (println e)
            (let [data1 (:data1 e)
                  data2 (:data2 e)
                  channel (:channel e)]
              (case channel


                0 (case data1

                    1 (swap! opz1 assoc :c1 data2)
                    2 (swap! opz1 assoc :c2 data2)
                    3 (swap! opz1 assoc :c3 data2)
                    4 (swap! opz1 assoc :c4 data2)

                    16 (swap! audio-l assoc :env data2)
                    17 (swap! audio-l assoc :attack data2)
                    18 (swap! audio-r assoc :env data2)
                    19 (swap! audio-r assoc :attack data2)
                    (+1 1)
                    )
                1 (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;
                    1 (swap! opz2 assoc :c2 data2)
                    2 (swap! opz2 assoc :c3 data2)
                    3 (swap! opz2 assoc :c4 data2)
                    4 (swap! opz2 assoc :c5 data2)

                    16 (swap! audio-l assoc :env data2)
                    17 (swap! audio-l assoc :attack data2)
                    18 (swap! audio-r assoc :env data2)
                    19 (swap! audio-r assoc :attack data2)
                    (+ 1 1)
                    )
                8 (do
                  ; (println data2)
                   (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;
                        1 (swap! opz9 assoc :c1 data2)
                        2 (swap! opz9 assoc :c2 data2)
                        3 (swap! opz9 assoc :c3 data2)
                        4 (swap! opz9 assoc :c4 data2)
                        5 (swap! opz9 assoc :c5 data2)
                        6 (swap! opz9 assoc :c6 data2)
                        7 (swap! opz9 assoc :c7 data2)
                        8 (swap! opz9 assoc :c8 data2)
                        9 (swap! opz9 assoc :c9 data2)
                        10 (swap! opz9 assoc :c10 data2)
                        11 (swap! opz9 assoc :c11 data2)
                        12 (swap! opz9 assoc :c12 data2)
                        13 (swap! opz9 assoc :c13 data2)
                        14 (swap! opz9 assoc :c14 data2)
                        15 (swap! opz9 assoc :c15 data2)
                        16 (swap! opz9 assoc :c16 data2)
                        17 (swap! opz9 assoc :c17 data2)
                        18 (swap! opz9 assoc :c18 data2)
                        19 (swap! opz9 assoc :c19 data2)
                        20 (swap! opz9 assoc :c20 data2)


                        (+ 1 1)
                        ))
                (do ;(println "unchanneled CC midi Channel")
                  (+ 1 1)
                    ))



              )
            )
          ::control-handler
          )



(defn mididebugger [state]

  (let [w (/ (q/width) 16)
        h 100
        s 40
        ts 20]
    (q/text-size ts)

    (if-not (= (get (:bd state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:bd state) :gain)) 0 0 255)
        (q/rect w h s s)
        (q/fill 255)
        (q/text "bd" (+ w 8) (+ h 26) ))

      )
    (if-not (= (get (:sd state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:sd state) :velocity)) 0 0 120)
        (q/rect (* 2 w) h s s)
        (q/fill 255)
        (q/text "sd" (+ (* 2 w) 8) (+ h 26) ))
      )

    (if-not (= (get (:ch state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:ch state) :velocity)) 0 0 120)
        (q/rect (* 3 w) h s s)
        (q/fill 255)
        (q/text "ch" (+ (* 3 w) 8) (+ h 26) ))

      )
    (if-not (= (get (:oh state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:oh state) :velocity)) 0 0 120)
        (q/rect (* 4 w) h s s)
        (q/fill 255)
        (q/text "oh" (+ (* 4 w) 8) (+ h 26) ))

      )
    (if-not (= (get (:pc1 state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:pc1 state) :velocity)) 0 0 120)
        (q/rect (* 5 w) h s s)
        (q/fill 255)
        (q/text "p1" (+ (* 5 w) 8) (+ h 26) ))

      )
    (if-not (= (get (:pc2 state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:pc2 state) :velocity))  0 0 120)
        (q/rect (* 6 w) h s s)
        (q/fill 255)
        (q/text "p2" (+ (* 6 w) 8) (+ h 26) ))

      )
    (if-not (= (get (:ld1 state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:ld1 state) :velocity))  0 0 120)
        (q/rect (* 7 w) h s s)
        (q/fill 255)
        (q/text "l1" (+ (* 7 w) 8) (+ h 26) ))

      )

    (if-not (= (get (:ld2 state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:ld2 state) :velocity))  0 0 120)
        (q/rect (* 8 w) h s s)
        (q/fill 255)
        (q/text "l2" (+ (* 8 w) 8) (+ h 26) ))

      )

    (if-not (= (get (:chords state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:chords state) :velocity))  0 0 120)
        (q/rect (* 9 w) h s s)
        (q/fill 255)
        (q/text "c1" (+ (* 9 w) 8) (+ h 26) ))

      )
    (if-not (= (get (:bass state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:bass state) :velocity))  0 0 120)
        (q/rect (* 10 w) h s s)
        (q/fill 255)
        (q/text "b1" (+ (* 10 w) 8) (+ h 26) ))

      )
    (if-not (= (get (:keyz state) :velocity) 0)
      (do
        (q/fill (* 2 (get (:keyz state) :velocity)) 0 0 120)
        (q/rect (* 11 w) h s s)
        (q/fill 255)
        (q/text "k1" (+ (* 11 w) 8) (+ h 26) ))

      )

    )
  )
