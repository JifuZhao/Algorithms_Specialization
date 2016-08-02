#!/usr/bin/env python3

# -*- coding: utf-8 -*-
"""
__author__      = "Jifu Zhao"
__email__       = "jzhao59@illinois.edu"
__date__        = "07/31/2016"
"""

import numpy as np
import time
import copy
import matplotlib.pyplot as plt

t0 = time.time()

# load the data
with open('./tsp.txt') as f:
    lines = f.readlines()

num_city = int(lines[0])

X = []
Y = []
for item in lines[1:]:
    x, y = map(float, item.split())
    X.append(x)
    Y.append(y)

plt.figure()
plt.plot(X, Y, 'o')
plt.show()
