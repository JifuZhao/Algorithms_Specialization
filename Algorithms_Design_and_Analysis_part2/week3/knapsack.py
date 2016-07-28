#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/27/2016"
"""

import time
t0 = time.time()

# load the data
with open('./knapsack1.txt') as f:
    lines = f.readlines()

size, num_item = map(int, lines[0].split())
print('Size: ', size, '\t Number of items: ', num_item)

values = []
weights = []
for item in lines[1:]:
    v, w = map(int, item.split())
    values.append(v)
    weights.append(w)

# initialize a 2D array
A = [[0 for i in range(num_item + 1)] for j in range(size + 1)]

for i in range(1, num_item + 1):
    for x in range(size + 1):
        w = weights[i - 1]
        v = values[i - 1]
        A1 = A[x][i - 1]
        if w > x:
            A[x][i] = A1
        else:
            A[x][i] = max(A1, A[x - w][i - 1] + v)

print('Optimal solution: ', A[size][num_item])
print('Running time: ', time.time() - t0)
