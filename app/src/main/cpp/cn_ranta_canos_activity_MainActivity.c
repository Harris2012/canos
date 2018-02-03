#include<jni.h>
#include<string.h>

#include "cn_ranta_canos_activity_MainActivity.h"

JNIEXPORT jstring JNICALL
Java_cn_ranta_canos_activity_MainActivity_stringFromJNI(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "Hello from JNI !");
}