// Winsocket.cpp : Defines the entry point for the console application.

#include "stdafx.h"
#include <pthread.h>
#include "CommunicationController.h"

using namespace std;

#define PTW32_STATIC_LIB

#define NUM_THREADS     5

void *PrintHello(void *threadid){
	long tid;
	tid = (long)threadid;
	cout << "Hello World! Thread ID, " << tid << endl;
	pthread_exit(NULL);
	return 0;
}

int _tmain(int argc, _TCHAR* argv[]){

	pthread_t threads[NUM_THREADS];
		int rc;
		int i;
		for( i=0; i < NUM_THREADS; i++ ){
			cout << "main() : creating thread, " << i << endl;
			rc = pthread_create(&threads[i], NULL, 
			PrintHello, (void *)i);
			if (rc){
			cout << "Error:unable to create thread," << rc << endl;
			exit(-1);
		}
	}
	
	pthread_exit(NULL);
	
	CommunicationController c;

	return c.startServer();
}