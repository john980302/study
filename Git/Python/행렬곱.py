import numpy as np


A = np.array([[1,2],[3,4]])
print(A.shape)
B = np.array([[5,6],[7,8]])
print(B.shape)

# A 와 B의 내적
print(np.dot(A,B))

a = np.array([[1,2,3],[4,5,6]])
print(a.shape)
b = np.array([[1,2],[3,4],[5,6]])
print(b.shape)

# a 와 b의 내적
print(np.dot(a,b))

aa = np.array([[1,2],[3,4],[5,6]])
print(aa.shape)
bb = np.array([7,8])
print(bb.shape)

# aa 와 bb의 내적
print(np.dot(aa,bb))
