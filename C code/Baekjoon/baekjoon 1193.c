#include <stdio.h>

int main()
{
	int a = 1, b = 1 , num;     // a/b
	scanf("%d", &num);
	for (int i = 1; i < num; i++) {
		if ((a + b) % 2 == 0) {     // ¦��
			if (a == 1) {
				b++;
			}
			else {
				a--;
				b++;
			}
		}
		else {                      // Ȧ��
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