#include <winsock2.h>
#include <stdio.h>

#pragma comment(lib,"ws2_32.lib")

#define PORT 3500
#define MAX_BUF_SIZE 256

int main()
{
	WSADATA wsdata;

	if (WSAStartup(MAKEWORD(2, 2), &wsdata) != 0) {
		printf("윈도우 소켓 초기화 실패\n\n");
		return 1;
	}

	SOCKET clientSocket;
	clientSocket = socket(AF_INET, SOCK_STREAM, 0);



	if (clientSocket < 0) {
		printf("소켓 생성 실패!\n\n");
		WSACleanup();
		return 1;
	}

	SOCKADDR_IN serverAddress;
	ZeroMemory(&serverAddress, sizeof(serverAddress));

	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = inet_addr("10.80.76.143");

	if (connect(clientSocket, (SOCKADDR*)&serverAddress, sizeof(serverAddress)) != 0) {
		printf("서버 접속에 실패-IP(아이피주소)와 PORT(포트번호)를 확인할것!\n\n");
		return 1;
	}

	char msg_str[MAX_BUF_SIZE];


	strcpy(msg_str, "~서버야 나 클라이언트야~");
	send(clientSocket, msg_str, MAX_BUF_SIZE, 0);
	printf("서버로 보낸 데이터 : %s\n\n", msg_str);



	recv(clientSocket, msg_str, MAX_BUF_SIZE, 0);
	printf("클라이언트로부터 받은 데이터 : %s\n\n", msg_str);

	gets(msg_str);

	closesocket(clientSocket);
	printf("서버와의 접속을 종료\n\n");
	WSACleanup();
	printf("윈도우 소켓 종료\n\n");


	return 0;



}

