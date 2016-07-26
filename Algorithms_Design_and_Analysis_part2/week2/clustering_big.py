#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/26/2016"
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

unique_nodes = graph.keys()  # find all nodes with unique values

clusters = dict()  # which nodes does cluster i contains
nodes = dict()  # which cluster does node i belongs to
i = 1
for node in unique_nodes:
    clusters[i] = [node]
    nodes[node] = i
    i += 1

def flip(bit):
    ''' function to flip the bits
    '''
    if bit == 0:
        return 1
    else:
        return 0

def find_similary_keys(key):
    ''' function to find the neighbors within 2 Hamming distance
    '''
    similar_key = []
    length = len(key)

    # find all nodes whose distance from key is 1
    for i in range(length):
        similar_key.append(key[0:i] + (flip(key[i]),) + key[i + 1:])

    # find all nodes whose distance from key is 2
    for i in range(length - 1):
        for j in range(i, length):
            similar_key.append(key[0:i] + (flip(key[i]),) + key[i + 1:j] +
                               (flip(key[j]),) + key[j + 1:])

    return similar_key

j = 0
for node in unique_nodes:
    j += 1
    if j % 1000 == 0:
        print('Now at node:\t', j)

    similar_nodes = find_similary_keys(node)
    cluster1 = nodes[node]

    for neighbor in similar_nodes:
        if neighbor not in nodes:
            continue
        cluster2 = nodes[neighbor]
        if cluster1 == cluster2:
            continue
        if len(clusters[cluster1]) >= len(clusters[cluster2]):
            clusters[cluster1] += clusters[cluster2]
            for temp_node in clusters[cluster2]:
                nodes[temp_node] = cluster1
            del clusters[cluster2]
        else:
            clusters[cluster2] += clusters[cluster1]
            for temp_node in clusters[cluster1]:
                nodes[temp_node] = cluster2
            del clusters[cluster1]
            cluster1 = cluster2

print(len(clusters.keys()))
print(time.time() - t0)
