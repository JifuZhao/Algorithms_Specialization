#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "08/01/2016"
"""

import numpy as np
import time

t0 = time.time()

# load the data
with open('./tsp_test.txt') as f:
    lines = f.readlines()

num_city = int(lines[0])

location = []
for item in lines[1:]:
    x, y = map(float, item.split())
    location.append((x, y))

# fuction to compute the distance between two cities
def compute_distance(a, b):
    x = a[0] - b[0]
    y = a[1] - b[1]
    distance = np.sqrt(x ** 2 + y ** 2)
    return distance

# compute distance matrix to save time
distance = np.zeros((num_city, num_city))
for i in range(num_city):
    for j in range(num_city):
        distance[i, j] = compute_distance(location[i], location[j])

# Initialize a dictionary to save data
Inf = float('inf')
A = dict()
A[((1, ), 1)] = 0

N = num_city
# Dynamic Programming Algorithm for TSP
for m in range(2, N + 1):
    # print('Current m is: ', m)
    # print(A)
    old_keys = list(A.keys())
    S = []  # create subset S
    for key in old_keys:
        old_set = key[0]
        if len(old_set) < m - 1:
            del A[key]
            continue
        for i in range(old_set[-1] + 1, num_city + 1):
            new_set = old_set + (i,)
            S.append(new_set)
    print('m =', m, '\tlen(S) =', len(S))
    for sub_S in S:
        # A[(sub_S, 1)] = Inf
        for j in sub_S[1:]:
            if len(sub_S) == 2:
                A[(sub_S, j)] = distance[sub_S[0] - 1, sub_S[1] - 1]
                continue
            minimum = Inf
            for k in sub_S:
                if k == j:
                    continue
                elif k == 1:
                    continue
                else:
                    # print(sub_S, j, k)
                    sub_key = tuple(x for x in sub_S if x != j)
                    temp_value = A[(sub_key, k)] + distance[k - 1, j - 1]
                    if temp_value < minimum:
                        minimum = temp_value
            A[(sub_S, j)] = minimum

key0 = tuple(x for x in range(1, N + 1))
minimum = Inf
temp_j = None
for j in range(2, N + 1):
    temp_value = A[(key0, j)] + distance[j - 1, 0]
    if temp_value < minimum:
        minimum = temp_value
        temp_j = j

print('Minimum is: ', minimum)
print('Time usage: ', time.time() - t0)
