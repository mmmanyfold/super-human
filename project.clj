(defproject super-human "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [io.nervous/cljs-lambda    "0.3.5"]]
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-npm                    "0.6.2"]
            [lein-doo                    "0.1.7"]
            [io.nervous/lein-cljs-lambda "0.6.6"]]
  :npm {:dependencies [[serverless-cljs-plugin "0.1.2"]
                       [request                 "2.81.0"]
                       [request-promise         "4.2.0"]]}
  :cljs-lambda {:compiler
                {:inputs  ["src"]
                 :options {:output-to     "target/super-human/super_human.js"
                           :output-dir    "target/super-human"
                           :target        :nodejs
                           :language-in   :ecmascript5
                           :optimizations :none}}})
