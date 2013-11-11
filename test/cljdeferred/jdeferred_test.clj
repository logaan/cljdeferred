(ns cljdeferred.jdeferred-test
  (:require [clojure.test :refer :all]
            [cljdeferred.core :as c]
            [cljdeferred.jdeferred :refer :all])
  (:import [org.jdeferred.impl
            DeferredObject]))

(deftest promise-chain-and-resolve
  (let [result   (atom)
        deferred (deferred)
        promise  (c/promise deferred)
        named    (c/then promise :name)
        printed  (c/then named #(reset! result %))]
    (c/resolve deferred {:name "logaan"})
    (is (= "logaan" @result))))


