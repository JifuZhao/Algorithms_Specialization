#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/22/2016"
"""

import heapq

# load the data
with open('./Median.txt') as f:
    lines = f.readlines()

stream = [int(i) for i in lines]
length = len(stream)

high = []
low = []

x = stream[0]
y = stream[1]
median = [x]

# carefully deal with the first two elements
if x <= y:
    heapq.heappush(low, -x)
    heapq.heappush(high, y)
    median.append(x)
else:
    heapq.heappush(low, -y)
    heapq.heappush(high, x)
    median.append(y)

# loop through the remaining inputs
for num in range(2, len(stream)):
    x = -heapq.heappop(low)
    y = heapq.heappop(high)
    z = stream[num]
    if z < x:
        temp_min = z
        temp_median = x
        temp_max = y
    elif z > y:
        temp_min = x
        temp_median = y
        temp_max = z
    else:
        temp_min = x
        temp_median = z
        temp_max = y

    if (num + 1) % 2 == 0:
        heapq.heappush(low, -temp_min)
        heapq.heappush(high, temp_max)
        heapq.heappush(high, temp_median)
        median.append(-low[0])
    else:
        heapq.heappush(low, -temp_min)
        heapq.heappush(low, -temp_median)
        heapq.heappush(high, temp_max)
        median.append(temp_median)

print(len(median), sum(median) % 10000)
