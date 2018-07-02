import numpy as np
import matplotlib.pylab as plt

def numerical_gradient(f, x):
    h = 1e-4
    grad = np.zeros_like(x)  # x와 형상이 같은 배열을 생성

    for idx in range(x.size):
        tmp_val = x[idx]
        # f(x+h) 계산
        x[idx] = tmp_val + h
        fxh1 = f(x)

        # f(x-h) 계산
        x[idx] = tmp_val - h
        fxh2 = f(x)

        grad[idx] = (fxh1 - fxh2) / (2 * h)
        x[idx] = tmp_val # 값 복원

    return grad

def function_2(x):
    return x[0]**2 + x[1]**2

a1 = numerical_gradient(function_2,np.array([3.0, 4.0]))
print(a1)
a2 = numerical_gradient(function_2,np.array([0.0, 2.0]))
print(a2)
a3 = numerical_gradient(function_2,np.array([3.0, 0.0]))
print(a3)