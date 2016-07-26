#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/25/2016"
"""

import time
t0 = time.time()

# load the data
with open('./clustering_big.txt') as f:
    lines = f.readlines()

num_nodes, num_bits = map(int, lines[0].split())

# using dictionary to store the data
graph = dict()
for item in lines[1:]:
    graph[tuple(map(int, item.split()))] = 1

def hamming_distance(u, v):
    return sum([1 if i == j else 0 for (i, j) in zip(u, v)])

keys = list(graph.keys())
selected_key = keys[0]
k = len(keys)
num = 0
while (len(keys) > 0):
    num += 1
    print(num)
    for another_key in keys[1:]:
        if hamming_distance(selected_key, another_key) <= 2:
            del graph[another_key]
            k -= 1
            num += 1
    del graph[selected_key]

    keys = list(graph.keys())
    selected_key = keys[0]

print(k)
print(num)
print(time.time() - t0)
