#include <stdio.h>
#include <math.h>
#include <string.h>

int main(void)
{
	// 변수 선언
	int gwanak[] = { 70, 45, 100, 92, 150, 81 };
	int gangnam[] = { 88, 92, 77, 30, 52, 55 };
	int myeongdong[] = { 50, 90, 88, 75, 77, 49 };
	int daelim[] = { 120, 92, 80, 150, 130, 105 };
	int *data[] = { gwanak,gangnam,myeongdong,daelim };
	char branch[][10] = { "관악점","강남점","명동점","대림점" };
	double avg[4] = { 0 };
	double dtemp = 0;
	char ctemp[10] = { 0 };
	int itemp = 0;

	// 평균 구하기
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 6; j++)
			avg[i] += *(data[i] + j);
		avg[i] = round(avg[i] / 6);
	}
	// 실적별 정렬
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (avg[i] > avg[j])
			{
				// 평균 정렬
				dtemp = avg[i];
				avg[i] = avg[j];
				avg[j] = dtemp;

				// 데이터값 정렬
				itemp = data[i];
				data[i] = data[j];
				data[j] = itemp;
				
				// 지점명 정렬
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}
	// 실적별 출력
	printf("실적별 출력...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", *(data[i] + j));
		}
		printf("%5.lf \n", avg[i]);
	}

	// 두 줄 내리기
	printf("\n\n");

	// 지점별 정렬
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (strcmp(branch[i],branch[j]) < 0 )
			{
				// 평균 정렬
				dtemp = avg[i];
				avg[i] = avg[j];
				avg[j] = dtemp;

				// 데이터값 정렬
				itemp = data[i];
				data[i] = data[j];
				data[j] = itemp;

				// 지점명 정렬
				strcpy(ctemp, branch[i]);
				strcpy(branch[i], branch[j]);
				strcpy(branch[j], ctemp);
			}
		}
	}

	// 지점별 출력
	printf("지점별 출력...\n");
	for (int i = 0; i < 4; i++) {
		printf("%s", branch[i]);
		for (int j = 0; j < 6; j++) {
			printf("%5d ", *(data[i] + j));
		}
		printf("%5.lf \n", avg[i]);
	}
	return 0;

}