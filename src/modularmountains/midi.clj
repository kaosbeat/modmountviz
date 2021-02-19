(ns modularmountains.core
  (:use [overtone.live])

  )


(def cc (atom {:c1 0 :c2 0 :c3 0 :c4 0 :c5 0 :c6 0 :c7 0 :c8 0 :c9 0 :c10 0 :c11 0 :c12 0 :c13 0 :c14 0 :c15 0 :c16 0}))

(def audio-l (atom {:attack 0 :env 0 }))
(def audio-r (atom {:attack 0 :env 0 }))

(def nano (atom {:k1 0 :s1 0 :b1a 0 :b1b 0 :k2 0 :s2 0 :b2a 0 :b2b 0 :k3 0 :s3 0 :b3a 0 :b3b 0 :k4 0 :s4 0 :b4a 0 :b4b 0 :k5 0 :s5 0 :b5a 0 :b5b 0 :k6 0 :s6 0 :b6a 0 :b6b 0 :k7 0 :s7 0 :b7a 0 :b7b 0 :k8 0 :s8 0 :b8a 0 :b8b 0 :k9 0 :s9 0 :b9a 0 :b9b 0 }))



(on-event [:midi :timing-clock]
          (fn [e]
         ;   (println e)
            ;(println  (:channel e) )
            ;(print " ")
             ;(print (:note e) )
            ;(print " ")


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


;;midimaps
(def midimap0 (atom []))(def midimap1 (atom []))(def midimap2 (atom []))(def midimap3 (atom []))(def midimap4 (atom []))(def midimap5 (atom []))(def midimap6 (atom []))(def midimap7 (atom []))(def midimap8 (atom []))(def midimap9 (atom []) )(def midimap10 (atom []) )
(def midimap0map (atom []))(def midimap1map (atom []))(def midimap2map (atom []))(def midimap3map (atom []))(def midimap4map (atom []))(def midimap5map (atom []))(def midimap6map (atom []))(def midimap7map (atom []))(def midimap8map (atom []))(def midimap9map (atom []))(def midimap10map (atom []))

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


(on-event [:midi :note-on]
          (fn [e]
              (let [note (:note e)
                    vel  (:velocity e)
                    channel (:channel e)]
                                        ;(println note vel channel)
                                        ;                (println channel note vel)
                (case channel
;;                  10 (midiparse midimap10 @midimap10 midimap10map @midimap10map midikeyz keyz :keyz note vel )
  ;;                15 (if (= note 60) (swap! bbeat inc) (println "ch15 unbeat"))
                  14 (case note
                       0 (do
                           (println vel)
                           ;(piracetambd/add @ch1)
                           ;(swap! midibd assoc :velocity vel :beat (inc (get @midibd :beat)))
                           )
                       1 (do
                           ;(piracetamsd/add @ch2)
                           ;(swap! midisd assoc :velocity vel)
                           )
                      ; 2 (swap! midich assoc :velocity vel)
                      ; 3 (swap! midioh assoc :velocity vel)

                       (+ 1 1)
                       ;(println "no match")
                       )
                 (+ 1 1)
;                 (println "unchannelled midi")
                  )

                )
              )
          ::keyboard-handler)

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
            ;(println e)
            (let [data1 (:data1 e)
                  data2 (:data2 e)
                  channel (:channel e)]
              (case channel
                0 (case data1
                    14 (swap! nano assoc :k1 data2)
                    2 (swap! nano assoc :s1 data2)
                    23 (swap! nano assoc :b1a data2)
                    33 (swap! nano assoc :b1b data2)
                    15 (swap! nano assoc :k2 data2)
                    3 (swap! nano assoc :s2 data2)
                    24 (swap! nano assoc :b2a data2)
                    34 (swap! nano assoc :b2b data2)
                    16 (swap! nano assoc :k3 data2)
                    4 (swap! nano assoc :s3 data2)
                    25 (swap! nano assoc :b3a data2)
                    35 (swap! nano assoc :b3b data2)
                    17 (swap! nano assoc :k4 data2)
                    5 (swap! nano assoc :s4 data2)
                    26 (swap! nano assoc :b4a data2)
                    36 (swap! nano assoc :b4b data2)
                    18 (swap! nano assoc :k5 data2)
                    6 (swap! nano assoc :s5 data2)
                    27 (swap! nano assoc :b5a data2)
                    37 (swap! nano assoc :b5b data2)
                    19 (swap! nano assoc :k6 data2)
                    8 (swap! nano assoc :s6 data2)
                    28 (swap! nano assoc :b6a data2)
                    38 (swap! nano assoc :b6b data2)
                    20 (swap! nano assoc :k7 data2)
                    9 (swap! nano assoc :s7 data2)
                    29 (swap! nano assoc :b7a data2)
                    39 (swap! nano assoc :b6b data2)
                    21 (swap! nano assoc :k8 data2)
                    12 (swap! nano assoc :s8 data2)
                    30 (swap! nano assoc :b8a data2)
                    40 (swap! nano assoc :b8b data2)
                    22 (swap! nano assoc :k9 data2)
                    13 (swap! nano assoc :s9 data2)
                    31 (swap! nano assoc :b9a data2)
                    41 (swap! nano assoc :b9b data2)

                   )

                15 (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;
                    0 (swap! cc assoc :c1 data2)
                    1 (swap! cc assoc :c2 data2)
                    2 (swap! cc assoc :c3 data2)
                    3 (swap! cc assoc :c4 data2)
                    4 (swap! cc assoc :c5 data2)
                    5 (swap! cc assoc :c6 data2)
                    6 (swap! cc assoc :c7 data2)
                    7 (swap! cc assoc :c8 data2)
                    8 (swap! cc assoc :c9 data2)
                    9 (swap! cc assoc :c10 data2)
                    10(swap! cc assoc :c11 data2)
                    11(swap! cc assoc :c12 data2)
                    12(swap! cc assoc :c13 data2)
                    13(swap! cc assoc :c14 data2)
                    14(swap! cc assoc :c15 data2)
                    15(swap! cc assoc :c16 data2)
                    16 (swap! audio-l assoc :env data2)
                    17 (swap! audio-l assoc :attack data2)
                    18 (swap! audio-r assoc :env data2)
                    19 (swap! audio-r assoc :attack data2)
                    )
                (do (println "unchanneled CC midi Channel")  (+ 1 1)
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
