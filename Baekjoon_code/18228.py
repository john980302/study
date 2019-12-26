# 입력 부분
N = int(input())

# 얼음을 깨뜨리는 힘
A = list(map(int, input().split()))


#print(N)
#for i in range(N):
#    print(A[i])

Lmin = A[0]
Rmin = A[N-1]
i = 0
while A[i] != -1:
    if Lmin > A[i]:
        Lmin = A[i]
    i += 1
for k in range(i+1,N):
    if Rmin > A[k]:
        Rmin = A[k]

print(Lmin + Rmin)
