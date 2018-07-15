#include <stdio.h>
#include <string.h>

int main(void)
{
	int a, b, c,res;
	char r[10];
	int ir[10] = { 0 };

	scanf("%d", &a);
	scanf("%d", &b);
	scanf("%d", &c);
	
	res = a * b * c;
	sprintf(r, "%d", res);

	for (int i = 0; i < strlen(r); i++) {
		ir[r[i] - '0']++;
	}

	for (int i = 0; i < 10; i++)
		printf("%d\n", ir[i]);

	return 0;
}