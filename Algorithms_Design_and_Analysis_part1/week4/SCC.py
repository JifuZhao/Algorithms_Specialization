#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/17/2016"
"""

import sys
import threading

def load_data(name):
    # use dictinary to store the data
    graph = dict()
    graph_rev = dict()

    with open(name) as f:
        line = f.readline()
        while line:
            tail, head = list(map(int, line.split()))
            # normal sequence
            if tail in graph:
                graph[tail].append(head)
            else:
                graph[tail] = [head]

            if head not in graph:
                graph[head] = []

            # reversed sequence
            if head in graph_rev:
                graph_rev[head].append(tail)
            else:
                graph_rev[head] = [tail]

            if tail not in graph_rev:
                graph_rev[tail] = []

            line = f.readline()  # continue to read next line
    return graph, graph_rev

# define global variables
t = 0
explored = None
f_t = None
scc = []
scc_count = 0

# DFS loop for reversed graph
def DFS_loop(G):
    global t
    global explored
    global f_t

    n = len(G.keys())
    t = 0
    s = None
    explored = [0] * n
    f_t = dict()

    for i in range(n, 0, -1):
        if explored[i - 1] == 0:
            DFS(G, i)

    return f_t

# DFS for reversed graph
def DFS(G, i):
    global t
    global f_t
    global explored

    explored[i - 1] = 1
    for j in G[i]:
        if explored[j - 1] == 0:
            DFS(G, j)
    t += 1
    f_t[t] = i

# DFS loop for normal graph
def DFS_loop_2(G):
    global explored
    global scc
    global scc_count
    global f_t

    n = len(G.keys())
    s = None
    explored = [0] * n
    scc = []

    for i in range(n, 0, -1):
        if explored[f_t[i] - 1] == 0:  # use f_t[i] rather than i
            scc_count = 0
            DFS_2(G, f_t[i])  # use f_t[i] rather than i
            scc.append(scc_count)
    return scc

# DFS for normal graph
def DFS_2(G, i):
    global f_t
    global explored
    global scc_count

    explored[i - 1] = 1
    scc_count += 1
    for j in G[i]:
        if explored[j - 1] == 0:
            DFS_2(G, j)

def main():
    # using small files to test the result
    graph, graph_rev = load_data('test1.txt')
    f_t = DFS_loop(graph_rev)
    scc = DFS_loop_2(graph)
    print('Test 1')
    print('My answer:\t', sorted(scc, reverse=True) + [0] * (5 - len(scc)))
    print('Correct answer: 3, 3, 3, 0, 0\n')

    graph, graph_rev = load_data('test2.txt')
    f_t = DFS_loop(graph_rev)
    scc = DFS_loop_2(graph)
    print('Test 2')
    print('My answer:\t', sorted(scc, reverse=True) + [0] * (5 - len(scc)))
    print('Correct answer: 3, 3, 2, 0, 0\n')

    graph, graph_rev = load_data('test3.txt')
    f_t = DFS_loop(graph_rev)
    scc = DFS_loop_2(graph)
    print('Test 3')
    print('My answer:\t', sorted(scc, reverse=True) + [0] * (5 - len(scc)))
    print('Correct answer: 3, 3, 1, 1, 0\n')

    graph, graph_rev = load_data('test4.txt')
    f_t = DFS_loop(graph_rev)
    scc = DFS_loop_2(graph)
    print('Test 4')
    print('My answer:\t', sorted(scc, reverse=True) + [0] * (5 - len(scc)))
    print('Correct answer: 7, 1, 0, 0, 0\n')

    # final answer
    graph, graph_rev = load_data('SCC.txt')
    f_t = DFS_loop(graph_rev)
    scc = DFS_loop_2(graph)
    print('*' * 40)
    print('Final Answer')
    print('My answer:\t', sorted(scc, reverse=True)[:5])

if __name__ == '__main__':
    threading.stack_size(67108864)
    sys.setrecursionlimit(2 ** 20)
    thread = threading.Thread(target=main)
    thread.start()
