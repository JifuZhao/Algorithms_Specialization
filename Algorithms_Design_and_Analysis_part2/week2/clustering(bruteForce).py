#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/25/2016"
"""

# load the data
with open('./clustering1.txt') as f:
    lines = f.readlines()

num_nodes = int(lines[0])

# using dictionary to store the graph
graph = dict()
for item in lines[1:]:
    u, v, cost = map(int, item.split())
    graph[(u, v)] = cost

# initialize the clusters
clusters = dict()
for node in range(1, 501):
    clusters[node] = [node]

k = 4  # keep 4 clusters
keys = sorted(clusters.keys())
num_clusters = len(clusters.keys())

while num_clusters > k:
    min_cost = 1000000
    min_i = None
    min_j = None

    for i in range(num_clusters - 1):
        for j in range(i + 1, num_clusters):
            key1 = keys[i]
            key2 = keys[j]
            for node1 in clusters[key1]:
                for node2 in clusters[key2]:
                    if node1 < node2:
                        distance = graph[(node1, node2)]
                    else:
                        distance = graph[(node2, node1)]

                    if distance <= min_cost:
                        min_cost = distance
                        min_i = key1
                        min_j = key2

    clusters[min_i] += clusters[min_j]
    del clusters[min_j]
    keys = sorted(clusters.keys())
    num_clusters = len(clusters.keys())

# calculate the max spacing
keys = sorted(clusters.keys())
num_clusters = len(keys)
max_spacing = []

for i in range(num_clusters - 1):
    for j in range(i + 1, num_clusters):
        min_distance = 100000
        key1 = keys[i]
        key2 = keys[j]
        for node1 in clusters[key1]:
            for node2 in clusters[key2]:
                if node1 < node2:
                    distance = graph[(node1, node2)]
                else:
                    distance = graph[(node2, node1)]

                if distance <= min_distance:
                    min_distance = distance

        max_spacing.append(min_distance)

print(min(max_spacing))
