#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/14/2016"
"""

# read the data from local file
with open('./QuickSort.txt') as f:
    sequence = f.readlines()
sequence = [int(num) for num in sequence]  # change string into integers

# copy arrays to avoid changing the original sequence
array_first = sequence[:]
array_last = sequence[:]
array_median = sequence[:]

# choosing the first element as pivot
def partition_first(array, l, r):
    p = array[l]  # set the pivot
    i = l + 1
    
    for j in range(l + 1, r + 1):
        if array[j] < p:
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
            i += 1

    array[l] = array[i - 1]
    array[i - 1] = p
    return i - 1

# choosing the last element as pivot
def partition_last(array, l, r):
    temp = array[l]  # exchange the left and right element
    array[l] = array[r]
    array[r] = temp

    p = array[l]  # set the pivot
    i = l + 1
    for j in range(l + 1, r + 1):
        if array[j] < p:
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
            i += 1

    array[l] = array[i - 1]
    array[i - 1] = p
    return i - 1

# choosing the median element as pivot
def partition_median(array, l, r):
    left = array[l]
    right = array[r]
    diff = (r - l) // 2
    median = array[l + diff]

    if (median - left) * (median - right) < 0:  # median is the median
        temp = array[l + diff]  # exchange the left and median element
        array[l + diff] = array[l]
        array[l] = temp
    elif (right - median) * (right - left) < 0:  # right is the median
        temp = array[r]  # exchange the left and right element
        array[r] = array[l]
        array[l] = temp

    p = array[l]  # set the pivot
    i = l + 1
    for j in range(l + 1, r + 1):
        if array[j] < p:
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
            i += 1

    array[l] = array[i - 1]
    array[i - 1] = p
    return i - 1

def sort(array, start, end, partition):
    global num
    # only calculate when the start point is less than end point
    if start < end:
        num += end - start
        split = partition(array, start, end)
        # recursive sort the left and right part
        sort(array, start, split - 1, partition)
        sort(array, split + 1, end, partition)

num = 0
sort(array_first, 0, 9999, partition_first)
print('First element: \t', num)
# print(array_first[:100])

num = 0
sort(array_last, 0, 9999, partition_last)
print('Last element: \t', num)
# print(array_last[:100])

num = 0
sort(array_median, 0, 9999, partition_median)
print('Median element: \t', num)
# print(array_median[:100])
