(ns _24._2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "24/2")))

(def reports
  (->> (str/split-lines input)
       (mapv #(str/split % #" "))
       (mapv #(mapv parse-long %))))

(defn safe? [l]
  (let [s (mapv - (rest l) (butlast l))]
    (and (apply = (map pos? s))
         (every? #(<= 1 (abs %) 3) s))))

;; part 1
(count (filter safe? reports))

;; part 2 (brute force)
(defn safe-dampened? [report]
  (loop [i 0]
    (when (< i (count report))
      (let [subreport (concat (subvec report 0 i) (subvec report (inc i)))]
        (or (safe? subreport) (recur (inc i)))))))

(count (filter safe-dampened? reports))
