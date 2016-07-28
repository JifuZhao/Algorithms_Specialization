#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/27/2016"
"""
import time
import sys
import threading

t0 = time.time()

""" The input file is too large, cannot use the method in knapsack.py
    Using recursive method and caching the calculated result in hash table for quickly lookup

    However, there are some problems with the maximum recursion depth
    problem with Python, refer to online resources to solve this problem
"""

# load the data
with open('./knapsack_big.txt') as f:
    lines = f.readlines()

size, num_item = map(int, lines[0].split())
print('Size: ', size, '\t Number of items: ', num_item)

values = []
weights = []
for item in lines[1:]:
    v, w = map(int, item.split())
    values.append(v)
    weights.append(w)

# initialize a hash table
calculated = dict()
for x in range(size + 1):
    calculated[(0, x)] = 0

# recursively solve the sub-problems
def find_A(i, x):
    global calculated
    global values
    global weights

    if (i, x) in calculated:
        return calculated[(i, x)]
    else:
        A1 = find_A(i - 1, x)
        w = weights[i - 1]
        if w > x:
            calculated[(i, x)] = A1
            return A1
        else:
            v = values[i - 1]
            A2 = find_A(i - 1, x - w) + v
            A = max(A1, A2)
            calculated[(i, x)] = A
            return A

def main():
    print('The optimal solution: ', find_A(num_item, size))
    print('Running time: ', time.time() - t0)

if __name__ == '__main__':
    threading.stack_size(67108864)
    sys.setrecursionlimit(2 ** 20)
    thread = threading.Thread(target=main)
    thread.start()
