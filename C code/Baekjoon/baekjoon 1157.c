#include <stdio.h>

int main()
{
	char str[1000001];
	int std[26] = { 0 };
	scanf("%s", &str);  // if 91���� ������ 65�� ���� ũ�� 97�� ����
	for (int i = 0; str[i]; i++) {
		if (str[i] < 91)
			std[str[i] - 65]++;
		else
			std[str[i] - 97]++;
	}
	// �ִ� ���ϱ�
	int max_num = 0;
	for (int i = 0; i < 26; i++)
		if (std[i] > max_num)
			max_num = std[i];
	// �ִ��� ������ �ε����� ��������
	int cnt = 0, index;
	for (int i = 0; i < 26; i++) {
		if (max_num == std[i]) {
			cnt++;
			index = i;
		}
	}

	// ���
	if (cnt > 1)
		printf("?\n");
	else if (cnt == 1)
		printf("%c\n", (index + 65));

	
	return 0;
}