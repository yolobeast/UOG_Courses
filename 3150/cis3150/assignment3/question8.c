#include "stdio.h"
#include "stdlib.h"
#include "string.h"

/*predefined functions*/
int teststack(int x[], int * top,int n);
int pop(int x[], int * top);
void push(int x[],int *top , int v);
int Move(int m);
void InitializeStack(char * input);
int Runalgo();
void sort(char *cards,int x, int *y);
int setarray(int * arr,int x);
int Runalgo(int numbertimeloops,int totnumberofcards);

/*globally defined values*/
int a[100];
int atop=0;
int b[100];
int btop=0;
int c[100];
int ctop=0;
char configuration [10000][8];
int numberofoccurance=0;


int main(){

    char input[100];
	int i=0;
	int x =0;

	printf("please input number cards: ");
	scanf("%d",&x);
	int check[x+1];
	char *numberofcards=malloc((x+1)*sizeof(char));
	setarray(check,(x+1));
	sort(numberofcards,(x+1),check);
	printf("Total solved config: %d\n",numberofoccurance);

	for(i=0;i<100;i++)
	{
		for(x=0;x<8;x++)
		{
			printf("%c",configuration[i][x] );
		}
	}
	
	return(0);

}

/*this function will set the array*/
int setarray(int * arr,int x){
	
	int i;
	for(i=0;i<=x;i++)
	{
		arr[i]=1;
	}

	arr[0]=2;
}

/*this function will premute the cards*/
void sort(char *cards,int x, int *y){

	int i;
	char c[3];
	int num=0;
	
	for(i=0;i<x;i++){
		if(y[i]==0){
			continue;
		}
		else
		{
			y[i]=y[i]-1;
			sprintf(c,"%d",i);
			strncat(cards,c,1);
			sort(cards,x,y);
			y[i]=y[i]+1;
		}
	}

	if(strlen(cards)==x+1)
	{
		for(i=0;i<strlen(cards);i++)
		{
			num++;
			configuration[num][i]=cards[i];
		}
		//seteverything(cards);
	}

	cards[strlen(cards)-1]='\0';

}

/*this function will set up every value for runalgo*/
int seteverything(char * input)
{
	int n = 0;
	int i =0;
	int maxloops=1;

	memset(a,-1,sizeof(a));
	memset(b,-1,sizeof(b));
	memset(c,-1,sizeof(c));
	atop=0;
	btop=0;
	ctop=0;
	InitializeStack(input);

	/*this will keep the number of cards*/
	n = atop+btop+ctop;

	for(i=1;i<=(n+2);i++)
	{
		maxloops = maxloops * i;
	}
	maxloops = maxloops/2;
    if(Runalgo(maxloops,n)==1)
    {
    	printf("%s\n",input);
    	numberofoccurance ++;
    }

}

/*this will initialize the functions*/
void InitializeStack(char * input)
{
	int i=0;
	int value=0;
	int x=1;
	int y=0;

	for(i=0;i<strlen(input);i++)
	{

		if((input[i]-'0') == 0 )
		{
			x++;
		}	
		else
		{
			if(x==1)
			{
				push(a,&atop,(input[i]-'0'));
			}
			if(x==2)
			{
				push(b,&btop,(input[i]-'0'));
			}
			if(x==3)
			{
				push(c,&ctop,(input[i]-'0'));
			}
		}
	}

}

/*This function will choose what moves to make*/
int Runalgo(int numbertimeloops,int totnumberofcards)
{
	int i=0;
	int algo[15]={1,3,5,3,3,5,5,2,2,1,1,1,1,1,5};

	/*this function will loop around the (n+2)!/2 times*/
	for(i=0;i<=numbertimeloops;i++)
	{
		if(teststack(a, &atop,totnumberofcards)==1)
		{
			return(1);
		}
		if(ctop ==0 && btop ==0)
		{
			Move(algo[0]);
		}
		else if(atop == 0 && ctop ==0)
		{
			Move(algo[1]);
		}
		else if(atop == 0 && btop ==0)
		{
			Move(algo[2]);
		}
		else if(atop == 0 && b[btop]<c[ctop])
		{
			Move(algo[3]);
		}
		else if(atop == 0 && c[ctop]<b[btop])
		{
			Move(algo[4]);
		}
		else if(btop == 0 && a[atop]<c[ctop])
		{
			Move(algo[5]);
		}
		else if(btop == 0 && c[ctop]<a[atop])
		{
			Move(algo[6]);
		}
		else if(ctop == 0 && a[atop]<b[btop])
		{
			Move(algo[7]);
		}
		else if(ctop == 0 && b[btop]<a[atop])
		{
			Move(algo[8]);
		}
		else if(a[atop]<b[btop]<c[ctop])
		{
			Move(algo[9]);
		}
		else if(a[atop]<c[ctop] <b[btop])
		{
			Move(algo[10]);
		}
		else if(b[btop]<a[atop] <c[ctop])
		{
			Move(algo[11]);
		}
		else if(b[btop]<c[ctop]<a[atop])
		{
			Move(algo[12]);
		}
		else if(c[ctop]<a[atop] <b[btop])
		{
			Move(algo[13]);
		}
		else if(c[ctop]<b[btop] <a[atop])
		{
			Move(algo[14]);
		}
	}


}
/*these are the move function that has been added*/
int Move(int m){

	int x=0;
	
	if(m==1 || m==2)
	{
		x=pop(a,&atop);
	}
	if(m==3 || m==4)
	{
		x=pop(b,&btop);
	}
	if(m==5 || m==6)
	{
		x=pop(c,&ctop);
	}

	if(x==0)
	{
		printf("There is an error\n");
	}
	if(m==3 || m==5)
	{
		push(a,&atop,x);
	}
	if(m==1 || m==6)
	{
		push(b,&btop,x);
	}
	if(m==2 || m==4)
	{
		push(c,&ctop,x);
	}

	return(1);

}

//Push v onto the stack
void push(int x[],int*top,int v){
	
	(*top)++;
	x[*top]=v;
	
}


//Popand return the top element of the stack 10
//or 0 if stack empty
int pop(int x[],int * top){

	if(*top ==0)
	{
		return(0);
	}
	*top= *top -1;
	return(x[*top+1]);
}

//Returns True = 1 if and only if the stack contains 
//elements 1,2,...,n where 1 is the top of the stack
int teststack(int x[], int * top,int n){

	int g;
	int i;
	if(n<1 || * top != n)
	{
		return(0);
	}
	for(g=1;g<=n;g++)
	{
		if(x[g] != n-g+1)
		{
			return(0);
		}	
	}	
	return(1);
}

