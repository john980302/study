// 201701637 ��ǻ�Ͱ��а�(��) �ֿ���

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//����ü ����
typedef struct item {
	int item_num;     //ǰ��
	char* item_name;  //ǰ�� 
	char* item_fuc;   //���
	int item_price;   //�ܰ�
	int item_sale_num;  //�Ǹż���
}item;

//�Լ� ����
void insert_itemfuc(item *itemlist);           // ����� �Է¹޴� �Լ�
void insert_item_salenum(item *itemlist);      // �Ǹż����� �Է¹޴� �Լ� 
void print_sales(item *itemlist);              // ��ǰ�Ǹ���Ȳ���� ����Լ�

											   //�Լ�
void insert_itemfuc(item *itemlist)
{
	char buffer[100];
	int size = 0, i = 0;
	while (i < 5) {
		printf("%s�� ����� �����ÿ�. : ", (itemlist + i)->item_name);
		fgets(buffer, sizeof(buffer), stdin); // �Է�
		buffer[strlen(buffer) - 1] = '\0';  //������ ���ڸ� �ι��ڷ� ��ü
		size = strlen(buffer);     // �Է¹��� ���ڿ��� ����
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
		printf("%s�� �Ǹż����� �����ÿ�. : ", (itemlist + i)->item_name);
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
	printf("%-10s%10s%25s%10s%10s\n", "ǰ��", "ǰ��", "���", "�ܰ�", "�Ǹż���");
	for (int i = 0; i < 5; i++) {
		printf("%-10d%10s%25s%10d%10d\n", (tmp + i)->item_num, (tmp + i)->item_name, (tmp + i)->item_fuc, (tmp + i)->item_price, (tmp + i)->item_sale_num);
		sum += (tmp + i)->item_price * (tmp + i)->item_sale_num;
	}
	printf(" => �Ǹ� �Ѿ� : %d\n", sum);

}

int main(void)
{
	FILE *fp;
	fp = fopen("part.txt", "r");
	if (fp == NULL) {
		printf("������ ���� ���߽��ϴ�.\n");
		return 1;
	}
	item itemlist[5];
	while (1) {
		char b[100];

	}
	/*insert_itemfuc(itemlist);          // ����� �Է¹޴� �Լ� ȣ��
	insert_item_salenum(itemlist);     // �Ǹż����� �Է¹޴� �Լ� ȣ��
	print_sales(itemlist);             // ��ǰ�Ǹ���Ȳ���� ����Լ� ȣ��*/
	return 0;
}