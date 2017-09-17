
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
%{
#include "y.tab.h"
%}

%%
[\t ]+    
#(.*)   
\n       
=	{return EQUALS;}
;  	{return SIMCOLONE;}     
[{]	{return L_OPEN;}
[}]	{return L_CLOSE;}
["]	{return QUOTES;}
,	{return COMMA;}
[^=;{}",\n\t ]+ {
			yylval.sval = strdup(yytext);
			if(strcmp(yytext, "title")==0)
			{
				return TITLE;
			}
			if(strcmp(yytext, "fields")==0)
			{
				return FIELDS;
			}
			if(strcmp(yytext, "buttons")==0)
			{
				return BUTTONS;
			}
			//printf("%s\n",yytext);
			return WORD;

		 }
%%