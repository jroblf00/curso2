
/**
* 
* @author Juan Carlos Robles Fernandez
*
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

#define DEFAULT_STRING_LENGTH 256
#define DEFAULT_TABLEPID_LENGTH 256 
#define EXIT "exit\0"
#define BACKGROUND "&\0"

void init(char** command);
void type_prompt();
void read_command(char** command);
void execute_command(char** command);
void remove_frist_char(char* command);
void read_path(char* command, char* path);

int main (int argc, char** argv) {

	int tablePid [DEFAULT_TABLEPID_LENGTH];	
	char** command;
	int posCommand = 0;
	
	tablePid[0] = getpid();

	do {	
		command = (char**)malloc(DEFAULT_STRING_LENGTH*sizeof(char*));
		for (posCommand=0; posCommand<DEFAULT_STRING_LENGTH; posCommand++) {

			*(command+posCommand) = (char*)malloc(DEFAULT_STRING_LENGTH*sizeof(char));

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

/**
*
* Muestra la ruta actual de trabajo con el prompt '>'
*
*/

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

/**
*
* Lee el comando que se introduce por teclado
*
* @param matriz de punteros en el que se guardan los comandos introducidos
*
*/

void read_command (char** command) {

	char buffer [DEFAULT_STRING_LENGTH];
	char* substr;
	int i = 1;
	
	fgets(buffer+1, sizeof(buffer), stdin);
	buffer[strlen(buffer)-1] = '\0';
	substr = strtok(buffer, " ");
	
	if (substr != NULL) {
	
		memcpy(command[0], substr, strlen(substr));
	
	}

	else {
	
		command[0] = NULL;
	
	}
	
	remove_frist_char(command[0]);
	
	/**
	*
	* Se separan los comandos y los argumentos en las diferentes columnas de la matriz
	*
	*/

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

/**
*
* Ejecuta el comando
*
* @param matriz con el comando a ejecutar
*
*/

void execute_command (char** command) {

	char path[DEFAULT_STRING_LENGTH];
	pid_t pidInit;
	int posCommand = 0;
	int background = 0;

	while(command[posCommand] != NULL) {

		if (strcmp(command[posCommand], BACKGROUND) == 0) {

			background = 1;
			command[posCommand] = NULL;	
		
		}

		posCommand++;

	}	

	if (strcmp(command[0], "cd\0") == 0){

		if(chdir(command[1]) == -1) {

			printf("Error con el comando '%s': %s\n", command[0], strerror(errno));
		
		}
		
	} 
	
	else if (command[0][0] != 0) {

		pidInit = fork();

		if (pidInit == -1) {
	
			perror("Error en la llamada a fork.");
		}

		else if (pidInit == 0) {

			/*if(execvp(command[0], command) == -1){

				printf("Error en comando '%s': %s\n", command[0], strerror(errno));
			
			}*/

			read_path(command[0], path);


			char* prueba2;			
			prueba2 = (char*)malloc(256*sizeof(char));
			char* const* prueba = prueba2;
			prueba2 = getenv("xcalc");

			
			if (execve("usr/bin/xcalc", command, prueba) == -1) {

				printf("Error en el comando");

			}

		}
		
		if (background == 0) {

			wait(NULL);
		
		}

		
	
	}

}

void remove_frist_char (char* command) {

	char buffer [DEFAULT_STRING_LENGTH];
	int pos = 1;
	
	//buffer[DEFAULT_STRING_LENGTH-1] = '\0';
	
	while ((command[pos] != '\n') && (pos<strlen(command))) {

		buffer[pos-1] = command[pos];
		pos++;
	
	}
	
	buffer[pos-1] = '\0';
	strcpy(command, buffer);
}

void read_path (char* command, char* path) {

	char buffer[DEFAULT_STRING_LENGTH];
	FILE *file;
	
	strcpy(buffer, "whereis ");
	strcat(buffer, command);
	strcat(buffer, " > tmp && cat tmp | awk '{print$2}' > tmp2");
	system(buffer);

	file = fopen("tmp2", "r");
	//fgets(path, DEFAULT_STRING_LENGTH, file);
	
	fscanf(file, "%s", path);


	fclose(file);
	system("rm tmp && rm tmp2");
	
}
