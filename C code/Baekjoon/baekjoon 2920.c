#include <stdio.h>

int main()
{
	int mix[8] = { 0 }, upcount = 0, downcount = 0;
	for (int i = 0; i < 8; i++)
		scanf("%d", &mix[i]);
	for (int i = 0; i < 8; i++) {
		for (int j = i + 1; j < i + 2; j++) {
			if (mix[j] - mix[i] == 1)
				upcount++;
			if (mix[j] - mix[i] == -1)
				downcount++;
		}
	}
	if (upcount == 7)
		printf("ascending\n");
	else if (downcount == 7)
		printf("descending\n");
	else
		printf("mixed\n");
	return 0;
}