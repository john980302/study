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
		printf("원도우 소켓 초기화 실패\n\n");
		return 1;
	}
	SOCKET serverSocket;
	serverSocket = socket(AF_INET, SOCK_STREAM, 0);

	if (serverSocket < 0)
	{
		printf("소켓 생성 실패!\n\n");
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
		printf("serverSocket에 IP와 PORT를 부여하는데 실패!\n\n");
		return 1;
	}

	printf("클라이언트의 접속을 대기중!\n\n");
	listen(serverSocket, SOMAXCONN);

	SOCKET socket;
	if ((socket = accept(serverSocket, NULL, NULL)) == INVALID_SOCKET)
	{
		printf("클라이언트와 데이터를 주고받는 소켓을 생성할 수 없음!\n\n");
		return 1;
	}

	printf("클라이언트가 접속했음!\n\n");

	printf("대화시작!\n\n");

	char msg_str[MAX_BUF_SIZE];


	recv(socket, msg_str, MAX_BUF_SIZE, 0);
	printf("클라이언트로부터 받은 데이터 : %s\n\n", msg_str);


	strcpy(msg_str, "~클라이언트 접속을 축하해~");
	send(socket, msg_str, MAX_BUF_SIZE, 0);
	printf("클라이언트로 보낸 데이터 : %s\n\n", msg_str);


	gets(msg_str);


	closesocket(socket);
	printf("클라이언트와의 접속을 종료\n\n");
	WSACleanup();
	printf("윈도우 소켓 종료\n\n");


	return 0;
}