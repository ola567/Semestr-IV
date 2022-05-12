#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<errno.h>
#include<fcntl.h>
#define ODCZYT 0
#define ZAPIS 1

int main()
{
    char res_command[10];

    int fd = open("myfifo", O_RDONLY);
    read(fd, &res_command , sizeof(res_command));
    printf("%s \n", res_command);
    close(fd);
    
    return 0;
}