(ns super-human.core
  (:require [cljs-lambda.macros :refer-macros [defgateway]]
            [cljs.nodejs :as nodejs]))

(def http (nodejs/require "request-promise"))

(defn get-env
  "Retrieve environment variable from process.env"
  [var]
  (-> nodejs/process
      .-env
      (aget var)))

(def FIREBASE-URL (get-env "SUPER_HUMAN_FIREBASE_URL"))

(defn GET [url]
  (http (clj->js {:url url :method "GET" :json true})))

(defn PUT
  [url body]
  (let [opts {:url url :method "PUT" :json true}]
    (http (clj->js (assoc opts :body body)))))

(defgateway btn-press [event _]
  (let [attributes (.-arr event)
        serial-num (nth attributes 1)
        click-type (nth attributes 5)
        now (js/Date.now)
        firebase-endpoint (str FIREBASE-URL serial-num "/" now ".json")]
    (PUT firebase-endpoint {:click-type click-type})
    {:status  200
     :headers {:content-type (-> event :headers :content-type)}
     :body    (event :body)}))
