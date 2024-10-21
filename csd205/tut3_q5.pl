is_divisible(X, Y) :- Z is X mod Y, Z =:= 0.

% Check for factors of X greater than or equal to Y
has_factor_gteq(X, Y) :- 
    is_divisible(X, Y), Y < X ;
    (Y2 is Y + 1, Y2 < X, has_factor_gteq(X, Y2)).

is_prime(2).
is_prime(X) :- 
    X > 2,
    \+(has_factor_gteq(X, 2)).

% check if prime factors are correct
factors(Number, Factors) :-
    product_factors(Factors, Product),
    Product =:= Number.

product_factors([], 1). %initial condition, start product with 1
product_factors([[Prime, Mult]|Rest], Product) :-
    is_prime(Prime),  % Ensure the prime factor is indeed prime
    Power is Prime ^ Mult,  % Calculate Prime raised to the power of Mult
    product_factors(Rest, RestProduct),  % Recursively calculate the product of the rest
    Product is Power * RestProduct.  % Multiply the current product
 
% e.g. usage ?- factors(24, [[2, 3], [3, 1]]).
% result: True