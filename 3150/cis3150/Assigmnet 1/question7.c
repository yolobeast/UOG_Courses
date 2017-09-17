#include "stdio.h"
#include "stdlib.h"
#include "string.h"


int main(){
	
	char getsting;
	char letter;
	int i =0;
	int x=1;
	int y= 0;

	/*its a main loop that goes around asking the chcratrs*/
	do{

		printf("please input a string : ");
		/*asking for the input*/
		scanf("\n%c",&getsting);
		letter = getsting;

			
			/*this is to check the states that the chcrater takesin*/
			if(letter == 'a' && x == 1 ){
				x++;
			}
			else if(letter == 'b' && x==1){
				x=1;
			}
			else if(letter == 'b' && x==2){
				x++;
			}
			else if(letter == 'a' && x==2){
				x=2;
			}
			else if(letter == 'a' && x==3){
				x++;
			}
			else if(letter == 'b' && x==3){
				x=1;
			}
			else if(letter == 'b'&& x == 4){
				x++;
			}
			else if(letter == 'a'&& x == 4){
				x=2;
			}
			else if((letter == 'a' || letter == 'b')&& x ==5){
				x=5;
			}
			else{
				y=1;
			}

			/*this will let you know what state is it*/
			printf("this is the final state of : %d \n",x);
	
	/*only stops when the chcrater is not a or b*/
	}while(y!=1);
	
}