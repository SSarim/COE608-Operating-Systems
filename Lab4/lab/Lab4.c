/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Lab4.c
 * Author: sshahwar
 *
 * Created on February 10, 2024, 6:15 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void executeCommands(char* parent_command, char* child_command);

int main(int argc, char** argv) {
    char input_line[200];

    //  user input
    printf("Your Command> ");
    fgets(input_line, sizeof(input_line), stdin);

    char* pipe_position = strchr(input_line, '|');
    if (pipe_position == NULL) {
        fprintf(stderr, "No Pipe Found\n");
        exit(1);
    }

    *pipe_position = '\0';  // Null-terminate 
    pipe_position++;        // Move past the '|'


    while (*pipe_position == ' ') {
        pipe_position++;
    }

  
    char* end = input_line + strlen(input_line) - 1;
    while (*end == ' ' || *end == '\n') {
        *end = '\0';
        end--;
    }

    executeCommands(input_line, pipe_position);

    return 0;
}

void executeCommands(char* parent_command, char* child_command) {
    FILE *pipe_fp;

   
    char command[200];
    snprintf(command, sizeof(command), "%s | %s", parent_command, child_command);

    if ((pipe_fp = popen(command, "r")) == NULL) {
        perror("Error in popen");
        exit(1);
    }

    // Read&print the output
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), pipe_fp) != NULL) {
        printf("%s", buffer);
    }

    // Close the pipe
    if (pclose(pipe_fp) == -1) {
        perror("Error in pclose");
        exit(1);
    }
}