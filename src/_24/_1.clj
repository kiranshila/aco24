(ns _24._1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "24/1")))

(def lr
  (->> (str/split-lines input)
     (map #(str/split % #"\s+"))
     (map (partial map parse-long))
     (reduce (fn [[l r] [x y]] [(conj l x) (conj r y)]) [[] []])))

;; part 1
(->> lr
     (map sort)
     (apply map vector)
     (reduce (fn [acc [x y]] (+ acc (abs (- x y)))) 0))

;; part 2
(->> [(first lr) (frequencies (second lr))]
     ((fn [[l rm]]
        (loop [acc 0 l l]
          (if-let [x (peek l)]
            (recur (+ acc (* x (rm x 0))) (pop l))
            acc)))))
