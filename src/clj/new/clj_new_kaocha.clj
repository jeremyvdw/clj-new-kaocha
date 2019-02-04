(ns clj.new.clj-new-kaocha
  (:require [clj.new.templates :refer [renderer group-name name-to-path ->files sanitize-ns]]))

(def render (renderer "clj_new_kaocha"))

(defn clj-new-kaocha
  "FIXME: write documentation"
  [target]
  (let [data {:name (group-name target)
              :sanitized (name-to-path target)
              :sanitized-ns (sanitize-ns target)}]
    (println "Generating fresh 'clj new' kaocha project in" (:name data))
    (->files data
             ["deps.edn" (render "deps.edn" data)]
             ["src/{{sanitized}}.clj" (render "core.clj" data)]
             ["test/{{sanitized}}_test.clj" (render "core_test.clj" data)]
             ["bin/kaocha" (render "kaocha" data)]
             ["tests.edn" (render "tests.edn" data)])))
