/*  =====================================================================
    zhelpers.h

    Helper header file for example applications.
    =====================================================================
*/

#ifndef __ZHELPERS_H_INCLUDED__
#define __ZHELPERS_H_INCLUDED__

//  Include a bunch of headers that we will need in the examples

#include <zmq.h>

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <time.h>
#include <assert.h>
#include <signal.h>

#pragma comment(lib, "Ws2_32.lib")

//  Version checking, and patch up missing constants to match 2.1
#if ZMQ_VERSION_MAJOR == 2
#   error "Please upgrade to ZeroMQ/3.2 for these examples"
#endif

//  Provide random number from 0..(num-1)
#if (defined (_MSC_VER))
#   define randof(num)  (int) ((float) (num) * rand () / (RAND_MAX + 1.0))
#else
#   define randof(num)  (int) ((float) (num) * random () / (RAND_MAX + 1.0))
#endif


//  Receive 0MQ string from socket and convert into C string
//  Caller must free returned string. Returns NULL if the context
//  is being terminated.
static char *
zmqh_recv (void *socket) {
    char buffer [256];
    int size = zmq_recv (socket, buffer, 255, 0);
    if (size == -1)
        return NULL;
    if (size > 255)
        size = 255;
    buffer [size] = 0;
    return _strdup (buffer);
}

static int
zmqh_has_more(void* socket) {
	int has_more = 0;
	size_t len;
	int rc = zmq_getsockopt(socket, ZMQ_RCVMORE, &has_more, &len);
	if(rc != 0)
		return 0;
	return has_more;
}

//  Convert C string to 0MQ string and send to socket
static int
zmqh_send (void *socket, char *string) {
    int size = zmq_send (socket, string, strlen (string), 0);
    return size;
}

//  Sends string as 0MQ string, as multipart non-terminal
static int
zmqh_sendmore (void *socket, char *string) {
    int size = zmq_send (socket, string, strlen (string), ZMQ_SNDMORE);
    return size;
}

//  Sleep for a number of milliseconds
static void
zmqh_sleep (int msecs)
{
#if (defined (_MSC_VER))
    Sleep (msecs);
#else
    struct timespec t;
    t.tv_sec  =  msecs / 1000;
    t.tv_nsec = (msecs % 1000) * 1000000;
    nanosleep (&t, NULL);
#endif
}

//  Return current system clock as milliseconds
static int64_t
zmqh_clock (void)
{
#if (defined (_MSC_VER))
    SYSTEMTIME st;
    GetSystemTime (&st);
    return (int64_t) st.wSecond * 1000 + st.wMilliseconds;
#else
    struct timeval tv;
    gettimeofday (&tv, NULL);
    return (int64_t) (tv.tv_sec * 1000 + tv.tv_usec / 1000);
#endif
}

//  Print formatted string to stdout, prefixed by date/time and
//  terminated with a newline.

static void
zmqh_console (const char *format, ...)
{
    va_list argptr;
    time_t curtime = time (NULL);
    struct tm *loctime = localtime (&curtime);
    char *formatted = (char*)malloc (20);
    strftime (formatted, 20, "%y-%m-%d %H:%M:%S ", loctime);
    printf ("%s", formatted);
    free (formatted);

    va_start (argptr, format);
    vprintf (format, argptr);
    va_end (argptr);
    printf ("\n");
}

#endif  //  __ZHELPERS_H_INCLUDED__
