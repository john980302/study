#include <stdio.h>

int main()
{
	char str[101];
	int num, cnt;
	scanf("%d", &num);
	cnt = num;
	for (int i = 0; i < num; i++) {
		scanf("%s", &str);
		int alpha[26] = { 0 };
		for (int k = 0; str[k]; k++) {
			if (str[k] != str[k + 1]) {
				if (alpha[str[k] - 'a'] == 0) {
					alpha[str[k] - 'a']++;
				}
				else {
					cnt--;
					break;
				}
					
			}
		}
	}
	printf("%d\n", cnt);

	return 0;
}