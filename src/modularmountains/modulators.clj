(ns modularmountains.modulators
  (:require
   [quil.helpers.seqs :refer [seq->stream range-incl cycle-between steps]]
   )
  )




(def piradfast (seq->stream (cycle-between 0 6.2830 0.0001 6.2830)))
(def pirad (seq->stream (cycle-between 0 6.2830 0.0002 6.2830)))

(def slowone (seq->stream (cycle-between 0 1 0.00002 0.0002)))


(def seedchange (seq->stream (cycle-between 0 100 1 100)))
(def hundredsteps (seq->stream (cycle-between 0 10 0.01 10)))
(def smallsteps (seq->stream (cycle-between 0 10 0.01 0.01 )))
