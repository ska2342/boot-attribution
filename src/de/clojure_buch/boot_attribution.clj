(ns de.clojure-buch.boot-attribution
  {:boot/export-tasks true}
  (:require [clojure.java.io :as io]
            [clojure.xml     :as xml]
            [boot.core       :as boot :refer [deftask]]
            [boot.pod        :as pod]
            [boot.file       :as file]
            [boot.util       :as util])
  )

(defn- unfold-dep [the-dep]
  (let [{:keys [dep jar]} the-dep
        [lib vers]        dep]
    [lib vers jar]))

;; Taken from lein-licenses plugin
(defn- pom-license-elems [pomxml]
  (for [elem (:content pomxml)
        :when (= (:tag elem) :licenses)
        license (:content elem)]
    license))

;; inspired by code from lein-licenses plugin
(defn- pom->license-names [pomxml]
  (let [license-elems (pom-license-elems pomxml)]
    ;; avoiding empty list from for
    (when (seq license-elems)
      (for [license-elem license-elems
            elem (:content license-elem)
            :when (= (:tag elem) :name)]
        (first (:content elem))))))

(defn- parse-xml-string [s]
  (xml/parse
   (java.io.ByteArrayInputStream.
    (.getBytes s))))

(defmulti resolve-licenses
  (fn [& args]
    (first args)))

(defmethod resolve-licenses ::unknown
  [& _]
  "Undetectable License! Use manual resolution")

;; FIXME does not yet search for parent POMs
(defmethod resolve-licenses ::pom
  [_ jar]
  (try (-> (pod/pom-xml jar)
           parse-xml-string
           pom->license-names)
       (catch Throwable _ nil)))


(defn- library-attributions [the-dep strategies]
  (let [[lib vers jar] (unfold-dep the-dep)]
    (util/info "Resolving POM for %s %s %s\n" lib vers jar)
    (some (fn resolve-license-for [strategy]
            (resolve-licenses strategy jar))
          strategies)))

;; FIXME: want more strategies:
;; - find a LICENSE(.txt) or similar
;; - Grep in a README(.md,txt,org)
(def ^:private license-strategies
  [::pom
   ::unknown])


;; FIXME
(defn copyright-attributions [the-dep]
  (let [[lib vers jar] (unfold-dep the-dep)]
    "NYI"))

(defn all-attributions [deps]
  (for [dep deps]
    {:dep dep
     :licenses (library-attributions dep license-strategies)
     :copyrights (copyright-attributions dep)}))

  
(defn all-deps [env]
  (pod/resolve-dependencies env))



(defn repl-test []
  (-> (all-deps (boot/get-env))
      all-attributions))
  


