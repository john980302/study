#include <stdio.h>


int first_festival(int a)
{
	int result = 0;
	if (a == 0)
		result = 0;
	else if (a == 1)
		result = 5000000;
	else if (a <= 3)
		result = 3000000;
	else if (a <= 6)
		result = 2000000;
	else if (a <= 10)
		result = 500000;
	else if (a <= 15)
		result = 300000;
	else if (a <= 21)
		result = 100000;
	else if (a <= 100)
		result = 0;
	

	return result;
}

int second_festival(int b)
{
	int result = 0;
	if (b == 0)
		result = 0;
	else if (b == 1)
		result = 5120000;
	else if (b <= 3)
		result = 2560000;
	else if (b <= 7)
		result = 1280000;
	else if (b <= 15)
		result = 640000;
	else if (b <= 31)
		result = 320000;
	else if (b <= 64)
		result = 0;

	return result;
}


int main()
{
	int num = 0,a = 0,b = 0, answer = 0;   // 가정한 횟수, a등, b등, 정답
	scanf("%d", &num);  

	for (int i = 0; i < num; i++) {
		scanf("%d %d", &a, &b);
		answer = first_festival(a) + second_festival(b);
		printf("%d\n", answer);
	}


	return 0;
}

