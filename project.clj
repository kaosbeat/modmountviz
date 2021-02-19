(defproject modularmountains "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
 :resource-paths [
                   "resources/Syphon/library/jsyphon.jar"
                   "resources/Syphon/library/libJSyphon.jnilib"
                   "resources/Syphon/library/Syphon.framework"
                   "resources/Syphon/library/Syphon.jar"
                   ]

  :dependencies [[org.clojure/clojure "1.8.0"]
                 ;[cider/cider-nrepl "0.13.0"]
                 [overtone "0.10.1"]
                 ;[quil "3.1.0"]
                 [quil "2.2.0"]]
  :jvm-opts ^:replace []
  :main modularmountains.core)
