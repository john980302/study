#include <stdio.h>
#include <math.h>
#include <string.h>

int main(void)
{
	// ���� ����
	int gwanak[] = { 70, 45, 100, 92, 150, 81 };
	int gangnam[] = { 88, 92, 77, 30, 52, 55 };
	int myeongdong[] = { 50, 90, 88, 75, 77, 49 };
	int daelim[] = { 120, 92, 80, 150, 130, 105 };
	int *data[] = { gwanak,gangnam,myeongdong,daelim };
	char branch[][10] = { "������","������","����","�븲��" };
	double avg[4] = { 0 };
	double dtemp = 0;
	char ctemp[10] = { 0 };
	int itemp = 0;

	// ��� ���ϱ�
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 6; j++)
			avg[i] += *(data[i] + j);
		avg[i] = round(avg[i] / 6);
	}
	// ������ ����
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (avg[i] > avg[j])
			{
				// ��� ����
				dtemp = avg[i];
				avg[i] = avg[j];
				avg[j] = dtemp;

				// �����Ͱ� ����
				itemp = data[i];
				data[i] = data[j];
				data[j] = itemp;
				
				// ������ ����
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}
	// ������ ���
	printf("������ ���...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", *(data[i] + j));
		}
		printf("%5.lf \n", avg[i]);
	}

	// �� �� ������
	printf("\n\n");

	// ������ ����
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (strcmp(branch[i],branch[j]) < 0 )
			{
				// ��� ����
				dtemp = avg[i];
				avg[i] = avg[j];
				avg[j] = dtemp;

				// �����Ͱ� ����
				itemp = data[i];
				data[i] = data[j];
				data[j] = itemp;

				// ������ ����
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}

	// ������ ���
	printf("������ ���...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", *(data[i] + j));
		}
		printf("%5.lf \n", avg[i]);
	}
	return 0;

}