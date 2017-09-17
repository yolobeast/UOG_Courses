/*
 * ------------------------------------------------------------------
 *  ParameterList.h - Manages the list from parameter list val
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


struct Parameter{
  
  char b [100][100];
  int index;
  int temp;
};

typedef struct Parameter ParameterList;

ParameterList * creatList(void);

char* PL_next(ParameterList* l);
