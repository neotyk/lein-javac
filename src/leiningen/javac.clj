(ns leiningen.javac
  (:use [leiningen.classpath :only [make-path find-lib-jars]]
        [clojure.contrib.java-utils :only [file]])
  (:import org.apache.tools.ant.taskdefs.Javac)
  (:import org.apache.tools.ant.types.Path)
  (:require lancet))

(defn javac
"creates and executes javac ant task.  Assumes $JAVA_HOME is set"
  [project]
  (let [java (Javac.)]
    (doto java
      (.setProject lancet/ant-project)
      (.setSrcdir (make-path (:source-path project)))
      (.setDestdir (file (:compile-path project)))
      (.setClasspath (make-path (apply make-path
                                       (find-lib-jars project))
                                (:source-path project)
                                (:test-path project)
                                (:compile-path project)
                                (:resources-path project)))
      (.setFork true)
      (.execute))))