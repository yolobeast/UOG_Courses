#include "stdio.h"
#include "stdlib.h"
#include "string.h"

int n;
int config[223];
int tot=0;
int x=1;

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
                    printf("%d",config[i] );
                }
                 printf("\n");
                 tot++;
            }
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
    printf("Total %d \n",tot);
}