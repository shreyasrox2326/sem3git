x = [[0.9217, -1.1502, -0.8019, 'O'],
     [-0.3939,    0.4857, 0.1942, 'N'],
     [-1.6726, -0.0511, -0.2043, 'C'],
     [-2.6767, 1.0746, -0.4156, 'C'],
     [-2.1763,  -1.0538,  0.8263, 'C'],
     [0.8047, -0.1205, -0.1451, 'C'],
     [1.9755,  0.6258,  0.3881, 'C'],
     [3.2176,  0.1895,   0.1583, 'C']]

import math


def get_distance(index1, index2):
    """Calculate the Euclidean distance between two atoms given their indices."""
    x1, y1, z1, a1 = x[index1]
    x2, y2, z2, a2 = x[index2]
    
    distance = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2 + (z2 - z1) ** 2)
    return distance

for i in range (8):
    for j in range (8):
        stringg = str(get_distance(i,j))[:5]
        if (len(stringg) < 5):
            stringg += '0'*(5 - len(stringg))
        print (i,x[i][3]," ",j,x[j][3]," ",stringg,end="| ",sep="")
    print('')


