#!/bin/bash

function sumar(){

	result=`expr $1 + $2`
	echo $result
}

function restar(){

	result=`expr $1 - $2`
	echo $result
}

function multiplicar(){

	result=`expr $1 \* $2`
	echo $result
}

function dividir(){

	result=`expr $1 / $2`
	echo $result
}

if test $# -eq 2
then
	echo elige una opcion:
	echo 1 sumar
	echo 2 restar
	echo 3 multiplicar
	echo 4 dividir
	read opcion
	case $opcion in
		1) result=`sumar $1 $2`
		   echo $result;;
		2) result=`restar $1 $2`
		   echo $result;;
		3) result=`multiplicar $1 $2`
		   echo $result;;
		4) resutl=`dividir $1 $2`
	  	   echo $result;;
	esac
fi 

