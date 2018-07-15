#include <stdio.h>

void printstar(char **star, int x, int y,int n);
int main(void)
{
	int x,y,n;
	char **star;
	scanf("%d", &n);
	y = n;
	x = 2 * n - 1;
	star = (char **)malloc(sizeof(char *)*y);
	for (int i = 0; i < n; i++)
		star[i] = (char *)malloc(sizeof(char)*x);
	printstar(star, 0, 0, n);
	for (int i = 0; i < y; i++) {
		for (int k = 0; k < x; k++) {
			if (star[i][k] == '*')
				printf("%c", star[i][k]);
			else
				printf("%c", 32);
		}
		printf("\n");
	}

	return 0;
}
void printstar(char **star, int x, int y, int n) {
	if (n == 3) {
		star[y][x + 2] = '*';
		star[y + 1][x + 1] = '*';
		star[y + 1][x + 3] = '*';
		for (int i = 0; i < 5; i++)
			star[y + 2][x + i] = '*';
		return;
	}
	printstar(star, x + n / 2, y, n / 2);
	printstar(star, x, y + n / 2, n / 2);
	printstar(star, x + n, y + n / 2, n / 2);


}