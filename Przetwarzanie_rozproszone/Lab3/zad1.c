#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#define ODCZYT 0
#define ZAPIS 1

int main()
{
    int potok[2];
    char command[10];
    char res_command[10];
    pipe(potok);

    int pid = fork();

    if (pid < 0)
        printf("Something went wrong!");
    else if(pid == 0)
    {
        printf("Process 1, reading from user\n");
        printf("Enter a string: \n");
        scanf("%s", command);
        close(potok[ODCZYT]);
        write(potok[ZAPIS], command, sizeof(command));
        close(potok[ZAPIS]);
    }
    else
    {
        waitpid(pid, NULL, 0);

        int pid2 = fork();
        if(pid2 == 0)
        {
            printf("Process 2, changing to upper case \n");
            read(potok[ODCZYT] , &res_command , sizeof(res_command));
            for (int i = 0; res_command[i]!='\0'; i++) 
            {
                res_command[i] = toupper(res_command[i]);
            }
            close(potok[ODCZYT]);
            write(potok[ZAPIS], res_command, sizeof(res_command));
            close(potok[ZAPIS]);
        }
        else
        {
            waitpid(pid2, NULL, 0);
            printf("Process 3, printing result\n");
            close(potok[ZAPIS]);
            read( potok[ODCZYT] , &res_command , sizeof(res_command));
            printf("%s \n", res_command);
            close(potok[ODCZYT]);
        }
    }
    return 0;
}