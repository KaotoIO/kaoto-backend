#!/bin/bash

git reset --hard 
git pull

echo "Do you want to download the latest zips of steps? (y/n)"
echo "Note: If you want to change the version of Apache Camel, "
echo "you have to generate it independently from this script."
read -r download

if [ "$download" = "y" ];
then

  echo "Downloading camel component metadata."
  wget https://github.com/KaotoIO/camel-component-metadata/archive/refs/heads/main.zip
  mv "main.zip" "api/src/main/resources/camel-component-metadata.zip"
  git add -f "api/src/main/resources/camel-component-metadata.zip"

  echo "Downloading kamelets."
  echo "Enter kamelets version:"
  echo "Example: 0.9.5"
  read -r versionKamelets

  path=$(pwd)
  git rm $path"/api/src/main/resources/camel-kamelets-*.jar"
  cd /tmp
  mkdir kamelets
  cd kamelets
  wget "https://github.com/apache/camel-kamelets/archive/refs/tags/v"$versionKamelets".zip"
  unzip *zip
  cd "camel-kamelets-"$versionKamelets"/kamelets/"
  zip "camel-kamelets-"$versionKamelets".jar" *
  mv "camel-kamelets-"$versionKamelets".jar" $path"/api/src/main/resources/"
  cd $path
  git add -f "api/src/main/resources/camel-kamelets-"$versionKamelets".jar"
  sed -i 's/kamelets-.*/kamelets-'$versionKamelets'.jar$"/g' api/src/main/resources/resources-config.json
  sed -i 's/kamelets-.*/kamelets-'$versionKamelets'.jar$"/g' api/src/main/resources/application.yaml
  git add api/src/main/resources/resources-config.json
  git add api/src/main/resources/application.yaml

  git commit -m "chore(resources): Updating zip files with step catalog components."
fi

echo "Enter version of Kaoto to release:"
echo "Example: 1.0.0"
read -r version

mvn versions:set -DnewVersion=$version
git add .
git commit -m "Updating to version $version"
git tag v$version

echo "Now we are going to test the new version."

mvn install

echo "Enter new development version:"
echo "(SNAPSHOT will be added automatically)"
echo "Example: 1.0.1"
read -r version2

mvn versions:set -DnewVersion="$version2"-SNAPSHOT
git add .
git commit -m "chore(release): Updating to version $version2-SNAPSHOT"

echo ""
echo "Check the git log and if you like what you see, just do"
echo "'git push' and 'git push --tags'."
echo "This will trigger the release creation."
echo ""
echo "Then go to https://github.com/KaotoIO/kaoto-backend/releases/ to review"
echo "the text of the release and publish it. "
echo ""
echo "Congratulations! Your job here is done."
