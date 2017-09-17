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

#include "hash.h"

/*creates the tables to amout of size that was provided*/
table_h *  create_table(int size)
{
  table_h * hashtable=NULL;
  hashtable=malloc(sizeof(table_h));
  int i;
  
  if (size<1)
  {
    return(NULL);
  }
  
  if (hashtable==NULL)
  {
    return NULL;
  }
  
  if ((hashtable->list=malloc(sizeof(entry *)*size))==NULL)
  {
    return NULL;
  }
  
  for (i=0;i<size;i++)
  {
    hashtable->list[i]=NULL;
  }
  
  hashtable->size=size;
  
  return hashtable;
}

/*calculate the index value for the hash table so there will be less colusion*/
int hashvalue(table_h*hashtable,char*key)
{
  int hasval;
  int i =0;
  int returnval;
  
  hasval=strlen(key);
  
  returnval=hasval % hashtable->size;
  
  
  return (returnval);
}

/*is going to destory the table*/
void tableDelete(table_h*deltable)
{
  

  free(deltable);  
//  int i=0;
//  entry * delentry;
//  for (i=0;i<deltable->size;i++)
//  {
//   while(delentry->next!=NULL)
//   {
//     free(delentry->key);
//     free(delentry->value);
//        
//   }
//   delentry->next=NULL;
//  }
}

/*enter the new pair of value and key after settign up the iniatail*/
entry * newpair(char * key,void * value)
{
  
  entry*new=malloc(sizeof(entry));
  new->key=key;
  new->value=value; 
  new->next = NULL;
  
  return new;
}

/*this is inalize the first elemnt in the table as well as check for collsion */
void setTable(table_h*templatetable,char *key,void *value)
{
  
  int container=0;
  entry * newentry=NULL;
  entry * new =NULL;
  entry * old =NULL;
  
  container =hashvalue(templatetable,key);
  
  new = templatetable->list[container];
  
  while(new !=NULL && new -> key !=NULL && strcmp(key, new->key) > 0 )
  {
    old=new;
    new =new ->next;
  }
  
    newentry=newpair(key,value);
    if(new == templatetable-> list[ container] )
    {
      newentry->next =new;
      templatetable->list[container]=newentry;
      
    }
    else if(new == NULL)
    {
      old->next =newentry;
      
    }
    else
    {
      newentry->next = new;
      old -> next = newentry;
    }
}


/*search through the table and the linked list*/
void * tableGet(table_h*hashtable,char * key)
{
  int container =0;
  entry *pair;
  
  container = hashvalue(hashtable,key);
  
  pair= hashtable->list[container];
  
  while(pair!= NULL && pair ->key != NULL && strcmp(key, pair->key)>0)
  {
    pair = pair ->next;
    
  }
  
  if (pair == NULL || pair->key==NULL || strcmp(key , pair -> key)!=0)
  {
    return NULL;
    
  }
  else
  {
    return(pair->value);
  }
}
