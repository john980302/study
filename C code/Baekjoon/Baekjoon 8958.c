#include <stdio.h>

int oxscore(char arr[]);

int main(void)
{
	int num = 0, score = 0;      // �׽�Ʈ���̽� ����, ����
	char testcase_buffer[81];  // �Է¹��� ���� O/X
	scanf("%d", &num);
	getchar();
	for (int i = 0; i < num; i++) {
		fgets(testcase_buffer, sizeof(testcase_buffer), stdin);
		score = oxscore(testcase_buffer);
		printf("%d\n", score);
	}
	return 0;
}

int oxscore(char arr[]) {
	int total = 0, score = 1, i = 0;
	while (arr[i] != 0) {
		if (arr[i] == 'O')
			total += score++;
		else
			score = 1;
		i++;
	}
	return total;
}