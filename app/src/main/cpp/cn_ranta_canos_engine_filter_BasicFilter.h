#ifndef CANOS_ANDROID_CN_RANTA_CANOS_ENGINE_FILTER_BASICFILTER_H
#define CANOS_ANDROID_CN_RANTA_CANOS_ENGINE_FILTER_BASICFILTER_H

#include <jni.h>

JNIEXPORT jstring JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_test(JNIEnv *env, jobject obj);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_gray(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height, jfloat factor);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_mosatic(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height, jint factor);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_lomo(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height, jfloat factor);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_nostalgic(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height, jfloat factor);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_comics(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height, jfloat factor);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_brown(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height, jfloat factor);

JNIEXPORT jintArray JNICALL
Java_cn_ranta_canos_engine_filter_BasicFilter_sketchPencil(JNIEnv *env, jobject obj, jintArray srcPixels, jint width, jint height);

#endif //CANOS_ANDROID_CN_RANTA_CANOS_ENGINE_FILTER_BASICFILTER_H
