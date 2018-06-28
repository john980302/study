#include <stdio.h>

int han_su(int n)
{
	int f, s, t;  //first, second, third
	if (n < 100)
		return 1;
	else
	{
		f = n / 100;
		s = (n / 10) % 10;
		t = n % 10;
		if ((f - s) == (s - t))
			return 1;
		else
			return 0;
	}
}


int main(void) {
	int n,cnt=0;     // 입력받는 숫자,count
	scanf("%d", &n);
	for (int i = 1; i <= n; i++) {
		if (han_su(i) == 1)
			cnt++;
	}
	printf("%d\n", cnt);
	return 0;
}