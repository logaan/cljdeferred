# cljdeferred

[Promises/A+] [promises] protocol for Clojure with implementation using
[JDeferred] [jdeferred].

## Install

``` clojure
[cljdeferred "0.1.0-SNAPSHOT"]
```

## Usage

``` clojure
(ns cljdeferred.jdeferred-test
  (:require [cljdeferred :as cd]
            [cljdeferred.jdeferred :refer :all]))

(let [result   (atom nil)
      deferred (deferred)
      promise  (cd/promise deferred)
      named    (cd/then promise :name)
      printed  (cd/then named #(reset! result %))]
  (cd/resolve deferred {:name "logaan"})
  @result) ; => "logaan"
```

## License

Copyright © 2013 Logan Campbell

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[promises]: http://promises-aplus.github.io/promises-spec/
[jdeferred]: http://jdeferred.org/
