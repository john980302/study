#include <stdio.h>

int main()
{
	char str[1000001];
	int std[26] = { 0 };
	scanf("%s", &str);  // if 91보다 작으면 65를 뺴고 크면 97을 빼기
	for (int i = 0; str[i]; i++) {
		if (str[i] < 91)
			std[str[i] - 65]++;
		else
			std[str[i] - 97]++;
	}
	// 최댓값 구하기
	int max_num = 0;
	for (int i = 0; i < 26; i++)
		if (std[i] > max_num)
			max_num = std[i];
	// 최댓값을 저장한 인덱스랑 개수세기
	int cnt = 0, index;
	for (int i = 0; i < 26; i++) {
		if (max_num == std[i]) {
			cnt++;
			index = i;
		}
	}

	// 출력
	if (cnt > 1)
		printf("?\n");
	else if (cnt == 1)
		printf("%c\n", (index + 65));

	
	return 0;
}