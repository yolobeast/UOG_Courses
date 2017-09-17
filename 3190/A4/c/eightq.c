/*=========================
-- Name : Sathi.Ranganathan
-- Student # : 0757619
-- Date : 05/04/2016
--=========================
-- How to run
-- 1. type gnatmake -Wall eightq.c
-- 1. ./a.out
-- 3. QueenC.txt will created
-- 4. In the file there will be 92 solutions
-------------------------------------------------------------*/
#include <stdio.h>

/* global varialbe declearation*/
int checkboolone[8];
int checkbooltwo[16];
int checkboolthree[14];
int x[8];
int static s;

/*function prototype declearation*/
void try(int i);
void print();

int main()
{
    int i;
	
	/*removing the old file*/
	remove("QueenC.txt");
	s=0;
	
	/*initializing the global variables*/
	for(i=1;i<9;i++)
	{
		checkboolone[i] = 1;
	}
	for(i=2;i<17;i++)
	{
		checkbooltwo[i] =1;
	}
	for(i=1;i<16;i++)
	{
		checkboolthree[i]=1;
	}
	/*calling the recursive function*/
	try(1);
	/*letting the user know what is happeing */
	printf("QueenC.txt has been created with slution to 8 queens\n");

    return 0;
}

/* recusive function*/
void try (i)
{
	int j;
	
	for (j=1;j<9;j++)
	{
		/* checking to make sure ther all the sam evalue*/
		if(checkboolone[j] == 1 && checkbooltwo[i+j] == 1 && checkboolthree[i-j+8] == 1)
		{
			x[i] = j;
			checkboolone[j] = 0;
			checkbooltwo[i+j]=0;
			checkboolthree[i-j+8]=0;
			if(i<8)
			{
				/*calling the recursive function*/
				//printf("%d",i);
				try(i+1);
			}
			else
			{
				/*calling print function*/
				//printf("%d",i);
				print();
			}
			
			/*resting the values*/
			checkboolone[j] = 1;
			checkbooltwo[i+j]=1;
			checkboolthree[i-j+8]=1;
		}
	}
}

/*print function*/
void print()
{
	int i;
	int g;
	FILE *fp;
	/*suming howmany time this function is called*/
	s++;
	/* opeing the file QueenC.txt*/
	fp = fopen("QueenC.txt", "a");
	
	/* writing to the file*/
	fprintf(fp,"\n");
	fprintf(fp,"Solution number: %d",s);
	fprintf(fp,"\n");
	for (i=1;i<9;i++)
	{
		for(g=1;g<9;g++)
		{
			
			if(x[i]==g)
			{
				fprintf(fp,"Q");
			}
			else
			{
				fprintf(fp,".");
			}
		}
		fprintf(fp,"\n");
	}
	fclose(fp);
	
}