(ns modularmountains.core

  (:require [quil.core :as q]
            [quil.middleware :as m]
            [modularmountains.osc :as osc]
            [modularmountains.midi]
            [modularmountains.modulators :as mm]
            [modularmountains.elfenwander :as elf]
            [modularmountains.mountaindrone :as drone]
            [modularmountains.distressfrequency :as freq]
            [modularmountains.theedgeofwhatisadministrativelypossible :as edge]

            )

  )



(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.

  (defonce debugfont (q/load-font (.getPath (clojure.java.io/resource "AndaleMono-48.vlw"))))
  (let [font debugfont]
    (q/text-font font)
    (q/text "fontsetup" 20 100))
  )

(defn update-state [state]

  )

(defn draw-state [state]
  (q/background 20)
;  (q/box 100)
  (osc/datadebug 1750 30 15 osc/ch1)
  (osc/datadebug 1750 100 15 osc/ch2)
;  (elf/draw)
 ; (freq/draw)
 ; (drone/draw)
 ; (edge/draw)
;

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
