;; -*- mode: clojure -*-
(def +version+ "2018-05")
(def PROJECT
  {:project 'de.clojure-buch/boot-attribution
   :version +version+
   ;; :build (or (env/env :build-number) "unknown")
   :license {"Eclipse Public License"
             "http://www.eclipse.org/legal/epl-v10.html"}})


(def runtime-deps
  '[[org.clojure/clojure "1.9.0"]])

(def test-deps
  '[[adzerk/boot-test "1.2.0" :scope "test"]])

;; Now, boot
(set-env!
 :source-paths #{"src/"}
 :resource-paths #{"resources/"}
 :dependencies (into runtime-deps test-deps)
 )

(task-options!
 pom (select-keys PROJECT [:project :version :description :license])
 )



