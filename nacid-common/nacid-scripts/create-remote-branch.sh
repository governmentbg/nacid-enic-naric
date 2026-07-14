#!/bin/bash

RED="\e[31m"
LIGHT_RED="\e[91m"
GREEN="\e[32m"
GREEN_BOLD="\e[1;32m"
ENDCOLOR="\e[0m"

pad=$(printf '%0.1s' "."{1..60})

# TODO: Check for a way to get a list of all nacid repositories via API call
declare -a REPOSITORIES=(
  #"minio"

  #"front-office/nacid-email-sender"
  #"front-office/nacid-frontoffice-migration"
  "front-office/nacid-frontoffice-components"
  "front-office/nacid-frontoffice-clients"
  "front-office/nacid-registers"
  "front-office/nacid-email-consumer"
  "front-office/nacid-core-api"
  "front-office/nacid-core-data"
  "front-office/nacid-services"
  "front-office/nacid-portal"
  #"front-office/nacid-qual-assess"

  #"config/nacid-config-server"
  #"config/nacid-frontend-config-manager"
  #"config/nacid-config-store"
  #"config/nacid-frontend-config-store"

  #"back-office/nacid-backoffice-migration"
  "back-office/nacid-backoffice-components"
  "back-office/nacid-backoffice-clients"
  "back-office/nacid-backoffice-common"
  "back-office/nacid-backoffice-cron-be"
  "back-office/nacid-backoffice-public-services"
  "back-office/nacid-backoffice-core"
  "back-office/nacid-backoffice-regprof"
  "back-office/nacid-backoffice-libserv"
  "back-office/nacid-backoffice-rudi"
  "back-office/nacid-backoffice-secondary-education"

  #"nacid_isobaq"
  #"nacid-npn"
  "nacid-payments"
  "nacid-common"
  #"nacid-grading-scales"
  "nacid-common-dto"
  "nacid-ext-clients"
  "nacid-components"
  "nacid-signature"
  )

function url_encode() {
    echo "$@" \
    | sed \
        -e 's/%/%25/g' \
        -e 's/ /%20/g' \
        -e 's/!/%21/g' \
        -e 's/"/%22/g' \
        -e "s/'/%27/g" \
        -e 's/#/%23/g' \
        -e 's/(/%28/g' \
        -e 's/)/%29/g' \
        -e 's/+/%2b/g' \
        -e 's/,/%2c/g' \
        -e 's/-/%2d/g' \
        -e 's/:/%3a/g' \
        -e 's/;/%3b/g' \
        -e 's/?/%3f/g' \
        -e 's/@/%40/g' \
        -e 's/\$/%24/g' \
        -e 's/\&/%26/g' \
        -e 's/\*/%2a/g' \
        -e 's/\./%2e/g' \
        -e 's/\//%2f/g' \
        -e 's/\[/%5b/g' \
        -e 's/\\/%5c/g' \
        -e 's/\]/%5d/g' \
        -e 's/\^/%5e/g' \
        -e 's/_/%5f/g' \
        -e 's/`/%60/g' \
        -e 's/{/%7b/g' \
        -e 's/|/%7c/g' \
        -e 's/}/%7d/g' \
        -e 's/~/%7e/g'
}

separator=", "
repositoriesString="$( printf "${separator}%s" "${REPOSITORIES[@]}" )"
repositoriesString="${repositoriesString:${#separator}}"

echo -e "${GREEN}This script will create branch with the given name directly in gitlab for NACID project.\nIf branch with the same name already exists in any of the repositories it will just skip the repository.${ENDCOLOR}\n"
echo -e "Press CTRL+C to exit at any time.\n"

read -p "Enter branch name (e.g. release/0): " branchName
read -p "Repeat branch name: " branchNameRepeat

# TODO: Add validation for empty string (replace all whitespace with empty and check)

if [ "$branchName" != "$branchNameRepeat" ]; then
  echo -e "${RED}Names doesn't match.${ENDCOLOR}"
  exit 1
fi

# TODO: Add validation for illegal characters. Force check for the branch to start with "release/" or just ask for release number?

echo -e "\nBranch with name ${GREEN_BOLD}$branchName${ENDCOLOR} will be created in (https://gitss.sirma.com/duosoft/nacid) ${GREEN}$repositoriesString${ENDCOLOR}\n"

read -s -p "Enter gitlab private token: " privateToken

printf "\n\n"

for i in "${REPOSITORIES[@]}"
do
  projectName="duosoft/nacid/$i"
  url="https://gitss.sirma.com/api/v4/projects/$( url_encode $projectName )/repository/branches?branch=$( url_encode $branchName )&ref=master"
  response=`curl -s --request POST \
  --header "PRIVATE-TOKEN: $privateToken" \
  --url "$url"`

  message=`echo $response | grep -o '"message":"[^"]*' | grep -o '[^"]*$'`
  
  status="${GREEN}SUCCESSFUL${ENDCOLOR}"
  if [ "$message" == "Branch already exists" ]; then
    status="${RED}ALREADY EXISTS${ENDCOLOR}"
  fi

  if [ "$message" == "401 Unauthorized" ]; then
    echo -e "${RED}Invalid git lab token. Access denied.${ENDCOLOR}"
    exit 1
  fi
  
  if [ "$message" == "403 Forbidden" ]; then
    echo -e "${RED}Operation forbidden. Check access token role and scopes!${ENDCOLOR}"
    exit 1
  fi
  
  result=`printf '%s%*.*s%s' "${GREEN}$i${ENDCOLOR}" 0 $((50 - ${#i} )) "$pad" " $status"`

  echo -e "Creating branch in $result"
done

printf "\nCompleted\n"
