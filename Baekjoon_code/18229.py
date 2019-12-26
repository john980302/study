#  N 사람, M 반복 횟수, K 점원과 사람간의 거리
N, M, K = map(int, input().split())

# A 행렬 초기화
A = [0 for i in range(N)]

# 점원과 사람간의 인접행렬
for i in range(N):
    A[i] = list(map(int, input().split()))

# 사람들이 점원을 향해 뻗는 손의 거리
NK = [int(0) for i in range(N)]

# 계산
j = 0
i = 0
while True:
    NK[j] += A[j][i]
    if NK[j] >= K:
        break
    j += 1
    if j > N-1:
        i += 1
        j = 0

# 출력
print(j+1, i+1)