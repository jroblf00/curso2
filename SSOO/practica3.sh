#!/bin/bash



if test $# -eq 0
then
	dir='pwd'
fi

if test $# -eq 1
then
	dir=$1
fi

if test $# -gt 1
then
	echo Error en los parametros.
	exit 1
fi

while true
do

echo Por favor, elija una opcion.
echo 1\)Numero de archivos.
echo 2\)Numero de subdirectorios.
echo 3\)Fichero mas grande.
echo 4\)Fichero mas pequeño.
echo 5\)Espacio total ocupado.
echo 6\)Numero de ficheros con permiso de lectura para el usuario que ejecuta el script.
echo 7\)Numero de ficheros con permiso de escritura para el usuario que ejecuta el script.
echo 8\)Numero de ficheros con permiso de ejecucion para el usuario que ejecuta el script.
echo 9\)Ficheros con permiso de ejecucion para todos los usuarios.
echo 10\)Salir.

	read opcion
	case $opcion in
		1) echo
		   echo El numero de archivos es: `ls -l | grep ^- | wc -l`
		   echo
		   ;;

		2) echo
		   echo El numero de subdirectorios es: `ls -lR | grep ^d | wc -l`
		   echo
		   ;;

		3) echo
		   echo El fichero mas grande es: `ls -l --sort=size | grep ^- | awk '{print $9}' | head -1`
		   echo
		   ;;

		4) echo
		   echo El fichero menos grande es: `ls -l --sort=size -r | grep ^- | awk '{print $9}' | head -1`
	           echo
		   ;;

		5) echo
		   echo El espacio total ocupado es: `du -sh | awk '{print $1}'`
		   echo
		   ;;

		6) echo
		   echo El numero de ficheros con permiso de lectura es: `ls -l | grep $USER | grep ^-r | awk '{print $9}' > tmp.txt && 
		   ls -l | grep \`cat /etc/group | grep $GROUPS | awk -F ":" '{print $1}'\` | egrep ^-.{3}r | awk '{print $9}' >> tmp.txt &&
	 	   ls -l | egrep ^-.{6}r | awk '{print $9}'>> tmp.txt && cat tmp.txt | sort -u | sed '/tmp.txt/d' | wc -l`
		   echo
		   ;;

		7) echo
		   echo El numero de ficheros con permiso de escritura es: `ls -l | grep $USER | grep ^-.w | awk '{print $9}' > tmp.txt && 
		   ls -l | grep \`cat /etc/group | grep $GROUPS | awk -F ":" '{print $1}'\` | egrep ^-.{4}w | awk '{print $9}' >> tmp.txt &&
		   ls -l | egrep ^-.{7}w | awk '{print $9}'>> tmp.txt && cat tmp.txt | sort -u | sed '/tmp.txt/d' | wc -l`
		   echo
		   ;;

	   	8) echo
		   echo El numero de ficheros con persimo de ejecucion es: `ls -l | grep $USER | grep ^-..x | awk '{print $9}' > tmp.txt && 
		   ls -l | grep \`cat /etc/group | grep $GROUPS | awk -F ":" '{print $1}'\` | egrep ^-.{5}x | awk '{print $9}' >> tmp.txt &&
		   ls -l | egrep ^-.{8}x | awk '{print $9}'>> tmp.txt && cat tmp.txt | sort -u | sed '/tmp.txt/d' | wc -l`
		   echo
		   ;;

		9) echo
		   echo El numero de ficheros de ejecucion para cualquier usuario es: `ls -l | grep ^-..x | awk '{print $9}' > tmp.txt && 
		   ls -l | egrep ^-.{5}x | awk '{print $9}' >> tmp.txt && ls -l | egrep ^-.{8}x | awk '{print $9}'>> tmp.txt && 
		   cat tmp.txt | sort -u | sed '/tmp.txt/d' | wc -l` 
		   echo
		   ;;

		10) exit 0;;
	esac
done

