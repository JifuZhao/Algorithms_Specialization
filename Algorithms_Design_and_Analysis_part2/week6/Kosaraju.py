#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "08/01/2016"
"""

import sys
import threading
import time

def load_data(name):
    # use dictinary to store the data
    graph = dict()
    graph_rev = dict()

    with open(name) as f:
        lines = f.readlines()
        num = int(lines[0])
        for item in lines[1:]:
            tail, head = list(map(int, item.split()))
            # normal sequence
            if -tail in graph:
                graph[-tail].append(head)
            else:
                graph[-tail] = [head]

            if -head in graph:
                graph[-head].append(tail)
            else:
                graph[-head] = [tail]

            if -tail not in graph:
                graph[-tail] = []
            if -head not in graph:
                graph[-head] = []

            # reversed sequence
            if head in graph_rev:
                graph_rev[head].append(-tail)
            else:
                graph_rev[head] = [-tail]

            if tail in graph_rev:
                graph_rev[tail].append(-head)
            else:
                graph_rev[tail] = [-head]

            if tail not in graph_rev:
                graph_rev[tail] = []
            if head not in graph_rev:
                graph_rev[tail] = []

    return graph, graph_rev, num

# define global variables
t = 0
explored = None
f_t = None
scc = None
scc_count = 0

# DFS loop for reversed graph
def DFS_loop(G, num):
    global t
    global explored
    global f_t

    t = 0
    explored = []
    f_t = dict()

    for key in G.keys():
        if key not in explored:
            DFS(G, key)

    return f_t

# DFS for reversed graph
def DFS(G, i):
    global t
    global f_t
    global explored

    explored.append(i)
    if i in G:
        for j in G[i]:
            if j not in explored:
                DFS(G, j)
    t += 1
    f_t[t] = i

# DFS loop for normal graph
def DFS_loop_2(G, num):
    global explored
    global scc
    global scc_count
    global f_t

    t = 0
    explored = []
    scc = dict()

    for i in range(num, 0, -1):
        if f_t[i] not in explored:  # use f_t[i] rather than i
            scc_count = 0
            DFS_2(G, f_t[i], i)  # use f_t[i] rather than i

    for key in scc.keys():
        l = scc[key]
        for i in l:
            if -i in l:
                return "Not satisfiable"

    return "    Satisfiable"

# DFS for normal graph
def DFS_2(G, i, leader):
    global f_t
    global explored
    global scc_count
    global scc

    explored.append(i)
    scc_count += 1
    if i in G:
        if leader not in scc:
            scc[leader] = [i]
        else:
            scc[leader].append(i)
        for j in G[i]:
            if j not in explored:
                if leader not in scc:
                    scc[leader] = [j]
                else:
                    scc[leader].append(j)
                DFS_2(G, j, leader)

def main():
    file_list = ['test1.txt', 'test2.txt', 'test3.txt', 'test4.txt',
                 'test5.txt', 'test6.txt']
    answer = ['Satisfiable', 'Satisfiable', 'Not satisfiable',
              'Not satisfiable', 'Satisfiable', 'Not satisfiable']
    print('File Name', '\t', 'Program Answer', '\t', 'Correct Answer')
    print('*' * 10, '\t', '*' * 15, '\t', '*' * 15)
    i = 0
    for name in file_list:

        t0 = time.time()
        graph, graph_rev, num = load_data(name)
        f_t = DFS_loop(graph_rev, num)
        scc = DFS_loop_2(graph, len(f_t))
        print(name, '\t', scc, '\t', answer[i])
        i += 1
        # print('Time: ', time.time() - t0, '\n')

if __name__ == '__main__':
    threading.stack_size(67108864)
    sys.setrecursionlimit(2 ** 20)
    thread = threading.Thread(target=main)
    thread.start()
