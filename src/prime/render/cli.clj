(ns prime.render.cli
  (:require [clojure.string :as s]))

(defn- make-space [n]
  (apply str (repeat n \space)))

(defn- rjust [width s]
  (let [a (str s)]
    (str (make-space (- width (count a))) a)))

(defn- cell-width [coll]
  (count (str (apply max (flatten coll)))))

(defn- prepend-row-header [table padding-fn]
  (map-indexed (fn [idx row] 
                 (into [] (concat 
                           (vec (padding-fn (nth (:rows table) idx ""))) 
                           (mapv #(padding-fn (str %)) row)))) 
               (:cells table)))

(defn- prepare-matrix [table padding-fn]
  (let [c-header (into [] (concat [\space] (mapv #(padding-fn (str %)) (:cols table))))]
    (into [] (concat [c-header] (prepend-row-header table padding-fn)))))

(defn draw
  "pretty print of table"
  ([table]
   (draw table \space \newline))

  ([table seperator new-line]
   (let [padding (partial rjust (cell-width (:cells table)))
         data (prepare-matrix table padding)]
     (println (s/join new-line data)))))



;; (def t {:rows '(2 3)
;;         :cols '(2 3)
;;         :cells [[4 6] [6 9] [10 15] [14 21] [22 33]]})
;; (draw t)
