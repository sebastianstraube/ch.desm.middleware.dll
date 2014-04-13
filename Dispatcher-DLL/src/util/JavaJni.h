#pragma once

#include <jni.h>

#include "Desm.h"

namespace desm {
	namespace util {
		namespace jni {
			
			static void throwExceptionWithMessage(JNIEnv* env, const char* msg) {
				env->ThrowNew(env->FindClass("java/lang/Exception"), msg);
			}

			static void checkReturnCode(JNIEnv * env, int rc) {
				switch(rc) {
				case desm::ERROR_OK:
					// everything is fine!
					break;
				case ERROR_FATAL:
					throwExceptionWithMessage(env, "fatal error");
					break;
				case ERROR_API_MISUSE:
					throwExceptionWithMessage(env, "api misuse");
					break;
				case ERROR_UNKNOWN_ID:
					throwExceptionWithMessage(env, "unknown id");
					break;
				default:
					throwExceptionWithMessage(env, "unknown desm error code");
					break;
				}
			}

		}
	}
};