#include <stdio.h>
int d(int n)
{
	int res[5] = {n / 10000, (n / 1000)%10,(n/100)%10,(n/10)%10,n%10}, sum = n;
	for (int i = 0; i < 5; i++)
		sum += res[i];
	return sum;
}
int main(void) {
	int cnt;
	for (int i = 1; i <= 10000; i++) {
		cnt = 0;
		for (int j = 1; j <= 10000; j++) {
			if (i == d(j))
				cnt++;
		}
		if (cnt == 0)
			printf("%d\n", i);
	}
	return 0;
}