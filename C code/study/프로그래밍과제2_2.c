#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

// ���� ����
int gwanak[3][6] = { { 70, 45, 100, 92, 150, 81 }
                    ,{ 46, 50, 30, 122, 123, 82 }
                    ,{95, 98, 40, 140, 54, 102} };
int gangnam[][6] = { { 88, 92, 77, 30, 52, 55 } 
                    ,{ 147, 124, 75, 84, 76, 116 }
                    ,{ 62, 110, 97, 96, 108, 136 } };
int myeongdong[][6] = { { 50, 90, 88, 75, 77, 49 }
                       ,{ 68, 90, 81, 30, 108, 86 }
                       ,{ 104, 53, 100, 41, 139, 106 } };
int daelim[][6] = { { 120, 92, 80, 150, 130, 105}
                   ,{ 118, 140, 43, 128, 143, 46 }
                   ,{ 32, 104, 62, 139, 54, 66 } };
int data2018[][6] = { gwanak[0],gangnam[0],myeongdong[0],daelim[0] };
int data2017[][6] = { gwanak[1],gangnam[1],myeongdong[1],daelim[1] };
int data2016[][6] = { gwanak[2],gangnam[2],myeongdong[2],daelim[2] };
int data[3][4][6] = { data2018,data2017,data2016 };
char branch[][10] = { "������","������","����","�븲��" };
double avg[3][4] = { 0 };

// �ƹ�Ű�� ������ ���θ޴���
void press_any_key(void)
{
	printf("\n\n");
	printf("�ƹ�Ű�� ������ ���� �޴���...");
	getch();
}

// ���� �޴�
int menu_display()
{
	int select;
	system("cls");
	printf("   --< �ǶǺн� ��ݱ� ���� ��� >--- \n");
	printf(" 1. 2018�� ������ ���\n");
	printf(" 2. 2018�� ������ ���\n");
	printf(" 3. 3�Ⱓ ��� ������ ���\n");
	printf(" 4. ������ ���� ��ȸ\n");
	printf(" 5. ����\n");
	printf(" �޴���ȣ �Է� : ");
	select = getch() - '0';
	return select;
}

// 2018�� ������ ���
void performance_2018()
{
	system("cls");
	// ���� ����
	double dtemp = 0 ;
	char ctemp[10] = { 0 };
	int itemp[6] = { 0 };

	// ��� ���ϱ�
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 6; j++)
			avg[0][i] += *(data2018[i]+j);
		avg[0][i] = round(avg[0][i] / 6);
	}

	// ������ ����
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (avg[0][i] > avg[0][j])
			{
				// ��� ����
				dtemp = avg[0][i];
				avg[0][i] = avg[0][j];
				avg[0][j] = dtemp;

				// �����Ͱ� ����
				strcpy(itemp,data2018[i]);
				strcpy(data2018[i],data2018[j]);
				strcpy(data2018[j],itemp);

				// ������ ����
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}

	// ������ ���
	printf("2018�� ������ ���...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", *((data2018[i])+j));
		}
		printf("%5.lf \n", avg[0][i]);
	}
	press_any_key();
}

// 2018�� ������ ���
void branch_2018()
{
	system("cls");
	// ���� ����
	double dtemp = 0;
	char ctemp[10] = { 0 };
	int itemp[6] = { 0 };

	// ��� ���ϱ�
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 6; j++)
			avg[0][i] += data2018[i][j];
		avg[0][i] = round(avg[0][i] / 6);
	}

	// ������ ����
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (strcmp(branch[i], branch[j]) < 0)
			{
				// ��� ����
				dtemp = avg[0][i];
				avg[0][i] = avg[0][j];
				avg[0][j] = dtemp;

				// �����Ͱ� ����
				strcpy(itemp, data2018[i]);
				strcpy(data2018[i], data2018[j]);
				strcpy(data2018[j], itemp);

				// ������ ����
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}

	// ������ ���
	printf("2018�� ������ ���...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", data[0][i][j]);
		}
		printf("%5.lf \n", avg[0][i]);
	}
	press_any_key();
}

// 3�Ⱓ ��� ������ ���
void avg_per_3()
{
	system("cls");
	// ��� ���ϱ�
	for (int k = 0; k < 3; k++) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++)
				avg[k][i] += data[k][i][j];
			avg[k][i] = round(avg[k][i] / 6);
		}
	}

	// 3�Ⱓ ��� ������ ���

	press_any_key();
}

// ���� �޴�
void submenu_display(void)
{
	system("cls");
	press_any_key();
}


// ���� �Լ�
int main(void)
{
	int num;

	while ((num = menu_display()) != 5)
	{
		switch (num)
		{
		case 1:
			performance_2018();
			break;
		case 2:
			branch_2018();
			break;
		case 3:
			avg_per_3();
			break;
		case 4:
			submenu_display();
			break;
		default:
			break;
		}
	}

}