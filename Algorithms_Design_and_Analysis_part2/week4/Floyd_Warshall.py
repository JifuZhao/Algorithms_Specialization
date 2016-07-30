#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/29/2016"
"""
import time
t0 = time.time()

def read_graph(fname):
    """ read the local data and save to a dictionary"""
    with open(fname) as f:
        lines = f.readlines()

    num_vertices, num_edges = map(int, lines[0].split())

    graph = dict()
    for item in lines[1:]:
        u, v, l = map(int, item.split())
        graph[(u, v)] = l

    return num_vertices, num_edges, graph

def Floyd_Warshall(num_vertices, graph):
    """ Implement Floyd-Warshall algorithm to compute
        the all-pair shortest-path
    """
    infinity = 100000

    # initialize the 3D array A
    A = [[[0 for k in range(num_vertices + 1)]
          for j in range(num_vertices)] for i in range(num_vertices)]
    print('Create A, done !')

    for i in range(1, num_vertices + 1):
        for j in range(1, num_vertices + 1):
            if i == j:
                A[i - 1][j - 1][0] = 0
            elif (i, j) in graph:
                A[i - 1][j - 1][0] = graph[(i, j)]
            else:
                A[i - 1][j - 1][0] = infinity
    print('Initializing A, done !')
    del graph  # delete graph to save memory

    # loop through k, i, j to find the shortest path
    for k in range(1, num_vertices + 1):
        if k % 50 == 0:
            print('Current k is: ', k)
        for i in range(num_vertices):
            for j in range(num_vertices):
                case1 = A[i][j][k - 1]
                case2 = A[i][k - 1][k - 1] + A[k - 1][j][k - 1]
                if case1 < case2:
                    A[i][j][k] = case1
                else:
                    A[i][j][k] = case2

    for i in range(num_vertices):
        if A[i][i][num_vertices] < 0:
            return "Negative cycle found"
    print('Pass negative cycle check !')

    minimum = infinity
    for i in range(num_vertices):
        for j in range(num_vertices):
            if A[i][j][num_vertices] < minimum:
                minimum = A[i][j][num_vertices]

    return minimum

# test module
# print('Test 1')
# num_vertices, num_edges, graph = read_graph('./test1.txt')
# print('Result: ', Floyd_Warshall(num_vertices, graph))
# print('\nTest 2')
# num_vertices, num_edges, graph = read_graph('./test2.txt')
# print('Result: ', Floyd_Warshall(num_vertices, graph))
# print('\nTest 3')
# num_vertices, num_edges, graph = read_graph('./test3.txt')
# print('Result: ', Floyd_Warshall(num_vertices, graph))
# print('\nTest 4')
# num_vertices, num_edges, graph = read_graph('./test4.txt')
# print('Result: ', Floyd_Warshall(num_vertices, graph))

# Assignment
print('Begin g1.txt ' + '.' * 10)
num_vertices1, num_edges1, graph1 = read_graph('./g1.txt')
print(Floyd_Warshall(num_vertices1, graph1))
print('Time: ', time.time() - t0)

# print('\n Begin g2.txt ' + '.' * 10)
# num_vertices2, num_edges2, graph2 = read_graph('./g2.txt')
# print(Floyd_Warshall(num_vertices2, graph2))
# print('Time: ', time.time() - t0)

# print('\n Begin g3.txt ' + '.' * 10)
# num_vertices3, num_edges3, graph3 = read_graph('./g3.txt')
# print(Floyd_Warshall(num_vertices3, graph3))
# print('Time: ', time.time() - t0)
