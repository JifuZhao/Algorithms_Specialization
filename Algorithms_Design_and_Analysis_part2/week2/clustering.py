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
graph_dict = dict()
for item in lines[1:]:
    u, v, cost = map(int, item.split())
    graph_dict[(u, v)] = cost

# using array to sort the costs
graph = []
for item in lines[1:]:
    graph.append(list(map(int, item.split())))

graph.sort(key=lambda x: x[2])

# initialize the clusters
clusters = dict()
nodes = dict()
for node in range(1, num_nodes + 1):
    clusters[node] = [node]  # which nodes does cluster i contains
    nodes[node] = node  # which cluster does node i belongs to

T = []
num_clusters = num_nodes
i = 0
while num_clusters > 4:
    u = graph[i][0]  # the first node
    v = graph[i][1]  # the second node
    u_cluster = nodes[u]
    v_cluster = nodes[v]
    if u_cluster != v_cluster:
        clusters[u_cluster] += clusters[v_cluster]
        for node in clusters[v_cluster]:
            nodes[node] = u_cluster
        del clusters[v_cluster]

    i += 1
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
                    distance = graph_dict[(node1, node2)]
                else:
                    distance = graph_dict[(node2, node1)]

                if distance <= min_distance:
                    min_distance = distance

        max_spacing.append(min_distance)

print(min(max_spacing))
