#include <stdio.h>

int main()
{
	char input[101];
	int res = 0;
	scanf("%s", &input);
	for (int i = 0; input[i]; i++) {

		if (input[i] == 'c') {
			if (input[i + 1] == '=' || input[i + 1] == '-')
				i++;
			res++;
		}
		else if (input[i] == 'd') {
			if (input[i + 1] == 'z' && input[i + 2] == '=')
				i += 2;
			else if (input[i + 1] == '-')
				i++;
			res++;
		}
		else if (input[i] == 'l' || input[i] == 'n') {
			if (input[i + 1] == 'j')
				i++;
			res++;
		}
		else if (input[i] == 's' || input[i] == 'z') {
			if (input[i + 1] == '=')
				i++;
			res++;
		}
		else
			res++;
		
	}

	printf("%d\n", res);


	return 0;
}