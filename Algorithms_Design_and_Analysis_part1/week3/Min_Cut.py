#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/16/2016"
"""

import copy
import random

# load the data
List = []
with open('./kargerMinCut.txt') as f:
    for item in f.readlines():
        List.append(list(map(int, item.split())))

def merge(sequence, u, v):
    sequence[u] += sequence[v][1:]  # merge v into u
    vertex_1 = sequence[u][0]
    vertex_2 = sequence[v][0]
    for i in range(len(sequence)):
        # change all vertex_2 into vertex_1 in the sequence[i]
        sequence[i][1:] = list(map(lambda x: vertex_1 if x == vertex_2 else x, sequence[i][1:]))
    sequence[u][1:] = [x for x in sequence[u][1:] if x != vertex_1]  # remove self-loops
    del sequence[v]  # remove item v

def random_contraction(adjacency_list, seed):
    sequence = copy.deepcopy(adjacency_list)
    random.seed(seed)  # generate random seed
    length = len(sequence)
    while length > 2:
        # u, v = random.sample(range(length), 2)  # wrong method, (u, v) may not be meaningful edge
        u = random.choice(range(length))
        vertex = random.choice(sequence[u][1:])  # make sure there exists the edge (u, v)
        for i in range(length):
            if sequence[i][0] == vertex:
                v = i
                break
        merge(sequence, u, v)
        length = len(sequence)

    return len(sequence[0]) - 1

def main():
    result = 100000
    for i in range(200 * 200):
        seed = i  # set seed to different numbers
        num = random_contraction(List, seed)
        result = min(result, num)
        print(result)

if __name__ == '__main__':
    main()
