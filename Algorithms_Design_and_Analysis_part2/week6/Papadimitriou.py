#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "08/01/2016"
"""
import numpy as np
import time

# load the file
def load_data(fname):
    with open(fname) as f:
        lines = f.readlines()

    num = int(lines[0])
    clauses = []
    for item in lines[1:]:
        u, v = map(int, item.split())
        clauses.append((u, v))

    return num, clauses

# function to initialize the random initial assignment
def initialize(num):
    return list(np.random.choice([1, -1], num))


# def Papadimitriou's 2-SAT algorithm
def papadimitriou(fname):
    num, clauses = load_data(fname)
    # inner_loop = 2 * num**2
    inner_loop = 2 * num
    # print('Total Outer loop: ', int(np.log2(num)))
    # print('Total Inner loop: ', inner_loop, '\n')
    visited = []
    # Papadimitriou's 2-SAT algorithm
    for i in range(1, int(np.log2(num)) + 1):
        print('Outer: ', i)
        assignment = initialize(num)
        while assignment in visited:
            assignment = initialize(num)

        visited.append(assignment[:])
        for j in range(1, inner_loop + 1):
            if j % 1000 == 0:
                print('**** Inner: ', j)
            satisfied = []
            for k in range(num):
                u = clauses[k][0]
                v = clauses[k][1]
                if u * assignment[abs(u) - 1] * v * assignment[abs(v) - 1] > 0:
                    satisfied.append(1)
                else:
                    satisfied.append(0)
            # print(sum(satisfied), num)
            if sum(satisfied) == num:
                return 'Satisfied'
            else:
                unsatisfy_index = [m for m in range(num) if satisfied[m] == 0]
                m = np.random.choice(unsatisfy_index, 1)[0]
                n = np.random.choice([0, 1], 1)[0]
                index = abs(clauses[m][n]) - 1
                value = assignment[index]
                assignment[index] = 1 if value == -1 else -1
            # print(assignment)
    return 'Unsatisfied'

if __name__ == '__main__':
    t0 = time.time()
    print('*' * 25)
    print('Test 1: Satisfiable Case')
    print('\nConclusion:\t', papadimitriou('./2sat_satisfiable.txt'))
    print('Time usage:\t', time.time() - t0, '\n')

    t0 = time.time()
    print('*' * 25)
    print('Test 2: Unsatisfiable Case')
    print('\nConclusion:\t', papadimitriou('./2sat_unsatisfiable.txt'))
    print('Time usage:\t', time.time() - t0, '\n')

    # t0 = time.time()
    # print('*' * 25)
    # print('File 1')
    # print('\nConclusion:\t', papadimitriou('./2sat1.txt'))
    # print('Time usage:\t', time.time() - t0, '\n')

    # t0 = time.time()
    # print('*' * 25)
    # print('File 2')
    # print('\nConclusion:\t', papadimitriou('./2sat2.txt'))
    # print('Time usage:\t', time.time() - t0, '\n')

    # t0 = time.time()
    # print('*' * 25)
    # print('File 3')
    # print('\nConclusion:\t', papadimitriou('./2sat3.txt'))
    # print('Time usage:\t', time.time() - t0, '\n')
    #
    # t0 = time.time()
    # print('*' * 25)
    # print('File 4')
    # print('\nConclusion:\t', papadimitriou('./2sat4.txt'))
    # print('Time usage:\t', time.time() - t0, '\n')
    #
    # t0 = time.time()
    # print('*' * 25)
    # print('File 5')
    # print('\nConclusion:\t', papadimitriou('./2sat5.txt'))
    # print('Time usage:\t', time.time() - t0, '\n')
    #
    # t0 = time.time()
    # print('*' * 25)
    # print('File 6')
    # print('\nConclusion:\t', papadimitriou('./2sat6.txt'))
    # print('Time usage:\t', time.time() - t0, '\n')
