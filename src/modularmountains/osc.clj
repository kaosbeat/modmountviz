(ns modularmountains.osc
  (:use [overtone.live])
  (:require [quil.core :as q])
  )




; start a server and create a client to talk with it
(def PORT 4243)
;(def spacePORT 9243)
                                        ;(def spaceOSCs (osc-server spacePORT))
(if (= nil (resolve 'OSCs))
  (do (def OSCs (osc-server PORT))
      (def OSCc (osc-client "localhost" 4242))))


(def ch1 (atom {:freq 0 :amp 0 :count 0 }))
(def ch2 (atom {:freq 0 :amp 0 :count 0 }))
(def ch1attack (atom 0))
(def ch2attack (atom 0))



(osc-handle OSCs "/ch1attack" (fn [msg] (reset! ch1attack (rand-int 1000))))
(osc-handle OSCs "/ch2attack" (fn [msg] (reset! ch2attack (rand-int 1000))))
(osc-handle OSCs "/ch1" (fn [msg] (swap! ch1 assoc :freq (nth (get msg :args) 0) :amp ( nth (get msg :args) 1) :count (nth (get msg :args) 2))))
(osc-handle OSCs "/ch2" (fn [msg] (swap! ch2 assoc :freq (nth (get msg :args) 0) :amp (nth (get msg :args) 1) :count (nth (get msg :args) 2))))


(defn datadebug [x y ts data]
  (q/text-size ts)
  (q/with-translation [x y 0]

    (dotimes [n (count @data)]
      (q/fill 255 255 0)
      (q/stroke 255 0 0)
      ;(q/text "bklad" 200 300)
      (q/text (name (nth (map first @data) n))  0 (* 1.2 (* n ts)) )
      (q/text (str (float (nth (map second @data) n))) (* ts 4)  (* 1.2 (* n ts)))
      )
    )
  )
