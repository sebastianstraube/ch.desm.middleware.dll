#pragma once

#include <string.h>
#include <algorithm>

namespace desm {
	namespace util {
		static int strlcpy(char* dst, const char* src, int len) {
			::strncpy_s(dst, len, src, len - 1);
			dst[len - 1] = '\0';
			return std::min<int>(strlen(src), len - 1);
		}
	}
}
