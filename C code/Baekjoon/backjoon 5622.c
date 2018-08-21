#include <stdio.h>

int main()
{
	char input[16];
	int res = 0;
	scanf("%s", &input);
	for (int i = 0; input[i]; i++) {
		if (input[i] == '1')
			res = res + 2;
		else if (input[i] >= 'A' && input[i] <= 'C')
			res = res + 3;
		else if (input[i] >= 'D' && input[i] <= 'F')
			res = res + 4;
		else if (input[i] >= 'G' && input[i] <= 'I')
			res = res + 5;
		else if (input[i] >= 'J' && input[i] <= 'L')
			res = res + 6;
		else if (input[i] >= 'M' && input[i] <= 'O')
			res = res + 7;
		else if (input[i] >= 'P' && input[i] <= 'S')
			res = res + 8;
		else if (input[i] >= 'T' && input[i] <= 'V')
			res = res + 9;
		else if (input[i] >= 'W' && input[i] <= 'Z')
			res = res + 10;
		else if (input[i] == '0')
			res = res + 11;
	}
	printf("%d\n", res);

	return 0;
}