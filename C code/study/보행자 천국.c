#include <stdio.h>

void right(int *r, int *c) {
	(*c)++;
}
void down(int *r, int *c) {
	(*r)++;
}
int main(void)
{
	int city_map[3][6] = { {0,2,0,0,0,2},{0,0,2,0,1,0},{1,0,0,2,2,0} };
	int row = 0, col = 0;
	while (row != 2 || col != 5) {
		switch (city_map[row][col])
		{
		case 0:

			break;
		case 1:
			break;

		}
	}

	return 0;
}