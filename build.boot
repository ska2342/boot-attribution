;; -*- mode: clojure -*-
(def PROJECT
  {:project 'de.clojure-buch.boot-attribution
   :version "2018-05"
   ;; :build (or (env/env :build-number) "unknown")
   :license {:name "Eclipse Public License"
             :url "http://www.eclipse.org/legal/epl-v10.html"}})


(def runtime-deps
  '[[org.clojure/clojure "1.9.0"]])

(def test-deps
  '[[adzerk/boot-test "1.2.0" :scope "test"]])

;; Now, boot
(set-env!
 :source-paths #{"src/"}
 ;; resource-paths
 :dependencies (into runtime-deps test-deps)
 )

(task-options!
 pom (select-keys PROJECT [:project :version :description :license])
 )



