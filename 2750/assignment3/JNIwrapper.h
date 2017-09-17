/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class JNIwrapper */

#ifndef _Included_JNIwrapper
#define _Included_JNIwrapper
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     JNIwrapper
 * Method:    create_PM
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_JNIwrapper_create_1PM
  (JNIEnv *, jobject);

/*
 * Class:     JNIwrapper
 * Method:    destroy_PM
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_JNIwrapper_destroy_1PM
  (JNIEnv *, jobject);

/*
 * Class:     JNIwrapper
 * Method:    Manage_PM
 * Signature: (Ljava/lang/String;II)I
 */
JNIEXPORT jint JNICALL Java_JNIwrapper_Manage_1PM
  (JNIEnv *, jobject, jstring, jint, jint);

/*
 * Class:     JNIwrapper
 * Method:    hasValue_PM
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_JNIwrapper_hasValue_1PM
  (JNIEnv *, jobject, jstring);

/*
 * Class:     JNIwrapper
 * Method:    parseFrom_PM
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_JNIwrapper_parseFrom_1PM
  (JNIEnv *, jobject, jstring);

/*
 * Class:     JNIwrapper
 * Method:    next_PL
 * Signature: ([Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_JNIwrapper_next_1PL
  (JNIEnv *, jobject, jobjectArray);

/*
 * Class:     JNIwrapper
 * Method:    getstring
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_JNIwrapper_getstring
  (JNIEnv *, jobject, jstring);

/*
 * Class:     JNIwrapper
 * Method:    getlist
 * Signature: (Ljava/lang/String;)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_JNIwrapper_getlist
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
