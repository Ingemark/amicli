# Asterisk AMI Command Line Interface

[![Build Status](https://travis-ci.com/Ingemark/pbxis.svg?branch=master)](https://travis-ci.com/Ingemark/pbxis)

*amicli* provides various AMI-related Clojure functions for use in scripts which
can be run from the UNIX command line. For example, it can be used to make phone
calls from shell scripts.

## Usage

Build the application with `lein uberjar` and run in the following way:

```
java -cp amicli-standalone.jar amicli.main script.clj
```

An example of the script:

```
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
```

## License

Copyright © 2018 Ingemark d.o.o. 

Distributed under the terms of Apache License version 2.0.
