import numpy as np
def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def identity_function(x):
    return x
# 1층
x = np.array([1.0,0.5])
w1 = np.array([[0.1,0.3,0.5],[0.2,0.4,0.6]])
b1 = np.array([0.1,0.2,0.3])

print(w1.shape) # (2,3)
print(x.shape) # (2,)
print(b1.shape) # (3,)

a1 = np.dot(x,w1) + b1
print(a1)
z1 = sigmoid(a1)
print(z1)

# 2층
w2 = np.array([[0.1,0.4],[0.2,0.5],[0.3,0.6]])
b2 = np.array([0.1,0.2])

print(z1.shape)
print(w2.shape)
print(b2.shape)

a2 = np.dot(z1,w2) + b2
print(a2)
z2 = sigmoid(a2)
print(z2)

# 3층
w3 = np.array([[0.1,0.3],[0.2,0.4]])
b3 = np.array([0.1,0.2])

a3 = np.dot(z2,w3) + b3
print(a3)
y = identity_function(a3)
print(y)
