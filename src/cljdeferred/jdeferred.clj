(ns cljdeferred.jdeferred
  (:require [cljdeferred.core :as c])
  (:import [org.jdeferred.impl
            DeferredObject]
           [org.jdeferred
            DoneFilter
            FailCallback]))

(defn deferred []
  (DeferredObject.))

(extend-type org.jdeferred.Deferred
  c/PromiseAPlus
  (then
    ([promise on-fulfilled]
     (.then promise (reify DoneFilter
                      (filterDone [this value]
                        (on-fulfilled value)))))
    ([promise on-fulfilled on-rejected]
     (.fail promise (reify FailCallback
                      (onFail [this reason]
                        (on-rejected reason)))) 
     (c/then promise on-fulfilled)))

  c/Deferred
  (resolve [deferred value]
    (.resolve deferred value))
  (reject [deferred reason]
    (.reject deferred reason))
  (promise [deferred]
    (.promise deferred)))

