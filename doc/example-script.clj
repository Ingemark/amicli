(ns amicli.user
  (:require [amicli :refer :all]))

(defn script []
  (-> (connect {:host "localhost"
                :username "admin"
                :password "dials"})
      (originate-call {:exten "100"
                       :channel "SIP/139"
                       :context "default"
                       :async true})))
(script)
