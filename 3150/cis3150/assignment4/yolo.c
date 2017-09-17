#include "stdio.h"
#include "stdlib.h"
#include "string.h"
int n;
int config[223];
int tot=0;
int x=1;
int numi[10]={59,25,27,3,2,15,9,30,21,19};
int checker = 0;
int totalsums=0;
int firstsum=0;
int secondsum=0;
int thirdsum=0;

void setthatarray(int y)
{
    if(config[i]==1)
    {
        firstsum = firstsum + numi[i-1];
    }
    else if(config[i]==2)
    {
        secondsum = secondsum + numi[i-1];
    }
    else
    {
        thirdsum = thirdsum + numi[i-1];
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

            if(c==1 && config[1]==1)
            {
                for (i=1; i<n+1; i++)
                 {
                    //printf("%d",config[i] );
                    setthatarray(int y);
                    //printf("firs sum : %d second sum: %d third sum : %d\n",firstsum,secondsum,thirdsum ); 
                }
                 //printf("\n");
                 tot++;
            }
        if(firstsum == secondsum && secondsum == thirdsum && thirdsum == firstsum)
        {
             if(c==1 && config[1]==1)
            {
                for (i=1; i<n+1; i++)
                {
                    printf("%d",config[i] );
                }
                 printf("\n");
                 totalsums++;
            }
            //printf("firs sum : %d second sum: %d third sum : %d\n",firstsum,secondsum,thirdsum );
           checker =1;
        }
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
        printf("Number of worked soulution are %d\n",totalsums);
    }
    printf("Total %d \n",tot);
}