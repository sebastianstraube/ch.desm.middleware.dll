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

			static void addObjectToArrayList(JNIEnv* env, jobject al, jobject val) {
				jclass clazz = env->GetObjectClass(al);
				jmethodID methodId = env->GetMethodID(clazz, "add", "(Ljava/lang/Object;)Z");
				if(!methodId) {
					return;
				}
				env->CallObjectMethod(al, methodId, val);
			}

			template<class T> static void addToArrayList(JNIEnv* env, jobject al, T val);

			template<> static void addToArrayList(JNIEnv* env, jobject al, int val) {
				jclass cls = env->FindClass("java/lang/Integer");
				jmethodID methodId = env->GetMethodID(cls, "<init>", "(I)V");
				jobject valObj = env->NewObject(cls, methodId, val);
				addObjectToArrayList(env, al, valObj);
			}

			template<> static void addToArrayList(JNIEnv* env, jobject al, float val) {
				jclass cls = env->FindClass("java/lang/Float");
				jmethodID methodId = env->GetMethodID(cls, "<init>", "(F)V");
				jobject valObj = env->NewObject(cls, methodId, val);
				addObjectToArrayList(env, al, valObj);
			}

			template<> static void addToArrayList(JNIEnv* env, jobject al, double val) {
				jclass cls = env->FindClass("java/lang/Double");
				jmethodID methodId = env->GetMethodID(cls, "<init>", "(D)V");
				jobject valObj = env->NewObject(cls, methodId, val);
				addObjectToArrayList(env, al, valObj);
			}
			template<> static void addToArrayList(JNIEnv* env, jobject al, std::string val) {
				jstring valObj = env->NewStringUTF(val.c_str());
				addObjectToArrayList(env, al, valObj);
			}

			static jobject newArrayList(JNIEnv* env) {
				jclass cls = env->FindClass("java/util/ArrayList");
				jmethodID methodId = env->GetMethodID(cls, "<init>", "()V");
				return env->NewObject(cls, methodId);
			}
		}
	}
};