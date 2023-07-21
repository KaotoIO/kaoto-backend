#!/bin/bash

echo "MAKE SURE YOU ARE ON LATEST MAIN"

echo "Enter version of Kaoto to release:"
echo "Example: 1.0.0"
read -r version

git checkout -b release-$version

echo "Do you want to push the generated branches to your fork? (y/n)"
echo "Note: This will make it easier to create the pull requests later."
read -r gitPush

if [ "$gitPush" = "y" ];
then
  echo "Enter the local name of the fork (git remote -v can help you here)"
  echo "Example: upstream"
  read -r gitFork
  
  echo "Enter the url of the fork (git remote -v can help you here)"
  echo "Example: https://github.com/YOUR_NAME/kaoto-backend"
  read -r gitUrl
fi

echo "Do you want to download the latest zips of steps? (y/n)"
echo "Note: This includes Apache Camel components, kamelets, and kaoto bridged camel components."
read -r download

if [ "$download" = "y" ];
then
  path=$(pwd)
  git rm $path"/api/src/main/resources/camel-*.zip"

  echo "Downloading Kaoto bridged Camel components (EIP)."
  wget https://github.com/KaotoIO/camel-component-metadata/archive/refs/heads/main.zip
  mv "main.zip" "api/src/main/resources/camel-component-metadata.zip"
  git add -f "api/src/main/resources/camel-component-metadata.zip"
  
  echo "Downloading Kaoto view definitions."
  wget https://github.com/KaotoIO/kaoto-viewdefinition-catalog/archive/refs/heads/main.zip
  mv "main.zip" "api/src/main/resources/view-definition.zip"
  git add -f "api/src/main/resources/view-definition.zip"
  
  echo "Downloading camel connectors."
  echo "Enter camel version to use:"
  echo "Example: 3.18.4"
  read -r versionCamel
  cd /tmp
  mkdir camel-connectors
  cd camel-connectors
  wget "https://github.com/apache/camel/archive/refs/tags/camel-"$versionCamel".zip"
  unzip *zip
  cp  "camel-camel-"$versionCamel"/dsl/camel-yaml-dsl/camel-yaml-dsl/src/generated/resources/schema/camel-yaml-dsl.json "$path"/camel-support/src/main/resources/io/kaoto/backend/camel/service/deployment/generator/camelroute/camel-yaml-dsl.json"
  cd "camel-camel-"$versionCamel"/catalog/camel-catalog/src/generated/resources/org/apache/camel/catalog/components/"
  zip "camel-connectors-"$versionCamel".zip" *.json
  mv "camel-connectors-"$versionCamel".zip" $path"/api/src/main/resources/"
  cd $path
  git add -f "api/src/main/resources/camel-connectors-"$versionCamel".zip"
  sed -i 's/camel-connectors-.*/camel-connectors-'$versionCamel'.zip$"/g' api/src/main/resources/resources-config.json
  sed -i 's/camel-connectors-.*/camel-connectors-'$versionCamel'.zip"/g' api/src/main/resources/application.yaml
  sed -i 's/camel-connectors-.*/camel-connectors-'$versionCamel'.zip"/g' api/src/test/resources/application.yaml
  
  echo "Downloading kamelets."
  echo "Enter kamelets version to use:"
  echo "Example: 3.18.4"
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
  sed -i 's/kamelets-.*/kamelets-'$versionKamelets'.jar"/g' api/src/main/resources/application.yaml
  git add api/src/main/resources/resources-config.json
  git add api/src/main/resources/application.yaml

  git commit -m "chore(resources): Updating zip files with step catalog components."
fi

mvn versions:set -DnewVersion=$version
git add .
git commit -m "chore(release): Updating to version $version"

if [ "$gitPush" = "y" ];
then
  git push $gitFork
fi

echo "Enter new development version:"
echo "(SNAPSHOT will be added automatically)"
echo "Example: 1.0.1"
read -r version2

git checkout -b prepare-for-$version2
mvn versions:set -DnewVersion="$version2"-SNAPSHOT
git add .
git commit -m "chore(release): Updating to version $version2-SNAPSHOT"

if [ "$gitPush" = "y" ];
then
  git push $gitFork
fi


echo ""
echo "Check the git log and if you like what you see, create pull requests"
echo "for both branches release-$version and release-$version2."
if [ "$gitPush" = "y" ];
then
  echo "Those pull requests could be found in:"
  echo "$gitUrl/pull/new/release-$version"
  echo "$gitUrl/pull/new/prepare-for-$version2"
fi
echo ""
echo "Once the pull requests are merged, you can tag the version where release-$version HEAD is."
echo "Creating the tag will trigger the release creation."
echo ""
echo "Finally, go to https://github.com/KaotoIO/kaoto-backend/releases/ to review"
echo "the text of the release and publish it. "
echo ""
echo "Congratulations! Your job here is done."
