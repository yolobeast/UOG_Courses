#include "stdio.h"
#include "stdlib.h"
#include "string.h"

int n;
int config[223];
int tot=0;
int x=1;
int numi[9]={1,2,3,4,5,14,16,17,31};
int numii[9]={1,2,3,4,5,6,7,8,9};
int numiii[10]={1,2,3,4,5,6,7,8,9,10};
int numiv[14]={1,1,2,2,3,3,4,4,5,5,6,6,7,7};
int numv[10]={25,27,3,2,15,9,30,21,19,56};
int numvi[10]={59,25,27,3,2,15,9,30,21,19};
int checker = 0;
int totalsums=0;
int firstsum=0;
int secondsum=0;
int thirdsum=0;

void sum(int y)
{
    if(config[y]==1)
    {
        firstsum = firstsum + numii[y-1];
    }
    else if(config[y]==2)
    {
        secondsum = secondsum + numii[y-1];
    }
    else
    {
        thirdsum = thirdsum + numii[y-1];
    }
}

void checksum(int g)
{
    int i;
     if(firstsum == secondsum && secondsum == thirdsum && thirdsum == firstsum)
        {
             if(g==1 && config[1]==1)
            {
                for (i=1; i<n+1; i++)
                {
                    printf("%d",config[i] );
                }
                 printf("\n");
                 if(firstsum == 0 && secondsum == 0 && thirdsum ==0)
                 {
                    tot++;
                 }
                 else
                 {
                    totalsums++;
                 }
            }
           checker =1;
        }
}

void sumingfunction(int h)
{
    int i;
    if(h==1 && config[1]==1)
    {
        for (i=1; i<n+1; i++)
        {
            sum(i);
        }
    }

}

void GenConfigurations(char*cards,int t) {
    int i;
    int c=1;

    if (t > n) {
        for(i=1;i<=n;i++)
        {
            if(config[i]>(x+1))
            {
                c=0;
            }

            if(config[i] > x)
            {
                x=config[i];
            }
        }
        x=1;
        //sumingfunction(c);
        checksum(c);
        firstsum =0;
        secondsum =0;
        thirdsum =0; 
    }
    else {
        for (i=1; i<4; i++) {
            config[t] = i;
            GenConfigurations(cards,t+1);
        }
    }
}

int main() {
int i;
    
    printf("Enter n: "); scanf("%d", &n);
    char *numcards=malloc((n)*sizeof(char));
    GenConfigurations(numcards,1);
    if(checker ==1)
    {
        printf("Number of worked soulution are : %d\n",totalsums);
    }
    printf("Total : %d \n",tot);
}