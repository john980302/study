// 201701637 컴퓨터공학과(야) 최우진

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//구조체 선언
typedef struct item {
	int item_num;     //품번
	char* item_name;  //품명 
	char* item_fuc;   //사양
	int item_price;   //단가
	int item_sale_num;  //판매수량
}item;

//함수 선언
void insert_itemfuc(item *itemlist);           // 사양을 입력받는 함수
void insert_item_salenum(item *itemlist);      // 판매수량을 입력받는 함수 
void print_sales(item *itemlist);              // 부품판매현황보고서 출력함수

											   //함수
void insert_itemfuc(item *itemlist)
{
	char buffer[100];
	int size = 0, i = 0;
	while (i < 5) {
		printf("%s의 사양을 적으시오. : ", (itemlist + i)->item_name);
		fgets(buffer, sizeof(buffer), stdin); // 입력
		buffer[strlen(buffer) - 1] = '\0';  //마지막 문자를 널문자로 교체
		size = strlen(buffer);     // 입력받은 문자열의 길이
		if (size > 0) {
			char * newstr = (char *)malloc(sizeof(char)*(size + 1));
			strcpy(newstr, buffer);
			(itemlist + i)->item_fuc = newstr;
			i++;
		}
		else {
			break;
		}
	}
}

void insert_item_salenum(item *itemlist)
{
	int temp, i = 0;
	while (i < 5) {
		printf("%s의 판매수량을 적으시오. : ", (itemlist + i)->item_name);
		scanf("%d", &temp);
		(itemlist + i)->item_sale_num = temp;
		i++;
	}
}

void print_sales(item *itemlist)
{
	int sum = 0;
	item *tmp;
	tmp = itemlist;
	printf("----------------------------------------------------------\n");
	printf("%-10s%10s%25s%10s%10s\n", "품번", "품명", "사양", "단가", "판매수량");
	for (int i = 0; i < 5; i++) {
		printf("%-10d%10s%25s%10d%10d\n", (tmp + i)->item_num, (tmp + i)->item_name, (tmp + i)->item_fuc, (tmp + i)->item_price, (tmp + i)->item_sale_num);
		sum += (tmp + i)->item_price * (tmp + i)->item_sale_num;
	}
	printf(" => 판매 총액 : %d\n", sum);

}

int main(void)
{
	FILE *fp;
	fp = fopen("part.txt", "r");
	if (fp == NULL) {
		printf("파일을 열지 못했습니다.\n");
		return 1;
	}
	item itemlist[5];
	while (1) {
		char b[100];

	}
	/*insert_itemfuc(itemlist);          // 사양을 입력받는 함수 호출
	insert_item_salenum(itemlist);     // 판매수량을 입력받는 함수 호출
	print_sales(itemlist);             // 부품판매현황보고서 출력함수 호출*/
	return 0;
}