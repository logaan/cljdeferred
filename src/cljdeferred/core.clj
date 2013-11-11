(ns cljdeferred.core
  (:refer-clojure :exclude [resolve promise]))

(defprotocol PromiseAPlus
  (then [promise on-fulfilled]
        [promise on-fulfilled on-rejected]))

(defprotocol Deferred
  (resolve [deferred value])
  (reject [deferred reason])
  (promise [deferred]))

