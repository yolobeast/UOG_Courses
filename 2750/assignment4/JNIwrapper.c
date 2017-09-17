#include <stdlib.h>
#include <stdio.h>
#include <jni.h>
#include "ParameterManager.h"

/*global variable*/
ParameterManager * params= NULL;

/*creatign function*/
JNIEXPORT jint JNICALL Java_JNIwrapper_create_1PM(JNIEnv * enviroment, jobject object)
{
  params = PM_create(10);
}

/*destory function for JNI
 * linked with my prameter manager*/
JNIEXPORT jint JNICALL Java_JNIwrapper_destroy_1PM(JNIEnv *enviroment, jobject object)
{
	//printf("hello");
	PM_destroy(params);
}

/*PM manage function 
 * it will test to see every value is avaliable*/
JNIEXPORT jint JNICALL Java_JNIwrapper_Manage_1PM(JNIEnv * enviroment, jobject object, jstring pramname, jint pramType, jint requir)
{
	const char * string;
	
	param_t ptype;
	
	string = (*enviroment)->GetStringUTFChars(enviroment,pramname,0);
	char *name = malloc(sizeof(strlen(string)+1));
		strcpy(name,string);
		if (pramType == 1)
		{
			ptype=INT_TYPE;
		}
		if (pramType == 2)
		{
			ptype=STRING_TYPE;
		}
		if (pramType == 3)
		{
			ptype=LIST_TYPE;
		}
		/*if (pramType == 4)
		{
			ptype=REAL_TYPE;
		}
		if (pramType == 5)
		{
			ptype=BOOLEAN_TYPE;
		}*/
		PM_manage(params,name,ptype,requir);
		(*enviroment)->ReleaseStringUTFChars(enviroment,pramname,string);
	
}

/*check to see table has the value in it */
JNIEXPORT jint JNICALL Java_JNIwrapper_hasValue_1PM(JNIEnv * enviroment, jobject object, jstring pramname)
{
	int res;
	
	const char * string= (*enviroment)->GetStringUTFChars(enviroment,pramname,NULL);
	char * name = malloc(sizeof(strlen(string)+1));
	strcpy(name,string);
	res=PM_hasValue(params,name);
	(*enviroment)->ReleaseStringUTFChars(enviroment,pramname,string);
	
	return(res);
}

/*PM parse form function 
 * this will linked up to the c file */
JNIEXPORT jint JNICALL Java_JNIwrapper_parseFrom_1PM(JNIEnv * enviroment, jobject object, jstring filename)
{
	FILE * fp;
	const char * string = (*enviroment)->GetStringUTFChars(enviroment,filename,NULL);
	int res=0;
	fp = fopen(string,"r");
	res = PM_parseFrom(params,fp,'#');
	(*enviroment)->ReleaseStringUTFChars(enviroment,filename,string);
	
	if(fp)
	{
		fclose(fp);
	}
		
	return(res);
}

/*this will get string 
 * from c function through PM_getvalue*/
JNIEXPORT jstring JNICALL Java_JNIwrapper_getstring(JNIEnv * enviroment, jobject object, jstring pramname)
{
	
	char * res=NULL;
	const char * string= (*enviroment)->GetStringUTFChars(enviroment,pramname,NULL);
	char * name = malloc(sizeof(strlen(string)+1));
	strcpy(name,string);
	if(name!=NULL)
	{
	  res=PM_getValue(params,name).str_val;
	  (*enviroment)->ReleaseStringUTFChars(enviroment,pramname,string);
	}
	return((*enviroment)->NewStringUTF(enviroment,res));

}



/*this will get list 
 * from c function through PM_getvalue*/
JNIEXPORT jobjectArray JNICALL Java_JNIwrapper_getlist(JNIEnv * enviroment, jobject object, jstring pramname)
{
	
	char * val;
	int i=0;
	ParameterList * list;
    jobjectArray res =NULL;
	const char * string= (*enviroment)->GetStringUTFChars(enviroment,pramname,NULL);
	char * name = malloc(sizeof(strlen(string)+1));
	strcpy(name,string);
	list=PM_getValue(params,name).list_val;
	res= (jobjectArray)(*enviroment)->NewObjectArray(enviroment,(list->temp+1),(*enviroment)->FindClass(enviroment,"java/lang/String"),NULL);
	
	while((val=PL_next(list)) != NULL)
	{
		(*enviroment)->SetObjectArrayElement(enviroment,res,i,(*enviroment)->NewStringUTF(enviroment,val));
		
		i++;
	}
	(*enviroment)->ReleaseStringUTFChars(enviroment,pramname,string);
	
	return(res);
	
}

