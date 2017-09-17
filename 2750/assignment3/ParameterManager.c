/*
 * ------------------------------------------------------------------
 *  ParameterManager.c - Manages the parameter
 *
 *  CIS*2750 - Software Systems Development and Integration - W15
 *  School of Computer Science
 *  University of Guelph
 *  Author: Sathi Ranganatahan
 * ------------------------------------------------------------------
 */

#include "hash.h"
#include "ParameterManager.h"

int checkfor=0;

/*this function will parse the parameter value info and the identifier
 * and stroe in a 2 d array to check to see if it's int,char,bool,list
 * and stores theose values in a union  and to the table*/
int PM_parseFrom(ParameterManager *p, FILE *fp, char comment)
{
  char returnfile;
  char fileinfo[500][500];
  char identifier[500][500];
  char listnum[500][500];
  int i=0;
  int x=0;
  int a=0;
  int b=0;
  int c=0;
  int d=0;
  int z=0;
  int qutation=0;
  int g=1;
  int mode=0;
  char commentchar=comment;
  ParameterValue * setvalue;
  int q;

  /*parse is based upon chcracter by chcaracter*/
  while((returnfile=fgetc(fp))!=EOF )
  {
    
    if (returnfile==' ' || returnfile == '\t')
    {
      continue;  
    }
    
    returnfile=checkcomment(returnfile, commentchar,fp);
    
    if(returnfile=='=')
    {
      
      while(returnfile !=';')
      {
	
	returnfile=checkcomment(returnfile, commentchar,fp);
	returnfile=fgetc(fp);
	
	if(returnfile=='=')
	{
	   return(0);
	}
	
	if(returnfile=='"')
	{
	  qutation=1;
	}
	
	if(qutation==0)
	{
	  if (returnfile=='\n'|| returnfile == commentchar || returnfile==';' || returnfile ==' ' || returnfile == '\t')
	  {
	    continue;
	  }
	}
	else 
	{
	  if (returnfile=='"'|| returnfile == commentchar || returnfile==';' || returnfile == '\t' || returnfile == ' ')
	  {
	    continue;
	  }
	}
	
	fileinfo[i][x]=returnfile;
	x++;
	
      }
      x=0;
      qutation=0;
      i++;
      
    }
    if (returnfile=='\n')
    {
      continue;
      
    }
    
    if (returnfile==';')
    {
      returnfile='\0';
    }
    
    identifier[a][b]=returnfile;
    p->iden[a][b]=returnfile;
    b++;
    
    if(returnfile=='\0')
    {
      b=0;
      
      setvalue=tableGet(p->values,identifier[a]);
      
      if(!setvalue)
      {
	if(checkfor==1)
	{
	  return(1);
	}
	else
	{
	  return(0);
	}
      }
      
      if (setvalue->ptype==INT_TYPE)
      {
			g=chekintype(fileinfo[a],setvalue);
      }
      if (setvalue->ptype==REAL_TYPE)
      {
			g=checkfloat(fileinfo[a],setvalue);
      }
      if (setvalue->ptype==BOOLEAN_TYPE)
      {
			g=chekbooltype(fileinfo[a],setvalue);
      }
      if (setvalue->ptype==STRING_TYPE)
      {
			g=chekstring(fileinfo[a],setvalue);
			//printf("strings : %s\n",fileinfo[a]);
			memset(fileinfo,0,sizeof fileinfo);
      }
      if(setvalue->ptype==LIST_TYPE)
      {
			g=checklist(fileinfo[a],setvalue);
			memset(fileinfo,0,sizeof fileinfo);
      }
      
      a++; 
     
    }
   
  }

  if(checkforrequired(p->tot,a)!=0)
  {
	return(0);
  }
  return(1); 
}


/*thisfunction will eleminate any comment that is between the parametter values*/
char checkcomment(char value, char chcaracter,FILE * openedfile)
{
  if(value==chcaracter)
  {
    while(value !='\n' && value!=EOF)
    {
      value=fgetc(openedfile);
      if (value=='!')
      {
	value=fgetc(openedfile);
	if(value=='#')
	{
	  checkfor=1;
	}
      }
    }
    
  } 
  return(value);
}

int checkforrequired(int a, int b)
{
  
  
  if (a!=b)
  {
    return(1);
    
  }
  else
  {
  
    return(0);
  }

  
}


/*this function will calls the the create table funcetion
 * and takes in a size from the main file*/
ParameterManager * PM_create(int size)
{
  ParameterManager*p=malloc(sizeof(ParameterManager));
  p->tot=0;
  p->values=create_table(size);
  if (size<=0)
  {
    printf("the size less than zero");
    return(NULL);
  }
  
  return(p);
}

/*this function calls the table destory function to free the table 
 * by the value and the key*/
int PM_destroy(ParameterManager *p)
{
  /*ParameterValue * destoryvals;
   int i;
    for(i=0;i<p->tot;i++)
   {
      destoryvals=tableGet(p->values,p->iden[i]);
     
     if(destoryvals)
     {
       
       if(destoryvals->ptype==STRING_TYPE)
       {
 		  destoryallstring(destoryvals);
       }
       else if(destoryvals->ptype==LIST_TYPE)
       {
 		 destoryalllist(destoryvals);
       }
     }
   }
  tableDelete(p->values);
  free(p);*/
  return(1);
}

ParameterValue * destoryallstring(ParameterValue*desstring)
{
		free(desstring->value.str_val);
}

ParameterValue * destoryalllist(ParameterValue*deslist)
{
  free(deslist->value.list_val);
}


/*it inalize the the table withh the identfier name from the main 
 * to creat the table*/
int PM_manage(ParameterManager *p, char *pname, param_t ptype, int required)
{
  ParameterValue * vals = malloc(sizeof(ParameterValue));
  vals->ptype=ptype;
  vals->initialized=required;
   
    /*  if (vals->ptype==STRING_TYPE)
      {
		printf("manager item is string:%s\n",pname);	
      }
      if(vals->ptype==LIST_TYPE)
      {
		printf("manager item is list:%s\n",pname);	
      }*/
   
  setTable(p->values,pname,vals);
  
  p->tot++;
  return(1);
  
}


/*get the stored values sets back to the main */
union param_value PM_getValue(ParameterManager* p, char *pname)
{
  ParameterValue * tableval;
  tableval =  tableGet(p->values,pname);
  
  return(tableval->value);  
}



/*this function will check if the table is set up properly 
 * if the table key value is not there then it returns a zero*/
int PM_hasValue(ParameterManager *p, char *pname)
{
  ParameterManager*tablesearch=tableGet(p->values,pname);
  if(tablesearch)
  {
    return(1);
  }
  else
  {
    return(0);
  }
}

/*this function takes a pointer to my parameter value as will as 
 * pointer to my 2d arry info then pares it out and sets the int_val*/
int chekintype(char * value,ParameterValue*p)
{
  
  int id;
  int c;
  int n=0;
  for (c=0;c<strlen(value);c++)
  {
    if (isdigit(value[c]) || value[c] =='-')
    {
      continue;
    }
    else 
    {
      return(0);
      
    }
  }
  p->value.int_val=(int)atoi(value);
  //printf("%d",p->values.int_val);
  return(1);
}


/*same as checking the int val except this will check 
 * if it has a decimal point*/
int checkfloat(char *value, ParameterValue*p)
{
  
  int c;
  int n=0;
  int m=0;
  
  for (c=0;c<strlen(value);c++)
  {
    if (isdigit(value[c]))
    {
      n++;
      continue;
    }
    if(value[c]=='.' || value[c] =='-')
    {
      n=n+3;
      continue;
      
    }
    else
    {
      return(0);
      
    }
  }
  
  if( n> strlen(value))
  {
    p->value.real_val=(float)atof(value);
    //printf("%lf\n",p->value.real_val);
    return(1);
  }
  else 
  {
    return(0);
    
  }
}

/*same idea as the int check buth this time it will set the boollean value 
 * to yes or no*/
int chekbooltype(char * value,ParameterValue*p)
{ 
  int c;
  
  for (c=0;c<strlen(value);c++)
  {
    if (value[c]=='t' && value[c+1]=='r' && value[c+2]=='u' && value[c+3]=='e')
    {
      p->value.bool_val=true;
      
      return(1);
      
    }
    else if (value[c]=='f' && value[c+1]=='a' && value[c+2]=='l' && value[c+3]=='s' && value[c+4]=='e')
    {
      p->value.bool_val=false;
      return(1);
    }
    
  }
  return(0);
}

/*this function will itterate through the list of string that is between '{' and '}'
 * then it will will ste the list_val */
int checklist(char *value,ParameterValue*p)
{ 
  char temp[300][300];
  int x=0;
  int c=0;
  int a=0;
  int b=0;
  char z;
  int i;
  int e=0;
  int f;
  
  ParameterList*list=creatList();
  
  for (e=0;e<strlen(value);e++)
  {
    if(value[e]=='{')
    {
      x = strlen(value);
      for(c=1;c<(x-1);c++)
      {
		z=value[c];
	
		if(z!=',')
		{
			temp[a][b]=z;
			b++;
		}
		if(z==',')
		{
			b=0;
			a++;
			list->index++;
			list->temp++;
		}
		strcpy(list->b[a],temp[a]);
	
      }
    }
  }
  
  memset(temp,0,sizeof temp);
  p->value.list_val=list; 
  if(p->value.list_val==NULL)
  {
    return(0);
  }
 
  return(1);
}


/*this function will check to seeif ther is '"' or 
 * it will checkfirst element it it  aletter then it wil stored in string_val*/
int chekstring(char * value,ParameterValue*p)
{
  
  int i;
  int c;
  p->value.str_val=malloc(strlen(value)+1);
  
 
  strcpy(p->value.str_val,value);
  
 return(1);

}
