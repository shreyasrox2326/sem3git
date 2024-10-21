male(kuru).
male(shantanu).
male(bhishma).
male(chitrangada).
male(vichitravirya).
male(vyasa).
male(parashara).
male(dhritarashtra).
male(yudishtira).
male(bhima).
male(arjuna).
male(nakula).
male(sahadeva).
male(shakuni).
male(pandu).
male(duryodhana).
male(dushasana).
male(sons_98).
male(abhimanyu).
male(parikshith).

female(satyavati).
female(ganga).
female(draupadi).
female(subhadra).
female(ambika).
female(ambalika).
female(gandhari).
female(kunti).
female(madri).
female(dussala).
female(srutakriti).
female(uttara).

pandava(yudishtira).
pandava(bhima).
pandava(arjuna).
pandava(nakula).
pandava(sahadeva).

kaurava(duryodhana).
kaurava(dussala).
kaurava(dushasana).
kaurava(sons_98).


parent(kuru,shantanu).

parent(ganga, bhishma).
parent(shantanu, bhishma).

parent(shantanu, chitrangada).
parent(satyavati, chitrangada).

parent(satavati, vichitravirya).
parent(shantanu, vichitravirya).

parent(parashara, vyasa).
parent(satyavati, vyasa).

parent(ambika, dhritarashtra).
parent(vyasa, dhritarashtra).

parent(vyasa, pandu).
parent(ambalika, pandu).

parent(dhritarashtra, duryodhana).
parent(gandhari, duryodhana).

parent(dhritarashtra, dussala).
parent(gandhari, dussala).

parent(dhritarashtra, dushasana).
parent(gandhari, dushasana).

parent(dhritarashtra, sons_98).
parent(gandhari, sons_98).

parent(kunti, yudishtira).
parent(pandu, yudishtira).

parent(kunti, bhima).
parent(pandu, bhima).

parent(kunti, arjuna).
parent(pandu, arjuna).

parent(pandu, nakula).
parent(madri, nakula).

parent(pandu, sahadeva).
parent(madri, sahadeva).

parent(arjuna, abhimanyu).
parent(subhadra, abhimanyu).

parent(arjuna, srutakriti).
parent(draupadi, srutakriti).

parent(abhimanyu, parikshith).
parent(uttara, parikshith).

married(shantanu, ganga).
married(shantanu, satyavati).
married(parashara, satyavati).
married(vichitravirya, ambika).
married(vyasa, ambika).
married(vyasa, ambalika).
married(dhritarashtra, gandhari).
married(pandu, kunti).
married(pandu, madri).
married(arjuna, draupadi).
married(arjuna, subhadra).
married(abhimanyu, uttara).

%rules

married(X, Y) :- married(Y, X).

ancestor(X, Y) :- parent(X, Y).
ancestor(X, Z) :- parent(X, Y), ancestor(Y, Z).

mother(X, Y) :- female(X), parent(X, Y).
father(X, Y) :- male(X), parent(X, Y).

wife(X, Y) :- female(X), married(X, Y).
husband(X, Y) :- male(X), married(X, Y).

step_sibling(X, Y) :-  
    X \= Y,
    (
        (father(Z, X), father(Z, Y), \+ (mother(W, X), mother(W, Y)));
        (mother(W, X), mother(W, Y), \+ (father(Z, X), father(Z, Y)))
    ).

step_brother(X, Y) :- male(X), step_sibling(X, Y).
step_sister(X, Y) :- female(X), step_sibling(X, Y).

brother(X, Y) :- male(X), X\=Y ,father(Z, X), father(Z, Y),  mother(W, X), mother(W, Y).
brother(shakuni, gandhari).

sister(X, Y) :- female(X), X\=Y ,father(Z, X), father(Z, Y),  mother(W, X), mother(W, Y).
sister(gandhari, shakuni).


step_mother(X, Y) :- female(X), married(Z, X), parent(Z, Y), \+parent(X,Y).
step_father(X, Y) :- male(X), married(Z, X), parent(Z, Y), \+ parent(X, Y).

grandfather(X, Y) :- male(X), parent(X, Z), parent(Z, Y).
grandmother(X, Y) :- female(X), parent(X, Z), parent(Z, Y).

maternal_aunt(X, Y) :- female(X), sister(X, Z), mother(Z, Y).
maternal_uncle(X, Y) :- male(X), brother(X, Z), mother(Z, Y).

maternal_uncle_wife(X, Y) :- female(X), married(X, Z), maternal_uncle(Z, Y).
maternal_aunt_husband(X, Y) :- male(X), married(X, Z), maternal_aunt(Z, Y).

paternal_aunt(X, Y) :- female(X), sister(X, Z), father(Z, Y).
paternal_uncle(X, Y) :- male(X), brother(X, Z), father(Z, Y).

paternal_aunt_husband(X, Y) :- male(X), married(X, Z), paternal_aunt(Z, Y).
paternal_uncle_wife(X, Y) :- female(X), married(X, Z), paternal_uncle(Z, Y).