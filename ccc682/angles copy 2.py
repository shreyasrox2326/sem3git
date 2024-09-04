import math

# Define the list of atoms with coordinates and element types
x = [
    [0.9217, -1.1502, -0.8019, 'O'],
    [-0.3939, 0.4857, 0.1942, 'N'],
    [-1.6726, -0.0511, -0.2043, 'C'],
    [-2.6767, 1.0746, -0.4156, 'C'],
    [-2.1763, -1.0538, 0.8263, 'C'],
    [0.8047, -0.1205, -0.1451, 'C'],
    [1.9755, 0.6258, 0.3881, 'C'],
    [3.2176, 0.1895, 0.1583, 'C']
]

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

def cross_product(v1, v2):
    """Compute the cross product of two vectors."""
    return (
        v1[1] * v2[2] - v1[2] * v2[1],
        v1[2] * v2[0] - v1[0] * v2[2],
        v1[0] * v2[1] - v1[1] * v2[0]
    )

def angle_between_vectors(v1, v2):
    """Calculate the angle between two vectors in radians."""
    dot_prod = dot_product(v1, v2)
    mag_v1 = magnitude(v1)
    mag_v2 = magnitude(v2)
    cos_theta = dot_prod / (mag_v1 * mag_v2)
    # Clamp the cosine value to the range [-1, 1] to avoid floating point errors
    cos_theta = max(-1.0, min(1.0, cos_theta))
    return math.acos(cos_theta)

def get_normal_vector(index1, index2, index3):
    """Compute the normal vector of the plane formed by three atoms."""
    v1 = get_vector(index2, index1)
    v2 = get_vector(index2, index3)
    return cross_product(v1, v2)

def get_dihedral_angle(index1, index2, index3, index4, index5, index6):
    """Calculate the dihedral angle between two planes formed by 123 and 234."""
    normal1 = get_normal_vector(index1, index2, index3)
    normal2 = get_normal_vector(index4, index5, index6)
    
    # Calculate the angle between the two normal vectors
    dot_prod = dot_product(normal1, normal2)
    mag_normal1 = magnitude(normal1)
    mag_normal2 = magnitude(normal2)
    
    # Calculate cosine of the angle
    cos_theta = dot_prod / (mag_normal1 * mag_normal2)
    
    # Clamp the cosine value to the range [-1, 1] to avoid floating point errors
    cos_theta = max(-1.0, min(1.0, cos_theta))
    
    # Angle between normals in radians
    angle_rad = math.acos(cos_theta)
    
    # Convert to degrees
    angle_deg = math.degrees(angle_rad)
    
    # Compute vectors for orientation check
    v1 = get_vector(index2, index1)
    v2 = get_vector(index2, index3)
    v3 = get_vector(index5, index4)
    v4 = get_vector(index5, index6)
    
    # Compute cross products for orientation check
    normal1 = cross_product(v1, v2)
    normal2 = cross_product(v3, v4)
    
    # Calculate the direction of the dihedral angle
    cross_prod_v = cross_product(normal1, normal2)
    if dot_product(cross_prod_v, get_vector(index1, index4)) < 0:
        angle_deg = 180 - angle_deg
    else: angle_deg = 180 + angle_deg
    return angle_deg

# Example usage:
index1, index2, index3, index4, index5, index6 = 3,2,1,4,2,3
dihedral_angle = get_dihedral_angle(index1, index2, index3, index4, index5, index6)
print(f"The dihedral angle between the planes formed by atoms {index1}-{index2}-{index3} and {index4}-{index5}-{index6} is {dihedral_angle:.2f} degrees.")
