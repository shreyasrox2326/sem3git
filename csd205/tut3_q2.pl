% Base case: If Y is 0, the GCD is X.
gcd(X, 0, X) :- X > 0.

% Recursive case: If Y is not 0, recursively call gcd with Y and X mod Y.
gcd(X, Y, Z) :- 
    Y > 0, 
    Rem is X mod Y, 
    gcd(Y, Rem, Z).