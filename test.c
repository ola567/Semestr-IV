#include <stdio.h>

void print_int(int);
void print_float(float);

int main()
{
    int a=4;
    int b=-2;
    float c=4.0;

    int suma = add(a, b);
    printf("%d \n",suma);

    int roznica=sub(a, b);
    printf("%d \n", roznica);

    print_int(a);
    printf("\n");
    print_float(c);
    printf("\n");

    return 0;
}