
#include <stdio.h>
#include <string.h>

int n;

int num[20];

int config[22];

int total = 0;

void printthisshizzle(char * input)
{
	printf("%s\n",input);
}

void GenStartingConfig(int t) {

int i;
char str[100];
char str2[12];

memset(&str2[0],'\0',sizeof(str2));
if (t > n+2) {

for (i=1; i<=n+2; i++){
	//printf("%d", config[i]);
	sprintf(str,"%d",config[i]);
	strcat(str2,str);
	if(i==(n+2))
	{	
		//printf("%s\n", str2);
		printthisshizzle(str2);
		//memset(&str[0],'\0',sizeof(str));
		memset(&str2[0],'\0',sizeof(str2));
		//continue;
	}
	
	
	
	}
	total++;
	
	//printf("\n");
}

else
{

for(i=0; i<=n; i++) {

if(num[i] > 0) {
num[i]--;

config[t] = i;

GenStartingConfig(t+1);

num[i]++;

}

}

}

}


int main() {

int i;


printf("Enter n:"); scanf("%d", &n);

num[0] = 2;

for(i=1; i<=n; i++) num[i] = 1;

GenStartingConfig(1);

printf("Total configs=%d\n", total);

}

