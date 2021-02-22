(ns modularmountains.midi
  (:use [overtone.live])
  (:require [modularmountains.core :as c]
            [modularmountains.elfenwander :as elf]
            [modularmountains.mountaindrone :as drone]
            [modularmountains.distressfrequency :as freq]
            [modularmountains.theedgeofwhatisadministrativelypossible :as edge]
            )
  )






(on-event [:midi :timing-clock]
          (fn [e]
            (+ 1 1)

            )
          ::clock-handler
          )


(on-event [:midi :midi-time-code]
          (fn [e]
            )
          ::clock-handler2
          )

(remove-event-handler ::clock-handler)
(remove-event-handler ::clock-handler2)


(on-event [:midi :note-on]  ;;CHANNEL minus one!!!
          (fn [e]
              (let [note (:note e)
                    vel  (:velocity e)
                    channel (:channel e)]

                (case channel
                  9 (do
                      (println note)
                      (swap! c/channelseeds assoc :edge note ))
                  10 (do
                      (swap! c/channelseeds assoc :freq note )
                      )
                  0 (do
                      (swap! c/channelseeds assoc :elf note )
                      (swap! elf/data assoc :glitch (rand-int 20))

                      )
                  1 (do

                      (swap! elf/data assoc :cubesize (rand-int 500))

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
                0 (do
                                        ; (println data2)
                    (let [opz c/opz1]
                     (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;

                       1 (swap! opz assoc :c1 data2)
                       2 (swap! opz assoc :c2 data2)
                       3 (swap! opz assoc :c3 data2)
                       4 (swap! opz assoc :c4 data2)
                       5 (swap! opz assoc :c5 data2)
                       6 (swap! opz assoc :c6 data2)
                       7 (swap! opz assoc :c7 data2)
                       8 (swap! opz assoc :c8 data2)
                       9 (swap! opz assoc :c9 data2)
                       10 (swap! opz assoc :c10 data2)
                       11 (swap! opz assoc :c11 data2)
                       12 (swap! opz assoc :c12 data2)
                       13 (swap! opz assoc :c13 data2)
                       14 (swap! opz assoc :c14 data2)
                       15 (swap! opz assoc :c15 data2)
                       16 (swap! opz assoc :c16 data2)
                       (+ 1 1)
                       )))
                1 (do
                                        ; (println data2)
                    (let [opz c/opz2]
                     (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;

                       1 (swap! opz assoc :c1 data2)
                       2 (swap! opz assoc :c2 data2)
                       3 (swap! opz assoc :c3 data2)
                       4 (swap! opz assoc :c4 data2)
                       5 (swap! opz assoc :c5 data2)
                       6 (swap! opz assoc :c6 data2)
                       7 (swap! opz assoc :c7 data2)
                       8 (swap! opz assoc :c8 data2)
                       9 (swap! opz assoc :c9 data2)
                       10 (swap! opz assoc :c10 data2)
                       11 (swap! opz assoc :c11 data2)
                       12 (swap! opz assoc :c12 data2)
                       13 (swap! opz assoc :c13 data2)
                       14 (swap! opz assoc :c14 data2)
                       15 (swap! opz assoc :c15 data2)
                       16 (swap! opz assoc :c16 data2)
                       (+ 1 1)
                       )))
                2 (do
                                        ; (println data2)
                    (let [opz c/opz3]
                     (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;

                       1 (swap! opz assoc :c1 data2)
                       2 (swap! opz assoc :c2 data2)
                       3 (swap! opz assoc :c3 data2)
                       4 (swap! opz assoc :c4 data2)
                       5 (swap! opz assoc :c5 data2)
                       6 (swap! opz assoc :c6 data2)
                       7 (swap! opz assoc :c7 data2)
                       8 (swap! opz assoc :c8 data2)
                       9 (swap! opz assoc :c9 data2)
                       10 (swap! opz assoc :c10 data2)
                       11 (swap! opz assoc :c11 data2)
                       12 (swap! opz assoc :c12 data2)
                       13 (swap! opz assoc :c13 data2)
                       14 (swap! opz assoc :c14 data2)
                       15 (swap! opz assoc :c15 data2)
                       16 (swap! opz assoc :c16 data2)
                       (+ 1 1)
                       )))
                3 (do
                                        ; (println data2)
                    (let [opz c/opz4]
                     (case data1 ;; this is A192 CVM16 > axoloti-1 channel ;;

                       1 (swap! opz assoc :c1 data2)
                       2 (swap! opz assoc :c2 data2)
                       3 (swap! opz assoc :c3 data2)
                       4 (swap! opz assoc :c4 data2)
                       5 (swap! opz assoc :c5 data2)
                       6 (swap! opz assoc :c6 data2)
                       7 (swap! opz assoc :c7 data2)
                       8 (swap! opz assoc :c8 data2)
                       9 (swap! opz assoc :c9 data2)
                       10 (swap! opz assoc :c10 data2)
                       11 (swap! opz assoc :c11 data2)
                       12 (swap! opz assoc :c12 data2)
                       13 (swap! opz assoc :c13 data2)
                       14 (swap! opz assoc :c14 data2)
                       15 (swap! opz assoc :c15 data2)
                       16 (swap! opz assoc :c16 data2)
                       (+ 1 1)
                       )))



                (do ;(println "unchanneled CC midi Channel")
                  (+ 1 1)
                    ))



              )
            )
          ::control-handler
          )
