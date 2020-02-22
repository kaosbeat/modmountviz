(ns modularmountains.osc
  (:use [overtone.live])
  (:require [quil.core :as q])
  )


(def serverstarted (atom false))



; start a server and create a client to talk with it

;(def spacePORT 9243)
                                        ;(def spaceOSCs (osc-server spacePORT))
;(if-not @serverstarted  (do))
(println "STARTINGSERVER")
(def port 4243)
(def OSCs (osc-server port))
(def OSCc (osc-client "localhost" 4242))
(reset! serverstarted true)


(def ch1 (atom {:freq 0 :amp 0 :count 0 }))
(def ch2 (atom {:freq 0 :amp 0 :count 0 }))
(def ch1attack (atom 0))
(def ch2attack (atom 0))



(osc-handle OSCs "/ch1attack" (fn [msg] (reset! ch1attack (rand-int 1000))))
(osc-handle OSCs "/ch2attack" (fn [msg] (reset! ch2attack (rand-int 1000))))
(osc-handle OSCs "/ch1" (fn [msg] (swap! ch1 assoc :freq (nth (get msg :args) 0) :amp ( nth (get msg :args) 1) :count (nth (get msg :args) 2))))
(osc-handle OSCs "/ch2" (fn [msg] (swap! ch2 assoc :freq (nth (get msg :args) 0) :amp (nth (get msg :args) 1) :count (nth (get msg :args) 2))))
