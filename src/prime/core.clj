(ns prime.core
  (:require [clojure.math.numeric-tower :as m]))

(defn prime?
  "returns true when number is prime"
  [n]
  (let [range-end (inc (m/floor (m/sqrt n)))]
    (if (< n 2)
      false
      (empty? (filter #(= (mod n %) 0) (drop 2 (range range-end)))))))

(defn primes
  "returns a lazy seq of n first prime number"
  [n]
  (take n (filter prime? (range))))
