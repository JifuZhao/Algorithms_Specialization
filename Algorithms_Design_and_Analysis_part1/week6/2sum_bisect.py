#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/22/2016"
"""
import time
import bisect

t0 = time.time()
# load the data
with open('./2sum.txt') as f:
    lines = f.readlines()

# transform the input into integers
num = sorted([int(i) for i in lines])
length = len(num)

s = set()
values = range(-10000, 10000 + 1)
for x in num:
    left = bisect.bisect_left(num, -10000 - x)
    right = bisect.bisect_right(num, 10000 - x)
    for y in num[left:right]:
        if y != x:
            s.add(x + y)

print(len(s))
print('Time: ', time.time() - t0)
