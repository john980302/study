#include <WinSock2.h>
#include <stdio.h>

#pragma comment(lib, "ws2_32.lib")

#define PORT 3500
#define MAX_BUF_SIZE 256

int main()
{
	WSADATA wsdata;

	if (WSAStartup(MAKEWORD(2, 2), &wsdata) != 0)
	{
		printf("������ ���� �ʱ�ȭ ����\n\n");
		return 1;
	}
	SOCKET serverSocket;
	serverSocket = socket(AF_INET, SOCK_STREAM, 0);

	if (serverSocket < 0)
	{
		printf("���� ���� ����!\n\n");
		WSACleanup();
		return 1;
	}

	SOCKADDR_IN serverAddress;
	ZeroMemory(&serverAddress, sizeof(serverAddress));

	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = htonl(INADDR_ANY);

	if (bind(serverSocket, (SOCKADDR*)&serverAddress, sizeof(serverAddress)) == SOCKET_ERROR)
	{
		printf("serverSocket�� IP�� PORT�� �ο��ϴµ� ����!\n\n");
		return 1;
	}

	printf("Ŭ���̾�Ʈ�� ������ �����!\n\n");
	listen(serverSocket, SOMAXCONN);

	SOCKET socket;
	if ((socket = accept(serverSocket, NULL, NULL)) == INVALID_SOCKET)
	{
		printf("Ŭ���̾�Ʈ�� �����͸� �ְ�޴� ������ ������ �� ����!\n\n");
		return 1;
	}

	printf("Ŭ���̾�Ʈ�� ��������!\n\n");

	printf("��ȭ����!\n\n");

	char msg_str[MAX_BUF_SIZE];


	recv(socket, msg_str, MAX_BUF_SIZE, 0);
	printf("Ŭ���̾�Ʈ�κ��� ���� ������ : %s\n\n", msg_str);


	strcpy(msg_str, "~Ŭ���̾�Ʈ ������ ������~");
	send(socket, msg_str, MAX_BUF_SIZE, 0);
	printf("Ŭ���̾�Ʈ�� ���� ������ : %s\n\n", msg_str);


	gets(msg_str);


	closesocket(socket);
	printf("Ŭ���̾�Ʈ���� ������ ����\n\n");
	WSACleanup();
	printf("������ ���� ����\n\n");


	return 0;
}