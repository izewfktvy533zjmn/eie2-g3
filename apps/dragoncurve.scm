;; 円周率
(define PI 3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128)
;;
;; 亀の位置
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

(define (dragon-curve-draw length level)
  (if (= level 0) 
      (forward length)
      (let ((next-length (* length (/ (sqrt 2) 2))))
	(pen 'down)
	(left 45)
	(dragon-curve-draw next-length (- level 1))

	(right 90)
	(pen 'up)
	(forward next-length)
	(right 180)
	(pen 'down)
	(dragon-curve-draw next-length (- level 1))

	(right 180)
	(pen 'up)
	(forward next-length)
	(left 45)
	(pen 'down))))

(define (dragon-curve length level)
       (pen 'down)
       (dragon-curve-draw length level))

(dragon-curve 120 10)
