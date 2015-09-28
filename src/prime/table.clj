(ns prime.table)

(defn build
  "initialize a table"
  ([]
   (build [] []))

  ([rows cols]
   (build rows cols (fn [a b] nil)))

  ([rows cols f]
   (let [cells (mapv (fn [a] (mapv (fn [b] (f a b)) cols)) rows)]
     {:rows rows :cols cols :cells cells})))


;; (build)
;; (build [1 2 3] [2 2])
;; (build [1 2 3] [2 2] #(* %1 %2))
