gcd(X,0,X) :- X>0.
gcd(X,Y,Z) :- 
    Y>0,
    Rem is X mod Y,
    gcd(Y, Rem, Z).

lcm(X,Y,Z) :- 
    Num = X*Y,
    gcd(X,Y,W),
    Z is Num/W.