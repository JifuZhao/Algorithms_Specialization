#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/20/2016"
"""
# use list to store data
graph = []
length = []

# load the data
with open('./dijkstraData.txt') as f:
    lines = f.readlines()

# save data into graph and length respectively
for line in lines:
    graph.append([])
    length.append([])
    splits = line.split()[1:]
    for item in splits:
        v, l = map(int, item.split(','))
        graph[-1].append(v)
        length[-1].append(l)

# initialize parameters
X = [1]  # save vertices that have been visited
A = dict()  # save the shortest length
A[1] = 0  # begin from vertex 1

# main loop
while len(X) < 200:
    temp_length = 1000000
    temp_w = None
    for v in X:  # v should be from X
        for i in range(len(graph[v - 1])):
            w = graph[v - 1][i]
            if w not in X:  # make sure w hasn't been added into X
                l = A[v] + length[v - 1][i]
                if l <= temp_length:
                    temp_length = l
                    temp_w = w
    X.append(temp_w)
    A[temp_w] = temp_length

k = [7, 37, 59, 82, 99, 115, 133, 165, 188, 197]
for i in k:
    print(i, '\t', A[i])
