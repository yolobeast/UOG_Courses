#include <stdio.h>

int num_moves[16] = {0,2,2,2,4,4,4,4,4,4,6,6,6,6,6,6};

int moves[16][6] = {
	{},
	{1,2},
	{3,4},
	{5,6},
	{3,4,5,6},
	{3,4,5,6},
	{1,2,5,6},
	{1,2,5,6},
	{1,2,3,4},
	{1,2,3,4},
	{1,2,3,4,5,6},
	{1,2,3,4,5,6},
	{1,2,3,4,5,6},
	{1,2,3,4,5,6},
	{1,2,3,4,5,6},
	{1,2,3,4,5,6},
};
int algo[16];
int total_algos=0;

void GenAlgos(int t)
{
	int i;

	if(t==16)
	{
		total_algos++;
		// for(i=1;i<=15;i++)
		// {
		// 	printf("%d\n", algo[i]);
		// }
		// printf("\n");
	}
	else
	{
		for(i=0;i<num_moves[t];i++)
		{
			algo[t]=moves[t][i];
			GenAlgos(t+1);
		}
	}
}

int main()
{
	GenAlgos(1);
	printf("total algos = %d\n",total_algos);
}