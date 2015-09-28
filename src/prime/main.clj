(ns prime.main
  (:require [prime.core :refer [primes]]
            [prime.table :as table]
            [prime.render.cli :as cli]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))


(def options-spec
  [["-h" "--help" "Show this message"]
   ["-s" "--size SIZE" "table size 10x5 -- 10 rows by 5 columns"
    :default {:rows 10 :cols 10}
    :parse-fn (fn [s] 
                (let [[rows cols] (mapv #(Integer/parseInt %) (clojure.string/split s #"x"))]
                  {:rows rows :cols cols}))
    ]])

(defn -main [& args]
  (let [{:keys [options arguments error summary]} (parse-opts args options-spec)
        rows (get-in options [:size :rows])
        cols (get-in options [:size :cols])]
    (cli/draw 
     (table/build (primes rows) (primes cols) #(* %1 %2)))))

