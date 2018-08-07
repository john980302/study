#include <stdio.h>
#include <math.h>

int main()
{
	int n = 0, k = 0, nums[501] = { 0 }, hap = 0; // 0<n<501 , 0<k<n+1
	double m = 0, answer = 99, var = 0, std = 0 , h = 0;
	scanf("%d %d", &n, &k);
	for (int i = 0; i < n; i++)
		scanf("%d", &nums[i]);
	while (n >= k)
	{
		int j = 0;
		while (j <= n - k)
		{
			for (int i = 0; i < k; i++)
				hap += nums[j + i];
			m = (double)hap / k;
			hap = 0;
			h = 0;
			for (int i = 0; i < k; i++)
				h += (nums[j + i] - m) * (nums[j + i] - m);
			var = h / k;
			std = sqrt(var);
			if (answer > std)
				answer = std;
			j++;
		}
		k++;
	}
	
	printf("%.12lf\n", answer);


	return 0;
}