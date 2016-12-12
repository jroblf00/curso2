#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

#define DEFAULT_STRING_LENGTH 256
#define EXIT "exit\0"

void init(char** command);
void type_prompt();
void read_command(char** command);
void execute_command(char** command);

int main (int argc, char** argv) {

	int tablaPid [256];	
	char** command;
	int i = 0;
	
	tablaPid[0] = getpid();

	do {	
	
		command = (char**)malloc(DEFAULT_STRING_LENGTH*sizeof(char*));
		for (i=0; i<DEFAULT_STRING_LENGTH; i++) {

			*(command+i) = (char*)malloc(DEFAULT_STRING_LENGTH*sizeof(char));

		}
		
		type_prompt();
		read_command(command);
		
		if (strcmp(command[0], EXIT) != 0) {
			
			execute_command(command);
		
		}	

	} while (strcmp(command[0], EXIT) != 0);
	
	free(command);

	return 0;

}

void init (char** command) {

	int i = 0;
	
	command = (char**)malloc(DEFAULT_STRING_LENGTH * sizeof(char));

	for(i=0; i<DEFAULT_STRING_LENGTH; i++){

		*(command+i) = (char*)malloc(DEFAULT_STRING_LENGTH * sizeof(char));

	}

}

void type_prompt () {

	char* ptr;
	char buffer [DEFAULT_STRING_LENGTH];

	ptr = getcwd(buffer, DEFAULT_STRING_LENGTH);
	if (ptr == buffer) {
	
		strcat(ptr, ">");
		printf("%s", ptr);
	
	}
	
	else {

		printf("Error con el comando getcwd: %s\n", strerror(errno));

	}

}

void read_command (char** command) {

	char buffer [DEFAULT_STRING_LENGTH];
	char* substr;
	int i = 1;

	fgets(buffer, sizeof(buffer), stdin);
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


	if (strcmp(command[0], "cd\0") == 0){

		if(chdir(command[1]) == -1) {

			printf("Error con el comando '%s': %s\n", command[0], strerror(errno));
		
		}
		
	} 
	
	else {

		p = fork();

		if (p == -1) {
	
			perror("Error en la llamada a fork.");
		}

		else if (p == 0) {

			if(execvp(command[0], command) == -1){

				printf("Error en comando '%s': %s\n", command[0], strerror(errno));
			
			}

		}

		wait(NULL);
	}

}
