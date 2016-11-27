#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define DEFAULT_STRING_LENGTH 256
#define EXIT "exit\n"

void type_prompt();
void read_command(char** command);
void execute_command(char** command);

int main (int argc, char** argv) {

	char** command;

	int i = 0;

	command = (char**)malloc(DEFAULT_STRING_LENGTH * sizeof(char*));

	for(i = 0; i < DEFAULT_STRING_LENGTH; i++){

		*(command + i) = (char *)malloc(DEFAULT_STRING_LENGTH);	
	
	}

	type_prompt();
	read_command(command);
	execute_command(command);

/*
	do {

		type_prompt();
		//fgets(command, DEFAULT_STRING_LENGTH, stdin);
		
		read_command();

	}while (1);
*/
	return 0;

}

void type_prompt () {

	char* ptr;
	char buffer [DEFAULT_STRING_LENGTH];

	ptr = getcwd(buffer, DEFAULT_STRING_LENGTH);
	strcat(ptr, ">");

	printf("%s", ptr);

}

void read_command (char** command) {

	char buffer [DEFAULT_STRING_LENGTH];
	char* substr;
	int i = 1;

	fgets(buffer, DEFAULT_STRING_LENGTH, stdin);
	buffer[strlen(buffer)-1] = '\0';

	substr = strtok(buffer, " ");

	if (substr != NULL) {
	
		memcpy(command[0], substr, strlen(substr));
	
	}

	else {
	
		command[0] = NULL;
	
	}
	
	while (command[i-1] != NULL) {
	
		substr = strtok(NULL, " ");

		if (substr != NULL) {
		
			memcpy(command[i], substr, strlen(substr));
		
		}

		else {
		
			command[i] = NULL;
		
		}

		i++;

	}

	command[i-1] = NULL;

}

void execute_command (char** command) {

	pid_t p;

	p = fork();

	if (p == -1) {
	
		perror("Error en la llamada a fork.");
	}

	else if (p == 0) {

		execvp(command[0], command);

	}

	else 
		printf("PID1:%d", p);

	
	printf("PID3:%d",wait(NULL));
	printf("PID2:%d", getpid());

}
