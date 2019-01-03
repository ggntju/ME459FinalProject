from random import random
import numpy as np
import sys

if (len(sys.argv) != 3):
    print("Usage: generate_sphere.py [# of spheres] [radius]")
n_spheres = (int)(sys.argv[1])
radius = (float)(sys.argv[2])

output_file = "spheres.input"

with open(output_file, 'w') as file:
	file.write("x,y,z\n")
	file.write(str(radius) + "\n")
	file.write(str(n_spheres) + "\n")
	for i in range(1, n_spheres + 1):
		v1 = random()
		v2 = random()
		v3 = random()
		file.write(str(v1) + "," + str(v2) + "," + str(v3) + "\n")
