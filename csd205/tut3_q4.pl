is_divisible(X,Y) :- Z is X mod Y, Z = 0.

has_factor_GTEQ(X,Y) :-
    is_divisible(X,Y), Y<X ;
    (Y2 is Y + 1, Y2 < X ,has_factor_GTEQ(X,Y2)).

is_prime(2).
is_prime(X) :- 
    X > 2,
    \+(has_factor_GTEQ(X,2)).


