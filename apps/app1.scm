(define (make-counter)
	(let ((count 1))
		(lambda () (set! count (+ count count)) count)))

(define counter (make-counter))

(write (counter))
(newline)
(write (counter))
(newline)
(write (counter))
(newline)
(write (counter))
(newline)
