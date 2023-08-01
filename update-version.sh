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
  /bin/bash ./update-resources.sh
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
