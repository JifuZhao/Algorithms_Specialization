#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/12/2016"
"""

num_inversion = 0

def merge(left, right):
    global num_inversion
    len_left = len(left)
    len_right = len(right)
    i = 0
    j = 0
    merged = []
    while (i < len_left and j < len_right):
        if left[i] <= right[j]:
            merged.append(left[i])
            i = i + 1
        else:
            merged.append(right[j])
            j = j + 1
            num_inversion = num_inversion + len_left - i
    merged = merged + left[i:]
    merged = merged + right[j:]
    return merged

def sort(data):
    length = len(data)
    if length == 1:
        return data
    else:
        n = length // 2
        return merge(sort(data[:n]), sort(data[n:]))

with open('./IntegerArray.txt') as f:
    integers = f.readlines()

integers = [int(integer) for integer in integers]
merged_sort = sort(integers)

print(num_inversion)
