(ns #^{:author "Hubert Iwaniuk"
       :doc "Installs javac on leiningen.compile/compile hook."}
  leiningen.hooks.javac
  (:use [robert.hooke :only [add-hook]]
        [leiningen.javac :only [javac]]
        [leiningen.compile :only [compile]])
  (:refer-clojure :exclude [compile]))

(add-hook #'leiningen.compile/compile (fn [f project] (do
                                                       (javac project)
                                                       (f project))))
