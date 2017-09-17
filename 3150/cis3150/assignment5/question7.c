#include "stdio.h"
#include "stdlib.h"
#include "string.h"

/*this is the global functions*/
void sort(char *cards,int x, int y);
int setarray(int * arr,int x);
int h=0;
int l=1;

int main()
{
	
	int x;
	int i;
	
	printf("please input number cards: ");
	scanf("%d",&x);
	int check[x];
	char *numberofcards=malloc((x)*sizeof(char));
    sort(numberofcards,x,x);
    printf("the total is %d\n",h);
	return(0);
}

/*this function will premute the cards*/
void sort(char *cards,int x, int y){

	int i;
	int j;
	int set[3];
	char c[x];
	int z=0;
	int e=0;
	int g=1;


	for(i=1;i<=3;i++){
		if(y!=0){
			y=y-1;
			sprintf(c,"%d",i);
			strncat(cards,c,1);
			sort(cards,x,y);
			y=y+1;
		}
	}

	if(strlen(cards)==x)
	{

		if((cards[0]-'0')==1)
		{
			for(j=0;j<x;j++)
			{
				if(cards[j]>(l+1))
				{
					g=0;
				}

				if(cards[j]> l)
				{
					l=cards[j];
				}
			}
			l=1;

			if(g==0)
			{
				printf("%s\n",cards);
			}
		}
	}
	cards[strlen(cards)-1]='\0';
}

