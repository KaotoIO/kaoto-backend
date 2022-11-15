#!/bin/bash

git reset --hard 
git pull

echo "Enter new version:"
echo "Example: 1.0.0"
read -r version

mvn versions:set -DnewVersion=$version
git add .
git commit -m "Updating to version $version"
git tag v$version

mvn install

echo "Did the tests pass? Do you want to push? (y/n)" 
read -r shouldPush

if [ "$shouldPush" = "y" ];
then

  echo "Enter new version:"
  echo "Example: 1.0.1"
  read -r version2

  mvn versions:set -DnewVersion="$version2"-SNAPSHOT
  git add .
  git commit -m "Updating to version $version2-SNAPSHOT"
  git push
  
  echo "Now go to https://github.com/KaotoIO/kaoto-backend/releases/ to review the text of the release and publish it. "
  echo "Congratulations! Your job here is done."
  
fi
