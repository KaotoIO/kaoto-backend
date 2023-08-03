#!/bin/bash

echo "MAKE SURE YOU ARE ON LATEST MAIN"

echo "Do you want to download the latest zip of Kaoto bridged camel components(main of camel-component-metadata)? (y/n)"
read -r download
path=$(pwd)
if [ "$download" = "y" ];
then
  echo "Removing old zip files"
  git rm api/src/main/resources/camel-component-metadata.zip
  git rm camel-support/src/test/resources/camel-component-metadata.zip

  echo "Downloading Kaoto bridged Camel components (EIP)."
  wget https://github.com/KaotoIO/camel-component-metadata/archive/refs/heads/main.zip
  cp "main.zip" "${path}/api/src/main/resources/camel-component-metadata.zip"
  mv "main.zip" "${path}/camel-support/src/test/resources/camel-component-metadata.zip"

  git add -f api/src/main/resources/camel-component-metadata.zip
  git add -f camel-support/src/test/resources/camel-component-metadata.zip

  echo "Updating version info in Readme"
  commitHash=$(git ls-remote https://github.com/KaotoIO/camel-component-metadata HEAD | head -n1 | awk '{print $1;}')
  sed -i 's/camel components: \*\*.*/camel components: \*\*'"$commitHash"'\*\*/g' README.md
  git add README.md
fi

echo "Do you want to download the latest Kaoto view definitions (main of kaoto-viewdefinition-catalog)? (y/n)"
read -r download
if [ "$download" = "y" ];
then
  path=$(pwd)
  echo "Removing old zip files"
  git rm api/src/main/resources/view-definition.zip
  git rm camel-support/src/test/resources/view-definition.zip

  echo "Downloading Kaoto view definitions."
  wget https://github.com/KaotoIO/kaoto-viewdefinition-catalog/archive/refs/heads/main.zip
  cp "main.zip" "${path}/api/src/main/resources/view-definition.zip"
  mv "main.zip" "${path}/camel-support/src/test/resources/view-definition.zip"

  git add -f api/src/main/resources/view-definition.zip
  git add -f camel-support/src/test/resources/view-definition.zip

  echo "Updating version info in Readme"
  commitHash=$(git ls-remote https://github.com/KaotoIO/kaoto-viewdefinition-catalog HEAD | head -n1 | awk '{print $1;}')
  sed -i 's/view definitions: \*\*.*/view definitions: \*\*'"$commitHash"'\*\*/g' README.md
  git add README.md
fi

echo "Do you want to update the Apache Camel components? (y/n)"
read -r download
if [ "$download" = "y" ];
then
  echo "Downloading camel connectors."
  echo "Enter camel version to use (tag in apache/camel repo):"
  echo "Example: 3.21.0"
  read -r versionCamel

  echo "Removing old zip files"
  git rm api/src/main/resources/camel-connectors-*.jar
  git rm camel-support/src/test/resources/camel-connectors-*.jar

  echo "Preparing zip file with camel component metadata"
  cd /tmp || exit 1
  mkdir camel-connectors
  cd camel-connectors || exit 1
  wget "https://github.com/apache/camel/archive/refs/tags/camel-${versionCamel}.zip"
  unzip ./camel-"${versionCamel}".zip
  cp "camel-camel-${versionCamel}/dsl/camel-yaml-dsl/camel-yaml-dsl/src/generated/resources/schema/camel-yaml-dsl.json" "${path}/camel-support/src/main/resources/io/kaoto/backend/camel/service/deployment/generator/camelroute/camel-yaml-dsl.json"
  cd "camel-camel-${versionCamel}/catalog/camel-catalog/src/generated/resources/org/apache/camel/catalog/components/" || exit 1
  zip "camel-connectors-${versionCamel}.zip" ./*.json

  echo "Copy new zip to Kaoto-backend"
  cp "camel-connectors-${versionCamel}.zip" "${path}/api/src/main/resources/"
  mv "camel-connectors-${versionCamel}.zip" "${path}/camel-support/src/test/resources/"
  cd "${path}" || exit 1

  echo "Updating configurations"
  git add -f "${path}/api/src/main/resources/camel-connectors-${versionCamel}.zip"
  git add -f "${path}/camel-support/src/test/resources/camel-connectors-${versionCamel}.zip"

  sed -i 's/camel-connectors-.*/camel-connectors-'"$versionCamel"'.zip$"/g' api/src/main/resources/resources-config.json
  sed -i 's/camel-connectors-.*/camel-connectors-'"$versionCamel"'.zip"/g' api/src/main/resources/application.yaml
  sed -i 's/camel-connectors-.*/camel-connectors-'"$versionCamel"'.zip"/g' api/src/test/resources/application.yaml
  sed -i 's/camel-connectors-.*/camel-connectors-'"$versionCamel"'.zip"/g' camel-support/src/test/resources/application.yaml
  sed -i 's/camel-connectors-.*/camel-connectors-'"$versionCamel"'.zip";/g' camel-support/src/test/java/io/kaoto/backend/camel/metadata/parser/step/camelroute/CamelRouteParseCatalogTest.java
  sed -i 's/VERSION = ".*/VERSION = "'"$versionCamel"'";/g' camel-support/src/test/java/io/kaoto/backend/camel/service/step/parser/kamelet/KameletStepParserServiceTest.java

  git add api/src/main/resources/resources-config.json api/src/main/resources/application.yaml \
  api/src/test/resources/application.yaml camel-support/src/test/resources/application.yaml \
  camel-support/src/test/java/io/kaoto/backend/camel/metadata/parser/step/camelroute/CamelRouteParseCatalogTest.java \
  camel-support/src/main/resources/io/kaoto/backend/camel/service/deployment/generator/camelroute/camel-yaml-dsl.json \
  camel-support/src/test/java/io/kaoto/backend/camel/service/step/parser/kamelet/KameletStepParserServiceTest.java

  echo "Updating version info in Readme"
  sed -i 's/Camel-connectors: \*\*.*/Camel-connectors: \*\*'"$versionCamel"'\*\*/g' README.md
  git add README.md

  rm -rf /tmp/camel-connectors
fi

echo "Do you want to update the Apache Camel Kamelets? (y/n)"
read -r download
if [ "$download" = "y" ];
then
  echo "Downloading kamelets."
  echo "Enter kamelets version to use (tag in apache/camel-kamelets repo):"
  echo "Example: 3.21.0"
  read -r versionKamelets

  echo "Removing old zip files"
  git rm api/src/main/resources/camel-kamelets-*.jar
  git rm camel-support/src/test/resources/camel-kamelets-*.jar

  echo "Preparing zip file with camel component metadata"
  cd /tmp || exit 1
  mkdir kamelets
  cd kamelets || exit 1
  wget "https://github.com/apache/camel-kamelets/archive/refs/tags/v${versionKamelets}.zip"
  unzip ./v"${versionKamelets}".zip
  cd "camel-kamelets-${versionKamelets}/kamelets/" || exit 1
  zip "camel-kamelets-${versionKamelets}.jar" ./*

  echo "Copy new zip to Kaoto-backend"
  cp "camel-kamelets-${versionKamelets}.jar" "${path}/api/src/main/resources/"
  mv "camel-kamelets-${versionKamelets}.jar" "${path}/camel-support/src/test/resources/"
  cd "${path}" || exit 1

  echo "Updating configurations"
  git add -f "${path}/api/src/main/resources/camel-kamelets-${versionKamelets}.jar"
  git add -f "${path}/camel-support/src/test/resources/camel-kamelets-${versionKamelets}.jar"

  sed -i 's/camel-kamelets-.*/camel-kamelets-'"$versionKamelets"'.jar$"/g' api/src/main/resources/resources-config.json
  sed -i 's/camel-kamelets-.*/camel-kamelets-'"$versionKamelets"'.jar"/g' api/src/main/resources/application.yaml
  sed -i 's/camel-kamelets-.*/camel-kamelets-'"$versionKamelets"'.jar"/g' api/src/test/resources/application.yaml
  sed -i 's/camel-kamelets-.*/camel-kamelets-'"$versionKamelets"'.jar"/g' camel-support/src/test/resources/application.yaml
  sed -i 's/VERSION = ".*/VERSION = "'"$versionKamelets"'";/g' camel-support/src/test/java/io/kaoto/backend/camel/metadata/parser/step/kamelet/KameletParseCatalogTest.java

  git add api/src/main/resources/resources-config.json api/src/main/resources/application.yaml \
  api/src/test/resources/application.yaml camel-support/src/test/resources/application.yaml \
  camel-support/src/test/java/io/kaoto/backend/camel/metadata/parser/step/kamelet/KameletParseCatalogTest.java

  echo "Updating version info in Readme"
  sed -i 's/Camel-kamelets: \*\*.*/Camel-connectors: \*\*'"$versionKamelets"'\*\*/g' README.md
  git add README.md

  rm -rf /tmp/kamelets
fi

echo "All resources were updated successfully! Do you want to create commit? (y/n)"
read -r download
if [ "$download" = "y" ];
then
  git commit -m "chore(resources): Updating zip files with step catalog components."
fi

echo "Do you want to run tests? (y/n)"
read -r download
if [ "$download" = "y" ];
then
  ./mvnw clean test
fi