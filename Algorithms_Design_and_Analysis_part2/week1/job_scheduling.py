#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/23/2016"
"""

# load the data
with open('./jobs.txt') as f:
    sequence = f.readlines()

# pre-process the data
weight = []
length = []
num_jobs = int(sequence[0])
for item in sequence[1:]:
    s = item.split()
    weight.append(int(s[0]))
    length.append(int(s[1]))

# method 1, using the difference of weight and length
diff_list = []
for (w, l) in zip(weight, length):
    diff_list.append((w, l, w - l))

diff_list.sort(key=lambda x: x[0], reverse=True)  # sort list by weight
diff_list.sort(key=lambda x: x[2], reverse=True)  # sort list by weight-length

completion_time = 0
weighted_time = 0
for item in diff_list:
    completion_time += item[1]
    weighted_time += item[0] * completion_time

print('weight - length: \t', weighted_time)

# method 2, using the ratio of weight and length
ratio_list = []
for (w, l) in zip(weight, length):
    ratio_list.append((w, l, w / l))

ratio_list.sort(key=lambda x: x[2], reverse=True)  # sort list by weight/length

completion_time = 0
weighted_time = 0
for item in ratio_list:
    completion_time += item[1]
    weighted_time += item[0] * completion_time

print('weight / length: \t', weighted_time)
