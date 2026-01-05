#!/usr/bin/bash

echo "Running script..."
echo "Listing directories..."
ls -l

echo "Building clients..."
for file in `ls "$PWD"`
do
  if [ -d "$file" ]
  then
    if [[ -e "$file/pom.xml" && -e "$file/manifest.yml" ]]
    then
      cd "$file"; echo "** this is a service: $file"; 
      cd ..;
    elif [ -e "$file/pom.xml" ]
    then
      cd "$file"; echo "This is a client... $file";
      mvn install; cd ..;
    fi
  else  
    echo "it is a file '$file'"
  fi
done

echo "Building services..."
for file in `ls "$PWD"`
do
  if [ -d "$file" ]
  then
    if [[ -e "$file/pom.xml" && -e "$file/manifest.yml" ]]
    then
      cd "$file"; echo "** this is a service: $file"; 
      targetFile=`grep '<artifactId>' pom.xml | sed -e 's/<\/*artifactId>//g' | sed -e 's/[[:blank:]]//g'  | head -1`;
      mvn clean install;
      echo "cp target/*.jar ../output/$targetFile.jar"; cp target/*.jar ../output/$targetFile.jar;
      cd ..;
    fi
  else  
    echo "it is a file '$file'"
  fi
done


ls -l output