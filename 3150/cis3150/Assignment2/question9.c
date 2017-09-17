#include "stdio.h"
#include "stdlib.h"
#include "string.h"

void sort(int *arry,int start,int g);
void print(int * arrycards,int size);

int main()
{
	int x=0;
	int i;
	int y;
	int a;
	
	printf("please input number cards: ");
	scanf("%d",&x);
	int cards[x+2];
	memset(cards,-1,sizeof(cards));
	int j =sizeof(cards)/sizeof(int);
	printf("%d\n",j );
	
	cards[1]=0;
	cards[2]=0;
	for(i=2;i<=(x+2);i++)
	{
		cards[i]=(i-2);
	}
	
	sort(cards,1,(j+1));
	return(0);
}

void sort(int *arry,int start,int g)
{

	int i;
	int temp;
	if(start==(g-1))
	{
		print(arry,g);
		
	}
	else
	{
		for (i=start;i<g;i++)
		{

			temp = arry[i];
			arry[i]=arry[start];
			arry[start]=temp;
			sort(arry,(start+1),g);
			arry[start]=arry[i];
			arry[i]=temp;
			
		}
	}

}

void print(int * arrycards,int size)
{
	int i;
	int x;
	int check=0;
	
	for(i=1;i<size;i++)
	{
		printf("%d",arrycards[i] );
	}
	printf("\n");
}