import numpy as np

def mean_squared_error(y, t):
      return 0.5 * np.sum((y-t)**2)
t = [0, 0, 1, 0, 0, 0, 0, 0, 0, 0]
y1= [0.1, 0.05, 0.6, 0.0, 0.05, 0.1, 0.0, 0.1, 0.0, 0.0]
a1 = mean_squared_error(np.array(y1),np.array(t))
print(a1)
y2 = [0.1, 0.05, 0.1, 0.0, 0.05, 0.1, 0.0, 0.6, 0.0, 0.0]
a2 = mean_squared_error(np.array(y2),np.array(t))
print(a2)
