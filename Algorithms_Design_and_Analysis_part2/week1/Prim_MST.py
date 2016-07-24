#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/23/2016"
"""

import time

# load the data
with open('./edges.txt') as f:
    sequence = f.readlines()

num_nodes, num_edges = map(int, sequence[0].split())

graph = dict()
for item in sequence[1:]:
    u, v, cost = map(int, item.split())
    if u < v:
        graph[(u, v)] = cost
    else:
        graph[(v, u)] = cost


# method 1, a little slow
t0 = time.time()

# initialize the parameters
X = set()
X.add(1)
V = set(range(2, num_nodes + 1))
cost = 0

while len(X) < num_nodes:
    temp_min = 100000000
    temp_u = None
    temp_v = None
    for u in X:
        for v in V:
            if u < v:
                node1 = u
                node2 = v
            else:
                node1 = v
                node2 = u
            if (node1, node2) in graph:
                length = graph[(node1, node2)]
                if length < temp_min:
                    temp_min = length
                    temp_u = u
                    temp_v = v
    X.add(temp_v)
    V.remove(temp_v)
    cost += temp_min

print('Total Cost:\t', cost)
print('method 1\t', time.time() - t0)

# method 2, much faster
t0 = time.time()

# initialize the parameters
X = set()
X.add(1)
V = set(range(2, num_nodes + 1))
cost = 0
while len(X) < num_nodes:
    keys = graph.keys()
    temp_min = 100000000
    temp_u = None
    temp_v = None
    for (u, v) in keys:
        if (u in X) and (v in V):
            length = graph[(u, v)]
            if length < temp_min:
                temp_min = length
                temp_u = u
                temp_v = v
                dict_u = u
                dict_v = v
        elif (v in X) and (u in V):
            length = graph[(u, v)]
            if length < temp_min:
                temp_min = length
                temp_u = v
                temp_v = u
                dict_u = u
                dict_v = v

    del graph[(dict_u, dict_v)]
    X.add(temp_v)
    V.remove(temp_v)
    cost += temp_min

print('Total Cost:\t', cost)
print('method 2\t', time.time() - t0)
