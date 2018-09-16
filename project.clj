(defproject amicli "0.1.0-SNAPSHOT"
  :description "Command-line interface to Asterisk AMI"
  :url "http://github.com/Ingemark/amicli"
  :license {:name "Apache License Version 2.0" :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.4.0"]
                 [org.clojure/core.incubator "0.1.4"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [org.asteriskjava/asterisk-java "2.0.2"]]
  :jvm-opts ["-Dlogback.configurationFile=logback.xml"]
  :main amicli.main
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[org.clojure/test.check "0.10.0-alpha3"]
                                  [org.clojure/spec.alpha "0.2.176"]
                                  [org.clojure/core.specs.alpha "0.2.44"]]}})
