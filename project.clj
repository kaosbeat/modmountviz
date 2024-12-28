(defproject modularmountains "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
 :resource-paths [
;                   "resources/Syphon/library/jsyphon.jar"
 ;                  "resources/Syphon/library/libJSyphon.jnilib"
  ;                 "resources/Syphon/library/Syphon.framework"
   ;                "resources/Syphon/library/Syphon.jar"
                   ]

  :dependencies [[org.clojure/clojure "1.10.1"]
                 ;[nrepl/nrepl "0.8.3"]
                 ;[overtone "0.10.6"]
                 ;[quil "3.1.0"]
                 [quil "3.1.0"]]
  :plugins [;[refactor-nrepl "2.5.0"]
            ;[cider/cider-nrepl "0.25.5"]
            ]
  :jvm-opts ^:replace []
  :main modularmountains.core)
