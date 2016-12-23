
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
#include <sys/stat.h>
#include <errno.h>
#include <signal.h>
#include <fcntl.h>

#define DEFAULT_STRING_LENGTH 256
#define EXIT "exit\0"
/**
 * BACKGROUND caracter con el que se manda la orden que se ejecute en
 * segundo plano. Para activarlo se tiene que dejar un espacio despues del 
 * ultimo parametro y poner el caracter indicado.
 */
#define BACKGROUND "&\0"
#define PROMPT "#\0"
#define REDIRECT ">"

void type_prompt();
void read_command(char** command);
void execute_command(char** command);
void remove_frist_char(char* command);
void read_path(char* command, char* path);
void show_process();
void redirect_output(char** command);

int main (int argc, char** argv) {

	char** command;
	int posCommand = 0;

	signal (SIGINT, show_process);	

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
	
		strcat(ptr, PROMPT);
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
* Ejecuta el comando introducido por teclado
*
* @param matriz con el comando a ejecutar
*
*/

void execute_command (char** command) {

	pid_t pidInit;
	int posCommand = 0;
	int background = 0;
	int redirect = 0;

	while(command[posCommand] != NULL) {

		if (strcmp(command[posCommand], BACKGROUND) == 0) {

			background = 1;
			command[posCommand] = NULL;	
		
		}

		else if (strcmp(command[posCommand], REDIRECT) == 0) {

			redirect = 1;
			redirect_output(command);

		}

		posCommand++;

	}	

	if (strcmp(command[0], "cd\0") == 0){

		if(chdir(command[1]) == -1) {

			printf("Error con el comando '%s': %s\n", command[0], strerror(errno));
		
		}
		
	} 
	
	else if ((command[0][0] != 0) && (redirect == 0) ) {

		pidInit = fork();

		if (pidInit == -1) {

	
			printf("Error en la llamada a fork: %s", strerror(errno));
		}

		else if (pidInit == 0) {

			if(execvp(command[0], command) == -1){

				printf("Error en comando '%s': %s\n", command[0], strerror(errno));
			
			}
		}

		else {

				

		}
		
		if (background == 0) {

			wait(NULL);
		
		}

		
	
	}

}

void remove_frist_char (char* command) {

	char buffer [DEFAULT_STRING_LENGTH];
	int pos = 1;
	
	while ((command[pos] != '\n') && (pos<strlen(command))) {

		buffer[pos-1] = command[pos];
		pos++;
	
	}
	
	buffer[pos-1] = '\0';
	strcpy(command, buffer);
}

/**
 *
 * Lee la ruta en la que esta alojado el programa que se va a ejecutar
 *
 * @param *command comando que se va a ejecutar
 * @param *path ruta donde esta alojado el programa a ejecutar
 *
 */
 
void read_path (char* command, char* path) {

	char buffer[DEFAULT_STRING_LENGTH];
	FILE *file;
	
	strcpy(buffer, "whereis ");
	strcat(buffer, command);
	strcat(buffer, " > tmp && cat tmp | awk '{print$2}' > tmp2");
	system(buffer);

	file = fopen("tmp2", "r");
	fscanf(file, "%s", path);


	fclose(file);
	system("rm tmp && rm tmp2");
	
}

/**
 *
 * Muesta los procesos abiertos
 *
 */

void show_process () {
	
	signal(SIGINT, SIG_IGN);

	char buffer[DEFAULT_STRING_LENGTH];
	char pid_char[4];
	int pid_int = getpid();

	printf("\n\nPulse intro para finalizar\n\n");
	printf("PID   WCHAN   CMD\n");
	
	sprintf(pid_char,"%d" , pid_int);
	strcpy(buffer, "ps -l | grep ");
	strcat(buffer, pid_char);
	strcat(buffer, " | awk '{print$4, $11, $14}'");
	
	system(buffer);

	signal(SIGINT, show_process);

}

void redirect_output(char** command) {

	int i = 0;
	char filename[DEFAULT_STRING_LENGTH];

	while (strcmp(command[i], REDIRECT) != 0) {

		i++;

	}

	strcpy(filename, command[++i]);

	command[i] = NULL;
	command[--i] = NULL;

	int  file = open(filename, O_WRONLY | O_CREAT | O_TRUNC, 0600);

	if (file < 0)  
		return  1;

	if (dup2(file ,STDOUT_FILENO) < 0)  
		return  1;

	execute_command(command);

	close(file);

	if (dup2(0 ,1) < 0)  
		return  1;

}