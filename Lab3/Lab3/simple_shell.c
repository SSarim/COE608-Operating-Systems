
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <ctype.h>

#define MAX_LENGTH 100

int main() {
    char line[MAX_LENGTH];
    char *argv[MAX_LENGTH];
    int background;

    while (1) {
        printf("Your command> ");
        fflush(stdout);

        // Read the command from stdin
        int n = 0;
        int ch;
        background = 0;
        while ((ch = getchar()) != '\n' && n < MAX_LENGTH - 1) {
            if (ch == '&') {
                background = 1;
                break;
            }
            line[n++] = (char)ch;
        }
        line[n] = '\0'; // Null-terminate the string

        // Parse the command
        char *token = strtok(line, " ");
        int i = 0;
        while (token != NULL) {
            argv[i++] = token;
            token = strtok(NULL, " ");
        }
        argv[i] = NULL; // Null-terminate the array

        if (argv[0] == NULL) {
            continue; // No command entered
        }

        pid_t pid = fork();
        if (pid == 0) {
            // Child process
            if (execvp(argv[0], argv) == -1) {
                perror("execvp");
            }
            exit(EXIT_FAILURE);
        } else if (pid > 0) {
            // Parent process
            if (!background) {
                wait(NULL); // Wait for child process to finish
            }
        } else {
            perror("fork");
        }
    }
    return 0;
}
