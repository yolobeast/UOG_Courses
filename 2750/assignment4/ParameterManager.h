/*
 * ------------------------------------------------------------------
 *  ParameterManager.h - Manages the parameter
 *
 *  CIS*2750 - Software Systems Development and Integration - W15
 *  School of Computer Science
 *  University of Guelph
 *  Author: Sathi Ranganatahan
 * ------------------------------------------------------------------
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#ifndef pmmanage
#define pmmanage
typedef Boolean;
enum {false, true};
#include "hash.h"
#include "ParameterList.h"

typedef enum { INT_TYPE, REAL_TYPE, BOOLEAN_TYPE, STRING_TYPE, LIST_TYPE } param_t;

union param_value
{
    int           int_val;
    float         real_val;
    Boolean       bool_val;   
    char          *str_val;
    ParameterList *list_val;  
};


struct PMValue_table
{
  int initialized;
  char * pname;
  param_t ptype;
  union param_value value;
};

typedef struct PMValue_table ParameterValue;

struct PM_table
{
  table_h* values;
  char  iden[100][100];
  int tot;
};

typedef struct PM_table ParameterManager;


char checkcomment(char value, char chcaracter,FILE * openedfile);
int chekintype(char * value,ParameterValue*p);
int chekstring(char * value,ParameterValue*p);
int chekbooltype(char * value,ParameterValue*p);
int checkfloat(char *value, ParameterValue*p);
int checklist(char *value,ParameterValue*p);

ParameterValue * destoryallstring(ParameterValue*desstring);
ParameterValue * destoryalllist(ParameterValue*deslist);

int PM_manage(ParameterManager *p, char *pname, param_t ptype, int required);
int PM_parseFrom(ParameterManager *p, FILE *fp, char comment);
int checkforrequired(int a, int b);
ParameterManager * PM_create(int size);
int PM_destroy(ParameterManager *p);
int PM_hasValue(ParameterManager *p, char *pname);
union param_value PM_getValue(ParameterManager *p, char *pname);

#endif
