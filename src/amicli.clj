(ns amicli
  (:require [clojure.core.strint :refer (<<)]
            [clojure.string :as s])
  (:import (org.asteriskjava.manager ManagerConnectionFactory)
           (clojure.lang Reflector RT)))

(defn- upcamelize [s] (s/replace (name s) #"(?:-|^)(\w)" (comp s/upper-case second)))

(defn- invoke [^String m o & args]
  (Reflector/invokeInstanceMethod o m (into-array Object args)))

(defn- proptype [b p]
  (let [t (-> b type (.getMethod (str "get" (upcamelize p)) nil) .getReturnType)]
    ({Boolean/TYPE Boolean, Byte/TYPE Byte, Character/TYPE Character, Short/TYPE Short,
      Integer/TYPE Integer, Long/TYPE Long Float/TYPE Float, Double/TYPE Double} t t)))

(defn- coerce [b p v]
  (condp = [(type v) (proptype b p)]
    [String Boolean] (Boolean/valueOf ^String v)
    [String Integer] (Integer/valueOf ^String v)
    [String Long] (Long/valueOf ^String v)
    v))

(defn action [type params]
  (let [a (Reflector/invokeConstructor
           (RT/classForName (<< "org.asteriskjava.manager.action.~{type}Action"))
           (object-array 0))]
    (doseq [[k v] params] (invoke (<< "set~(upcamelize k)") a (coerce a k v)))
    a))

(defn action-id [] (.substring (.toString (java.util.UUID/randomUUID)) 0 8))

(defn connect [{:keys [host username password]}]
  (let [connection (-> (ManagerConnectionFactory. host username password)
                       .createManagerConnection)]
    (.login connection)
    connection))

(defn originate-call [amiconn options]
  (.sendAction amiconn (action "Originate" options)))
