#include <stdafx.h>

extern "C" {
	__declspec(dllexport) void stw_deallocate(void** p) {
		if(p && *p) {
			free(*p);
			*p = NULL;
		}
	}
};