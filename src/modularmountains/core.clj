(ns modularmountains.core

  (:require [quil.core :as q]
            [quil.middleware :as m]
            ; [modularmountains.osc :as osc]
            [modularmountains.data]

            [modularmountains.modulators :as mm]
            [modularmountains.elfenwander :as elf]

             [modularmountains.mountaindrone :as drone]
            [modularmountains.distressfrequency :as freq]
            [modularmountains.theedgeofwhatisadministrativelypossible :as edge]
            [modularmountains.midi :as midi]
            )
  (:import ('codeanticode.syphon.SyphonServer))
  (:import ('jsyphon.JSyphonServer))


  )

(def server (atom nil))


(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  (reset! server (codeanticode.syphon.SyphonServer. (quil.applet/current-applet) "ModularMountains"))
  ; Set color mode to HSB (HSV) instead of default RGB.
  (defonce debugfont (q/load-font (.getPath (clojure.java.io/resource "AndaleMono-48.vlw"))))
 (let [font debugfont] (q/text-font font) (q/text "fontsetup" 20 100))
  )

(defn update-state [state]

  )

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






(defn draw-state [state]
  (q/background 20)
  (q/box 100)
 ; (datadebug 1750 30 15 osc/ch1)
 ; (datadebug 1750 100 15 osc/ch2)
;  (datadebug 1750 180 15 opz1)
; (datadebug 1750 500 15 audio-l)
; (datadebug 1750 550 15 audio-r)
(elf/draw)
 ; (freq/draw)
 ; (drone/draw)
  ;;
;(edge/draw)
                                        ;
  (.sendScreen @server )

  )





(q/defsketch modularmountains
  :title "Modular Mountains"
  :size :fullscreen
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:present]
  :renderer :opengl
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
