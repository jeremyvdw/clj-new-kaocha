(ns clj.new.clj-new-kaocha
  (:require [clj.new.templates :refer [renderer project-name name-to-path ->files sanitize-ns]]))

(def render (renderer "clj_new_kaocha"))

(defn clj-new-kaocha
  "FIXME: write documentation"
  [name]
  (let [data {:name (project-name name)
              :sanitized (name-to-path name)
              :sanitized-ns (sanitize-ns name)}]
    (println "Generating fresh 'clj new' kaocha project.")
    (->files data
             ["deps.edn" (render "deps.edn" data)]
             ["src/{{sanitized}}.clj" (render "core.clj" data)]
             ["test/{{sanitized}}_test.clj" (render "core_test.clj" data)]
             ["bin/kaocha" (render "kaocha" data)]
             ["tests.edn" (render "tests.edn" data)])))
