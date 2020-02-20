;; 円周率
(define PI 3.14159265358979323846) 

 
;; 亀の位置 
;; 
;; 座標系  
(define tx 0) 
(define ty 0) 

;; 亀の進行方向 
(define direction 0) 
 
;; ペンの状態 
(define pendown #f) 
 
;; 経路のリスト
(define keiro_list '())

;; キャンバスの作成
(define CANVAS (canvas 480 480))


;; ペンが降りているか否か
(define (pen x) 
	(if (eq? x 'down) (set! pendown #t) (set! pendown #f))) 
 

(define (forward distance) 
	(let 
		((nx (+ tx (* distance (cos (/ (* PI direction) 180))))) 
     	(ny (+ ty (* distance (sin (/ (* PI direction) 180))))))
    	;; 経路のリストの作成
    	(if pendown (set! keiro_list (append keiro_list (list (list (list tx ty) (list nx ny))))))
    	;; 直線描画(Javaによる図形描画)
    	(drawline CANVAS keiro_list)
    	(set! tx nx) 
    	(set! ty ny))) 
 
 
(define (left angle) 
	(set! direction (+ direction angle))) 
 
 
(define (right angle) 
	(set! direction (- direction angle))) 
 


 
;; 以下、タートルグラフィックスの描画プログラム  
(define (c-curve-draw length level) 
	(if (= level 0)  
		(forward length) 
       	(let ((next-length (* length (/ (sqrt 2) 2)))) 
 			(left 45) 
 			(c-curve-draw next-length (- level 1)) 
 			(right 90) 
 			(c-curve-draw next-length (- level 1)) 
			(left 45)
		))) 
 
 
(define (c-curve length level) 
(pen 'down) 
(c-curve-draw length level)) 
 
 
(c-curve 120 8) 
