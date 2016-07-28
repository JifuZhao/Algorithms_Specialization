#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/27/2016"
"""

""" simple code for Dynamic Programming of BSTs.

    Using Numpy is much easier
"""

def numpy_BST():
    import numpy as np
    weight = [0.05, 0.4, 0.08, 0.04, 0.1, 0.1, 0.23]

    # A = [[0] * 7] * 7
    A = np.zeros((7, 7))
    n = 7

    for s in range(7):
        for i in range(1, n + 1 - s):
            prob_sum = sum(weight[i - 1:i + s])
            minimum = []
            for r in range(i, i + s + 1):
                if i > r - 1:
                    A1 = 0
                else:
                    A1 = A[r - 1 - 1, i - 1]
                if r + 1 > i + s:
                    A2 = 0
                else:
                    A2 = A[i + s - 1, r + 1 - 1]
                temp = prob_sum + A1 + A2
                minimum.append(temp)

            A[s + i - 1, i - 1] = min(minimum)

    print(A)

def list_BST():
    import numpy as np
    weight = [0.05, 0.4, 0.08, 0.04, 0.1, 0.1, 0.23]

    A = []
    for i in range(7):
        A.append([0, 0, 0, 0, 0, 0, 0])

    n = 7
    for s in range(7):
        for i in range(1, n + 1 - s):
            # if i + s > 7:
            #     continue
            prob_sum = sum(weight[i - 1:i + s])
            minimum = []
            for r in range(i, i + s + 1):
                if i > r - 1:
                    A1 = 0
                else:
                    A1 = A[i - 1][r - 1 - 1]
                if r + 1 > i + s:
                    A2 = 0
                else:
                    A2 = A[r + 1 - 1][i + s - 1]
                temp = prob_sum + A1 + A2
                minimum.append(temp)

            A[i - 1][s + i - 1] = min(minimum)

    for i in range(n):
        temp = []
        for j in range(n):
            temp.append(round(A[j][i], 3))
        print(temp)

numpy_BST()
print('\n')
list_BST()
