#include "stdio.h"
#include "stdlib.h"
#include "string.h"

/*this is the global functions*/
void sort(char *cards,int x, int *y);
int setarray(int * arr,int x);

int main()
{
	
	int x;
	int i;
	
	printf("please input number cards: ");
	scanf("%d",&x);
	int check[x+1];
	char *numberofcards=malloc((x+1)*sizeof(char));
	setarray(check,(x+1));
	sort(numberofcards,(x+1),check);
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
		printf("%s\n",cards);
	}

	cards[strlen(cards)-1]='\0';

}