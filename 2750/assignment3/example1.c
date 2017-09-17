/*
 * ------------------------------------------------------------------
 *  a1example.c - sample program demonstrating use of assignment 1
 *                ParameterManager library
 *
 *  CIS*2750 - Software Systems Development and Integration - W15
 *  School of Computer Science
 *  University of Guelph
 *  Author: David McCaughan
 * ------------------------------------------------------------------
 */


#include <stdlib.h>
#include <stdio.h>
#include "ParameterManager.h"


int main()
{
    ParameterManager *params;   /* parameter manager for student record */
    ParameterList *plist;
    ParameterList * plist2;/* list of marks parameter */
    char *item;                 /* item from list of marks (for iteration) */
    char *name;
    char * idiots;/* student name */
    int id;                     /* student ID */
    Boolean registered;         /* registration status */
    float avg = -1;             /* average (optional parameter) */

    if (!(params = PM_create(10)))
    {
        /* ERROR CREATING PARAMETER MANAGER */
        exit(0);
    }

    /*
     * register parameters of interest
     */
    PM_manage(params,"idiots",STRING_TYPE,1);
    PM_manage(params,"name",LIST_TYPE,1);
    PM_manage(params,"id",INT_TYPE,1);
    PM_manage(params,"regstat",BOOLEAN_TYPE,1);
    PM_manage(params,"marks",LIST_TYPE,1);
    PM_manage(params,"average",REAL_TYPE,1);

    /*
     * parse parameters fromn standard input
     */
    if (!PM_parseFrom(params,stdin,'#'))
    {
        
      printf("parse error\n");
      /* ERROR PARSING PARAMETERS */
        exit(0);
    }

    /*
     * query parameter manager to get parameter values
     */
    plist2 = PM_getValue(params,"name").list_val;

    idiots = PM_getValue(params,"idiots").str_val;
    id = PM_getValue(params,"id").int_val;

    registered = PM_getValue(params,"regstat").bool_val;

    plist = PM_getValue(params,"marks").list_val;

    if (PM_hasValue(params,"average"))
        avg = PM_getValue(params,"average").real_val;

    /*
     * output of parameters
     */
    printf("Parameters:\n");
    printf("name        = ");
    while(name = PL_next(plist2))
     //item = PL_next(plist) ;  
    printf("%s ",name);
    //PL_next(plist); 
    printf("\n");
    printf("idiots      = %s\n",idiots);
    printf("id          = %d\n",id);
    printf("registered? = %s\n",registered == true ? "yes" : "no");

    printf("marks       = ");
    while(item = PL_next(plist))
     //item = PL_next(plist) ;  
    printf("%s ",item);
    //PL_next(plist); 
    printf("\n");

    printf("average     = ");
    if (avg >= 0)
        printf("%f\n",avg);
    else
        printf("not entered\n");

    PM_destroy(params);

    return(1);

}
