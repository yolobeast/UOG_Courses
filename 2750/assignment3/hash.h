/*
 * ------------------------------------------------------------------
 *  hash.c - creat the table
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
#ifndef tabling
#define tabling

struct entries
{
  char * key;
  void * value;
  struct entries *next;
  
};
typedef struct entries entry;


struct hashtable
{
  int size; 
  struct entries ** list;
};
typedef struct hashtable table_h;


table_h * create_table(int size);
char * duplicate(const char * s);
void tableDelete(table_h*deleating);
int hashvalue(table_h*hashtable,char*key);
entry * newpair(char * key,void * value);
void setTable(table_h*templatetable,char *key,void *value);
void * tableGet(table_h*hashtable,char * key);
#endif