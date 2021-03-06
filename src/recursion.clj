(ns recursion)

(defn rld [] (use 'recursion :reload))

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
  (if (empty? coll)
    false
    (= (rest coll) () )))

(defn my-last [coll]
  (let [tail (rest coll)]
    (if (empty? tail)
      (first coll)
      (my-last tail))))

(defn max-element [a-seq]
  (let [fst (first a-seq)
        rst (rest a-seq)]
    (cond
      (empty? a-seq) nil
      (empty? rst) fst
      :else (max fst (max-element rst)))))

(defn seq-max [seq-1 seq-2]
  (let [cnt1 (count seq-1)
        cnt2 (count seq-2)
        mx (max cnt1 cnt2)]
    (if (= cnt2 mx) seq-2
      seq-1)))

(defn longest-sequence [a-seq]
  (let [fst (first a-seq)
        rst (rest a-seq)]
    (cond
      (empty? a-seq) nil
      (empty? rst) fst
      :else (seq-max fst (longest-sequence rst)))))
  

(defn my-filter [pred? a-seq]
  (let [fst (first a-seq)
        rst (rest a-seq)]
    (cond
      (empty? a-seq) a-seq
      (pred? fst) (cons fst (my-filter pred? rst))
      :else (my-filter pred? rst))))

(defn sequence-contains? [elem a-seq]
  (cond
    (empty? a-seq) false
    (= (first a-seq) elem) true
    :else (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (let [fst (first a-seq)
        rst (rest a-seq)]
    (cond
      (empty? a-seq) []
      (pred? fst) (cons fst (my-take-while pred? rst))
      :else [])))
                                                                

(defn my-drop-while [pred? a-seq]
  (let [fst (first a-seq)
        rst (rest a-seq)]
    (cond
      (empty? a-seq) []
      (pred? fst) (my-drop-while pred? rst)
      :else a-seq)))

(defn seq= [a-seq b-seq]
  (= a-seq b-seq))

(defn my-map [f seq-1 seq-2]
  (cond
    (or (empty? seq-1) (empty? seq-2)) []
    :else (cons (f (first seq-1) (first seq-2))
          (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if
    (<= k 0) 1
    (* n (power n (dec k)))))

(defn fib [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else
      (+ (fib (- n 1)) (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (<= how-many-times 0) []
    (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (let [nxt (dec up-to)]
    (if (<= up-to 0) []
      (cons nxt (my-range nxt)))))

(defn tails [a-seq]
  (let [a-seq (into () (reverse a-seq))]
    (if (empty? a-seq) (cons a-seq ())
      (cons a-seq (tails (rest a-seq))))))

(defn inits [a-seq]
  (let [a-seq (into () (reverse a-seq))]
    (if (empty? a-seq) (cons a-seq ())
      (cons a-seq (inits (reverse (rest (reverse a-seq))))))))

(defn rotsHelper [a-seq cnt]
  (let [getRot (fn [s] 
                 (let [cnk (take (- (count s) 1) s)]
                 (concat (vector (last s)) cnk)))]
    (if (= cnt 0) []
      (cons a-seq (rotsHelper (getRot a-seq) (- cnt 1))))))

(defn rotations [a-seq]
  (if (empty? a-seq) [[]]
    (rotsHelper a-seq (count a-seq))))

(defn my-frequencies-helper [freqs a-seq]
  (let [frst (first a-seq)
        new-fq (if (contains? freqs frst)
                 (inc (get freqs frst))
                 1)]
    (if (empty? a-seq) freqs
      (my-frequencies-helper (assoc freqs frst new-fq) (rest a-seq)))))

(defn my-frequencies [a-seq]
  (if (empty? a-seq) {}
    (my-frequencies-helper {} a-seq)))

(defn un-frequencies [a-map]
  (let [first-pair (first a-map)
        first-fq (second first-pair)]
    (if (empty? a-map) ()
      (concat (repeat first-fq (first first-pair)) 
              (un-frequencies (rest a-map))))))

(defn my-take [n coll]
  (if (or 
        (= n 0)
        (= (first coll) nil))
    []
    (cons (first coll) (my-take (dec n) (rest coll)))))

(defn my-drop [n coll]
  (if (= n 0) coll
    (my-drop (dec n) (rest coll))))

(defn halve [a-seq]
  (let [mid (int (/ (count a-seq) 2))
        left (take mid a-seq)
        right (drop mid a-seq)]
    (assoc (assoc [] 0 left) 1 right)))

(defn seq-merge [a-seq b-seq]
  (cond
    (empty? a-seq) b-seq
    (empty? b-seq) a-seq
    (< (first a-seq) (first b-seq)) 
      (cons (first a-seq) (seq-merge (rest a-seq) b-seq))
    :else
      (cons (first b-seq) (seq-merge a-seq (rest b-seq)))))

(defn merge-sort [a-seq]
  (let [singleton? (or (= (count a-seq) 0) (= (count a-seq) 1))
        split (halve a-seq)]
    (if singleton? 
      a-seq
      (seq-merge
        (merge-sort (first split))
        (merge-sort (second split))))))


(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

