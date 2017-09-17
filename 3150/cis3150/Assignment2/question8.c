#include "stdio.h"
#include "stdlib.h"
#include "string.h"

/*predefined functions*/
int teststack(int x[], int * top,int n);
int pop(int x[], int * top);
void push(int x[],int *top , int v);
int Move(int m);

/*globally defined values*/
int a[100];
int atop=0;
int b[100];
int btop=0;
int c[100];
int ctop=0;


int main(){

	memset(a,-1,sizeof(a));
	memset(b,-1,sizeof(b));
	memset(c,-1,sizeof(c));
    /*call all the functions*/
	pop(a,&atop);
	push(a,&atop,5);
	push(a,&atop,4);
	push(a,&atop,1);
	pop(a,&atop);
	push(a,&atop,3);
	push(a,&atop,2);
	push(a,&atop,1);
	int i=0;
    
	if(teststack(a, &atop,5)==1){
		printf("The stack is sorted !\n");
	}

	return(0);

}

/*these are the move function that has been added*/
int Move(int m){

	int x=0;
	if(m == 1){
		push(b,&btop,a[atop]);
		x=pop(a,&atop);
	}
	else if(m == 2){
		push(c,&ctop,a[atop]);
		x=pop(a,&atop);
	}
	else if(m == 3){
		push(a,&atop,b[atop]);
		x=pop(b,&btop);
	}
	else if(m == 4){
		push(c,&ctop,b[atop]);
		x=pop(b,&btop);
	}
	else if(m == 5){
		push(a,&atop,c[atop]);
		x=pop(c,&ctop);
	}
	else if(m == 6){
		push(b,&btop,c[atop]);
		x=pop(c,&ctop);
	}

	return(x);

}

//Push v onto the stack
void push(int x[],int*top,int v){
	
	(*top)++;
	x[*top]=v;
	
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
	for(g=0;g<=n;g++)
	{
		if(x[*top]!=n){
			
			return(1);
		}
		
	}

	return(0);
}

