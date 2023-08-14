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
    char command[10];
    char res_command[10];
    if(mkfifo("myfifo", 0777) == -1)
    {
        if(errno != EEXIST)
        {
            printf("Could not create fifo file\n");
            return 1;
        }
    }

    printf("Enter a string: \n");
    scanf("%s", command);
    int fd = open("myfifo", O_WRONLY);
    write(fd, command, sizeof(command));
    close(fd);
    
    return 0;
}