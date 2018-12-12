#!/usr/bin/env python3

# Author: Nic Olsen
# (c) 2018
# Generates a triangle mesh of the _surface_ of the unit cube
# defined by: {(x,y,z): x,y,z \in [0,1]}

import numpy as np
import numpy.linalg as la


def mesh_plane(lo, v1, v2, d, n_intervals, file):
    v1 = v1 / la.norm(v1)
    v2 = v2 / la.norm(v2)

    for i in range(n_intervals):
        for j in range(n_intervals):
            # Construct two triangles corresponding to this parallelogram
            pos = lo + i * d * v1 + j * d * v2

            # Triangle 1
            node1 = pos
            node2 = pos + d * v1 + d * v2
            node3 = pos + d * v2
            file.write("{0},{1},{2}\n".format(node1[0], node1[1], node1[2]))
            file.write("{0},{1},{2}\n".format(node2[0], node2[1], node2[2]))
            file.write("{0},{1},{2}\n".format(node3[0], node3[1], node3[2]))

            # Triangle 2
            node1 = pos
            node2 = pos + d * v1
            node3 = pos + d * v1 + d * v2
            file.write("{0},{1},{2}\n".format(node1[0], node1[1], node1[2]))
            file.write("{0},{1},{2}\n".format(node2[0], node2[1], node2[2]))
            file.write("{0},{1},{2}\n".format(node3[0], node3[1], node3[2]))

    return n_intervals * n_intervals * 2


output_file = "mesh.input"
n_triangles = 0

with open(output_file, 'w') as file:
    # Global origin
    origin = np.array([0, 0, 0])

    # Basis vectors
    v1 = np.array([1, 0, 0])
    v2 = np.array([0, 1, 0])
    v3 = np.array([0, 0, 1])

    # Width of subdividing quads
    d = 0.1

    # Number of quads in each direction per plane
    n_intervals = 10

    # Low X plane
    n_triangles = n_triangles + \
        mesh_plane(origin, v2, v3, d, n_intervals, file)

    # High X plane
    n_triangles = n_triangles + \
        mesh_plane(origin + v1, v2, v3, d, n_intervals, file)

    # Low Y plane
    n_triangles = n_triangles + \
        mesh_plane(origin, v1, v3, d, n_intervals, file)

    # High Y plane
    n_triangles = n_triangles + \
        mesh_plane(origin + v2, v1, v3, d, n_intervals, file)

    # Low Z plane
    n_triangles = n_triangles + \
        mesh_plane(origin, v1, v2, d, n_intervals, file)

    # High Z plane
    n_triangles = n_triangles + \
        mesh_plane(origin + v3, v1, v2, d, n_intervals, file)

# Prepend the triangle count
with open(output_file, 'r') as file:
    contents = file.read()
with open(output_file, 'w') as file:
    file.write(str(n_triangles) + "\n" + contents)
