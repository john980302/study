#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

// 변수 선언
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
char branch[][10] = { "관악점","강남점","명동점","대림점" };
double avg[3][4] = { 0 };

// 아무키나 누르면 메인메뉴로
void press_any_key(void)
{
	printf("\n\n");
	printf("아무키나 누르면 메인 메뉴로...");
	getch();
}

// 메인 메뉴
int menu_display()
{
	int select;
	system("cls");
	printf("   --< 또또분식 상반기 매출 통계 >--- \n");
	printf(" 1. 2018년 실적별 통계\n");
	printf(" 2. 2018년 지점별 통계\n");
	printf(" 3. 3년간 평균 실적별 통계\n");
	printf(" 4. 지점별 실적 조회\n");
	printf(" 5. 종료\n");
	printf(" 메뉴번호 입력 : ");
	select = getch() - '0';
	return select;
}

// 2018년 실적별 통계
void performance_2018()
{
	system("cls");
	// 변수 선언
	double dtemp = 0 ;
	char ctemp[10] = { 0 };
	int itemp[6] = { 0 };

	// 평균 구하기
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 6; j++)
			avg[0][i] += *(data2018[i]+j);
		avg[0][i] = round(avg[0][i] / 6);
	}

	// 실적별 정렬
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (avg[0][i] > avg[0][j])
			{
				// 평균 정렬
				dtemp = avg[0][i];
				avg[0][i] = avg[0][j];
				avg[0][j] = dtemp;

				// 데이터값 정렬
				strcpy(itemp,data2018[i]);
				strcpy(data2018[i],data2018[j]);
				strcpy(data2018[j],itemp);

				// 지점명 정렬
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}

	// 실적별 출력
	printf("2018년 실적별 출력...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", *((data2018[i])+j));
		}
		printf("%5.lf \n", avg[0][i]);
	}
	press_any_key();
}

// 2018년 지점별 통계
void branch_2018()
{
	system("cls");
	// 변수 선언
	double dtemp = 0;
	char ctemp[10] = { 0 };
	int itemp[6] = { 0 };

	// 평균 구하기
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 6; j++)
			avg[0][i] += data2018[i][j];
		avg[0][i] = round(avg[0][i] / 6);
	}

	// 지점별 정렬
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (strcmp(branch[i], branch[j]) < 0)
			{
				// 평균 정렬
				dtemp = avg[0][i];
				avg[0][i] = avg[0][j];
				avg[0][j] = dtemp;

				// 데이터값 정렬
				strcpy(itemp, data2018[i]);
				strcpy(data2018[i], data2018[j]);
				strcpy(data2018[j], itemp);

				// 지점명 정렬
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}

	// 지점별 출력
	printf("2018년 지점별 출력...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", data[0][i][j]);
		}
		printf("%5.lf \n", avg[0][i]);
	}
	press_any_key();
}

// 3년간 평균 실적별 통계
void avg_per_3()
{
	system("cls");
	// 평균 구하기
	for (int k = 0; k < 3; k++) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++)
				avg[k][i] += data[k][i][j];
			avg[k][i] = round(avg[k][i] / 6);
		}
	}

	// 3년간 평균 실적별 출력

	press_any_key();
}

// 세브 메뉴
void submenu_display(void)
{
	system("cls");
	press_any_key();
}


// 메인 함수
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