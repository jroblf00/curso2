#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

int main (){

	printf("Arrancando...\n");
	
	int estado;
	pid_t mipid, hijoMuerto;
	mipid = fork();
	
	int a = 10;
	
	if(mipid == -1){

		perror("Error en la llamada a fork()\n");

	}
	
	else if(mipid == 0){

		printf("Hijo: mi pid es %d\n", getpid());
		a = 5;
		sleep(5);
		
		exit(28);
	}

	else {
		
		hijoMuerto = wait(&estado);
		printf("Padre: mi pid es %d\n", getpid());
		printf("Padre %d: se me ha muerto el hijo %d\n", getpid(), hijoMuerto);

	}
	printf("%d:El valor de estado es %d\n",getpid() , estado);
	printf("El valor de estado de verdad es %d\n", WEXITSTATUS(estado));
	printf("Finalizando...\n");

	return 0;
}
