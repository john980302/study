#include <winsock2.h>
#include <stdio.h>

#pragma comment(lib,"ws2_32.lib")

#define PORT 3500
#define MAX_BUF_SIZE 256

int main()
{
	WSADATA wsdata;

	if (WSAStartup(MAKEWORD(2, 2), &wsdata) != 0) {
		printf("������ ���� �ʱ�ȭ ����\n\n");
		return 1;
	}

	SOCKET clientSocket;
	clientSocket = socket(AF_INET, SOCK_STREAM, 0);



	if (clientSocket < 0) {
		printf("���� ���� ����!\n\n");
		WSACleanup();
		return 1;
	}

	SOCKADDR_IN serverAddress;
	ZeroMemory(&serverAddress, sizeof(serverAddress));

	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = inet_addr("10.80.76.143");

	if (connect(clientSocket, (SOCKADDR*)&serverAddress, sizeof(serverAddress)) != 0) {
		printf("���� ���ӿ� ����-IP(�������ּ�)�� PORT(��Ʈ��ȣ)�� Ȯ���Ұ�!\n\n");
		return 1;
	}

	char msg_str[MAX_BUF_SIZE];


	strcpy(msg_str, "~������ �� Ŭ���̾�Ʈ��~");
	send(clientSocket, msg_str, MAX_BUF_SIZE, 0);
	printf("������ ���� ������ : %s\n\n", msg_str);



	recv(clientSocket, msg_str, MAX_BUF_SIZE, 0);
	printf("Ŭ���̾�Ʈ�κ��� ���� ������ : %s\n\n", msg_str);

	gets(msg_str);

	closesocket(clientSocket);
	printf("�������� ������ ����\n\n");
	WSACleanup();
	printf("������ ���� ����\n\n");


	return 0;



}

