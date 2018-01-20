(ns super-human.core
  (:require [cljs-lambda.macros :refer-macros [defgateway]]
            [cljs.nodejs :as nodejs]))

(def fb-url "https://super-human-4913b.firebaseio.com/7FDW.json")

(def http (nodejs/require "request-promise"))

(defn GET [url]
  (http (clj->js {:url url :method "GET" :json true})))

(defn PUT
  [url body]
  (let [opts {:url url :method "PUT" :json true}]
    (http (clj->js (assoc opts :body body)))))

(defgateway echo [event ctx]
  (js/console.log "event: " event)
  (js/console.log "ctx: " ctx)
  {:status  200
   :headers {:content-type (-> event :headers :content-type)}
   :body    (event :body)})
