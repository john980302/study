#include <stdio.h>
int reverse(int num) {
	int h, s, o, result;
	h = num / 100;
	s = num % 100 / 10;
	o = num % 10;
	result = 100 * o + s * 10 + h;
	return result;
}
int main(void)
{
	int num1, num2;
	scanf("%d %d", &num1, &num2);
	num1 = reverse(num1);
	num2 = reverse(num2);
	if (num1 > num2)
		printf("%d\n", num1);
	else
		printf("%d\n", num2);
	return 0;
}