#!/bin/bash

usage(){
    echo "Usage(): $0 [Commit_Message]"
    exit 1
}

commit_code(){
    echo "Processing: Adding codes to staging"
    git add .
    echo "--"
    echo "Processing: Checkin Codes with ${commit_message}"
    git commit -m "${commit_message}"
    echo "--"
    echo "Processing: Push Codes"
    git push
}

if [[ ! $# -eq 1 ]]
then
    usage
fi

commit_message="$1"
commit_code


