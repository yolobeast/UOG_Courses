#include "stdio.h"
#include "stdlib.h"
#include "string.h"

/*predefined functions*/
int teststack(int x[], int * top,int n);
int pop(int x[], int * top);
void push(int x[],int *top , int v);

int main(){

	
	int a[100];
	memset(a,-1,sizeof(a));
	int atop=0;
	
	/*call all the functions*/
	pop(a,&atop);
	push(a,&atop,5);
	push(a,&atop,4);
	push(a,&atop,1);
	pop(a,&atop);
	push(a,&atop,3);
	push(a,&atop,2);
	push(a,&atop,1);
    
	if(teststack(a, &atop,5)==1){
		printf("The stack is sorted !\n");
	}

	return(0);

}

//Push v onto the stack
void push(int x[],int*top,int v){
	
	x[*top]=v;
	(*top)++;
}


//Popand return the top element of the stack 10
//or 0 if stack empty
int pop(int x[],int * top){

	if(*top !=0)
	{
		(*top)--;
		return(x[*top]);
	}
	else
	{
		return(0);
	}
	x[(*top)]=-1;
}

//Returns True = 1 if and only if the stack contains 
//elements 1,2,...,n where 1 is the top of the stack
int teststack(int x[], int * top,int n){

	int g;
	for(g=0;g<=(n-1);g++)
	{
		if(x[*top]!=n && x[g]==(g+(n-g))){
			
			return(1);
		}
		
	}

	return(0);
}

