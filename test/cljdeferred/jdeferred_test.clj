(ns cljdeferred.jdeferred-test
  (:require [clojure.test :refer :all]
            [cljdeferred :as cd]
            [cljdeferred.jdeferred :refer :all])
  (:import [org.jdeferred.impl
            DeferredObject]))

(deftest promise-chain-and-resolve
  (let [result   (atom nil)
        deferred (deferred)
        promise  (cd/promise deferred)
        named    (cd/then promise :name)
        printed  (cd/then named #(reset! result %))]
    (cd/resolve deferred {:name "logaan"})
    (is (= "logaan" @result))))


