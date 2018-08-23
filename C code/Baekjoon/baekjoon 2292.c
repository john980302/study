#include <stdio.h>

int main()
{
	double input,ans;
	int exit = 0, start=2, end=7, i=2,interval;
	scanf("%lf", &input);
	if (input == 1) {
		exit = 1;
		ans = 1;
	}
		
	while (exit != 1) {
		if (input >= start && input <= end) {
			exit = 1;
			ans = i;
		}
		else {
			interval = end - start + 6;
			start = end + 1;
			end = start + interval;
			i++;

		}
		
	}
	printf("%.lf\n", ans);
	return 0;
}