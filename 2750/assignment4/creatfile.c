#include <stdio.h> 
#include <stdlib.h>
#include <string.h>


int main(int arc,char ** argv)
{
  
	
	char * folder;
	char * nameoffile;
	FILE * outfille;
	char file[4096];
	
	nameoffile = argv[1];
	folder = argv[2];
	
	sprintf(file, "%s/%s.java", folder,nameoffile);
	outfille = fopen(file, "w");
	fprintf(outfille," ");
	fclose(outfille);
	

  return(0);
}