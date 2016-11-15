#!/bin/bash

echo Por favor, introduce un numero entre el 1 y el 2
read input

while test $input -lt 1 -o $input -gt 2
do
	
	case $input in
	
		1) echo has introducido el numero $input;;
		2) echo has introducido el numero $input;;
		*) echo introduce un numero entre 1 y 2;;
	esac
read input 

done
