#include<jni.h>
#include<string.h>

#include "cn_ranta_canos_fragment_TestFragment.h"

JNIEXPORT jstring JNICALL
Java_cn_ranta_canos_fragment_TestFragment_stringFromJNI(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "Hello from TestFragment JNI !");
}
