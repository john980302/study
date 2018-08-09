#include <stdio.h>

int main()
{
	int final_score[5] = { 0 },hap = 0, avg;
	for (int i = 0; i < 5; i++) {
		scanf("%d", &final_score[i]);
		if (final_score[i] < 40)
			final_score[i] = 40;
		hap += final_score[i];
	}
	avg = hap / 5;
	printf("%d\n", avg);

	return 0;
}