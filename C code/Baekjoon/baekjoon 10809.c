#include <stdio.h>
#include <string.h>

int main()
{
	char s[101] = { 0 }, alpha[26] = { 0 };
	gets(s);
	for (int i = 0; i < 26; i++)
		alpha[i] = -1;
	for (int i = 0; i < strlen(s); i++)
		if (alpha[s[i] - 97] == -1)
			alpha[s[i] - 97] = i;
	for (int i = 0; i < 26; i++)
		printf("%d ", alpha[i]);
	printf("\n");
	return 0;
}