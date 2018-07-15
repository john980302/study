#include <stdio.h>
#include <string.h>

int main(void)
{
	char word[1000001];
	int cnt = 0,i=0,size;
	fgets(word, sizeof(word)+1, stdin);
	size = strlen(word);
	while (i != size+1) {

		if (word[i] == ' '|| word[i] == '\0')
			cnt++;
		i++;
		
	}
	if (word[0] == ' ')
		cnt--;
	if (word[size - 2] == ' ')
		cnt--;
	printf("%d\n", cnt);
	
	return 0;
}