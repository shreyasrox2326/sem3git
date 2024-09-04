# Define a list of atoms, each with its coordinates and element type.

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



import math

def get_vector(atom1, atom2):
    """Compute the vector from atom1 to atom2."""
    x1, y1, z1, _ = x[atom1]
    x2, y2, z2, _ = x[atom2]
    return (x2 - x1, y2 - y1, z2 - z1)

def dot_product(v1, v2):
    """Calculate the dot product of two vectors."""
    return v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2]

def magnitude(v):
    """Calculate the magnitude of a vector."""
    return math.sqrt(v[0]**2 + v[1]**2 + v[2]**2)

def angle_between_vectors(v1, v2):
    """Calculate the angle between two vectors in radians."""
    dot_prod = dot_product(v1, v2)
    mag_v1 = magnitude(v1)
    mag_v2 = magnitude(v2)
    cos_theta = dot_prod / (mag_v1 * mag_v2)
    # Clamp cos_theta to the range [-1, 1] to avoid potential floating point errors
    cos_theta = max(-1.0, min(1.0, cos_theta))
    theta = math.acos(cos_theta)  # angle in radians
    return theta

def get_angle_between_atoms(index1, index2, index3):
    """Calculate the angle formed by three atoms with given indices."""
    v1 = get_vector(index2, index1)
    v2 = get_vector(index2, index3)
    angle_rad = angle_between_vectors(v1, v2)
    angle_deg = math.degrees(angle_rad)  # convert to degrees
    return angle_deg

# Example usage:
index1, index2, index3 = 0,5,6
angle = get_angle_between_atoms(index1, index2, index3)
print(f"The angle between vectors formed by atoms {index2}-{index1} and {index2}-{index3} is {angle:.2f} degrees.")
