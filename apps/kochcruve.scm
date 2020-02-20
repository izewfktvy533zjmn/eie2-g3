;; 円周率
(define PI 3.14159265358979323846) 
;; 亀の位置
;;
;; 座標系
;; y
;; |
;; +- x
(define tx 0)
(define ty 0)

;; 亀の進行方向
(define direction 0)

;; ペンの状態
(define pendown #f)

(define keiro_list '())

(define CANVAS (canvas 480 480))

(define (pen x)
  (if (eq? x 'down) (set! pendown #t) (set! pendown #f)))

(define (forward distance)
  (let ((nx (+ tx (* distance (cos (/ (* PI direction) 180)))))
	(ny (+ ty (* distance (sin (/ (* PI direction) 180))))))
    (if pendown (set! keiro_list (append keiro_list (list (list (list tx ty) (list nx ny))))))
    (drawline CANVAS keiro_list)
	(set! tx nx)
    (set! ty ny)))

(define (left angle)
  (set! direction (+ direction angle)))

(define (right angle)
  (set! direction (- direction angle)))


;; 以下、タートルグラフィックスの描画プログラム

(define (koch-curve-draw length level)
  (if (= level 0) 
      (forward length)
      (let ((next-length (/ length 3)))
	(koch-curve-draw next-length (- level 1))

	(left 60)
	(koch-curve-draw next-length (- level 1))

	(right 120)
	(koch-curve-draw next-length (- level 1))

	(left 60)
	(koch-curve-draw next-length (- level 1)))))

(define (koch-curve length level)
  (pen 'down)
  (koch-curve-draw length level))

(koch-curve 200 4)

