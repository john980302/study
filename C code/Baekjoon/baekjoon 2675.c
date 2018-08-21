#include <stdio.h>

int main() {

	int cnt,t_num,size; // °¹¼ö, °¢ È½¼ö
	char t_c[21];
	scanf("%d", &cnt);
	for (int i = 0; i < cnt; i++) {
		scanf("%d %s", &t_num, &t_c);
		size = 0;
		for(int j=0;t_c[j];j++)	{
			for (int k = 0; k < t_num; k++)
				printf("%c", t_c[size]);
			size++;
		}
		printf("\n");
	}
	return 0;
}