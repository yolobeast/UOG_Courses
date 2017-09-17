#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/*
=====================
Routine for printing out array contents
=====================
*/
void print(int n, int * a) {
    int i ; 
    for (i = 0; i <= n; i++) {
        printf("%d", a[i]); 
    }
    printf("\n"); 
}
/*
=====================
The algorithm
=====================
*/
void integerPartition(int n, int * a, int level){
    int first; 
    int i; 
    if (n < 1) return ;    
        a[level] = n;
    print(level, a);
    first = (level == 0) ? 1 : a[level-1];
    for(i = first; i <= n / 2; i++){
        a[level] = i; 
        integerPartition(n - i, a, level + 1);
    }
}
int main(int argc, char ** argv){
    int n = 3;     
    int * a = (int * ) malloc(sizeof(int) * n); 
    integerPartition (n, a, 0); 
return(0);
}