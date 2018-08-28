#include <stdio.h>

int main()
{
	int a = 1, b = 1 , num;     // a/b
	scanf("%d", &num);
	for (int i = 1; i < num; i++) {
		if ((a + b) % 2 == 0) {     // Â¦¼ö
			if (a == 1) {
				b++;
			}
			else {
				a--;
				b++;
			}
		}
		else {                      // È¦¼ö
			if (b == 1)
				a++;
			else {
				b--;
				a++;
			}
		}
	}

	printf("%d/%d\n", a, b);

	return 0;
}