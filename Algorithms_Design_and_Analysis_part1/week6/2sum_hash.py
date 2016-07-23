#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/22/2016"
"""
import time

t0 = time.time()
# load the data
with open('./2sum.txt') as f:
    lines = f.readlines()

# transform the input into integers
num = [int(i) for i in lines]
length = len(num)

# create hash tables HT
HT = dict((key, value) for (key, value) in zip(num, range(1, length + 1)))

i = 0
count = 0
keys = HT.keys()
for value in range(-10000, 10000 + 1):
    i += 1
    if i % 1000 == 0:
        print(i, time.time() - t0)
    for x in keys:
        y = value - x
        if (y in HT) and (y != x):
            count += 1
            break

print(count)
print('Time: ', time.time() - t0)
