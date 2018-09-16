(ns amicli.main
  (:require amicli
            [clojure.tools.cli :as cli])
  (:gen-class))

(defn cli-options [])

(defn -main
  "Takes a filename of a script to be loaded as argument."
  [& args]
  (let [{:keys [options arguments errors summary]} (cli/parse-opts args nil)]
    (if errors
      (doall (map println errors))
      (load-file (first arguments)))))

