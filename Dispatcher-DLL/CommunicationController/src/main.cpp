// main.cpp : Defines the entry point for the console application.
#include "stdafx.h"

#include "Thread.h"
#include "CommunicationController.h"

#define MAX_THREADS 2
#define BUF_SIZE 255

using namespace std;

DWORD WINAPI MyThreadFunctionStartServer( LPVOID lpParam );
DWORD WINAPI MyThreadFunctionStartClient( LPVOID lpParam );

void ErrorHandler(LPTSTR lpszFunction);

//------------------------------------------
// A function to Display the message
// indicating in which tread we are
//------------------------------------------
void DisplayMessage (HANDLE hScreen, char *ThreadName, int Data, int Count)
{

    TCHAR msgBuf[BUF_SIZE];
    size_t cchStringSize;
    DWORD dwChars;

    // Print message using thread-safe functions.
    StringCchPrintf(msgBuf, BUF_SIZE, TEXT("."), Count, ThreadName, Data); 
    StringCchLength(msgBuf, BUF_SIZE, &cchStringSize);
    WriteConsole(hScreen, msgBuf, cchStringSize, &dwChars, NULL);
}

//-------------------------------------------
// A function that represents Thread number 1
//-------------------------------------------
DWORD WINAPI Thread_no_1( LPVOID lpParam ) 
{
    int     Data = 0;
    int     count = 0;
    HANDLE  hStdout = NULL;
    
    // Get Handle To screen.
    // Else how will we print?
    if( (hStdout = GetStdHandle(STD_OUTPUT_HANDLE)) == INVALID_HANDLE_VALUE )  
    return 1;

    // Cast the parameter to the correct
    // data type passed by callee i.e main() in our case.
    Data = *((int*)lpParam);

	DisplayMessage (hStdout, "Thread_no_1", 0, 0);

	CommunicationController c;
	c.startServer();

    return ERROR;
} 

//-------------------------------------------
// A function that represents Thread number 2
//-------------------------------------------
DWORD WINAPI Thread_no_2( LPVOID lpParam ) 
{
    int     Data = 0;
    int     count = 0;
    HANDLE  hStdout = NULL;
    
    // Get Handle To screen. Else how will we print?
    if( (hStdout = GetStdHandle(STD_OUTPUT_HANDLE)) == INVALID_HANDLE_VALUE )  
    return 1;

    // Cast the parameter to the correct
    // data type passed by callee i.e main() in our case.
    Data = *((int*)lpParam); 

	DisplayMessage (hStdout, "Thread_no_2", 0, 0);

	Sleep(2000);

    CommunicationController c;
	c.startClient();
    
    return 0; 
}

int startThreadTest(){
	 // Data of Thread 1
    int Data_Of_Thread_1 = 1;
    // Data of Thread 2
    int Data_Of_Thread_2 = 2;

    // variable to hold handle of Thread 1
    HANDLE Handle_Of_Thread_1 = 0;
    // variable to hold handle of Thread 2
    HANDLE Handle_Of_Thread_2 = 0;

    // Aray to store thread handles 
    HANDLE Array_Of_Thread_Handles[2];

    // Create thread 1.
    Handle_Of_Thread_1 = CreateThread( NULL, 0, Thread_no_1, &Data_Of_Thread_1, 0, NULL);  
    if ( Handle_Of_Thread_1 == NULL) ExitProcess(Data_Of_Thread_1);
    
    // Create thread 2.
    Handle_Of_Thread_2 = CreateThread( NULL, 0, Thread_no_2, &Data_Of_Thread_2, 0, NULL);  
    if ( Handle_Of_Thread_2 == NULL) ExitProcess(Data_Of_Thread_2);

    // Store Thread handles in Array of Thread
    // Handles as per the requirement
    // of WaitForMultipleObjects() 
    Array_Of_Thread_Handles[0] = Handle_Of_Thread_1;
    Array_Of_Thread_Handles[1] = Handle_Of_Thread_2;

    // Wait until all threads have terminated.
	WaitForMultipleObjects(2, Array_Of_Thread_Handles, TRUE, INFINITE);
    printf("Since All threads executed lets close their handles \n");

    // Close all thread handles upon completion.
    CloseHandle(Handle_Of_Thread_1);
    CloseHandle(Handle_Of_Thread_2);

	return 0;
}

class HelloWorld
{
public:
    DWORD print ()
    {
        std::cout << "Hello World!" << std::endl;
        return 0;
    }
};

const char* CommunicationController::initialize::DEFAULT_CLIENT_IP = "127.0.0.1";

int _tmain(int argc, _TCHAR* argv[]){

	/*
	startThreadTest();
	*/

	// Random object with DWORD method (void)
    CommunicationController cc;

    // thread should call print method of world.
	Thread<CommunicationController> thread_server(&cc, &CommunicationController::startServer);
    if (thread_server.start()){
		std::cout << "Thread start()" << std::endl;
	}
	
    // thread should call print method of world.
	Thread<CommunicationController> thread_client(&cc, &CommunicationController::startClient);
    if (thread_client.start()){
		std::cout << "Thread start()" << std::endl;
	}

	thread_server.join(); // wait for thread
	thread_client.join(); // wait for thread

	system("pause");
    return 0;
}