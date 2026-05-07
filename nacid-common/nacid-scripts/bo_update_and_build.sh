#!/bin/bash

process_project() {
    # Check if this is a git repo
    if ! git status &> /dev/null; then
        echo "Not a git repository"
        return
    fi

    # Fetch all branches
    echo "Fetching all branches from remote..."
    git fetch --all --prune

    # First check for local branch
    if git show-ref --quiet --verify "refs/heads/$BRANCH"; then
        echo "Local branch $BRANCH exists, checking it out..."
        git checkout "$BRANCH"
    # Then check for remote branch
    elif git ls-remote --exit-code --heads origin "$BRANCH" &> /dev/null; then
        echo "Branch $BRANCH exists on remote, checking it out..."
        git checkout "$BRANCH" || git checkout -b "$BRANCH" "origin/$BRANCH"
        git pull origin "$BRANCH"
    else
        echo "Branch $BRANCH not found locally or remotely, using $MASTER_BRANCH..."
        git checkout "$MASTER_BRANCH"
        git pull origin "$MASTER_BRANCH"
    fi

    # Build logic
    if [ -f "pom.xml" ]; then
        echo "Building parent project..."
        mvn clean install -DskipTests
        return
    fi

    # Build child modules
    for dir in */; do
        if [ -f "$dir/pom.xml" ]; then
            echo "Building module: $dir"
            (cd "$dir" && mvn clean install -DskipTests)
        fi
    done
}

# Main script
if [ -z "$1" ]; then
    echo "Usage: $0 branch_name"
    read -p "Press enter to exit..."
    exit 1
fi

BRANCH="$1"
MASTER_BRANCH="master"

PROJECT_LIST=(
    "nacid-common-dto"
    "nacid-common"
    "nacid-payments"
    "front-office/nacid-frontoffice-clients"
    "nacid-ext-clients"
    "back-office/nacid-backoffice-clients"
    "back-office/nacid-backoffice-common"
    "back-office/nacid-backoffice-core"
    "back-office/nacid-backoffice-rudi"
    "back-office/nacid-backoffice-libserv"
    "back-office/nacid-backoffice-regprof"
	"back-office/nacid-backoffice-public-services"
)

echo "Executing for branch: $BRANCH"

for PROJECT in "${PROJECT_LIST[@]}"; do
    echo
    echo "======================================"
    echo "Processing: $PROJECT"
    echo "======================================"

    if [ -d "$PROJECT" ]; then
        (cd "$PROJECT" && process_project)
    else
        echo "Cloning $PROJECT..."
        git clone "https://gitss.sirma.com/duosoft/nacid/$PROJECT.git" "$PROJECT" && 
            (cd "$PROJECT" && process_project)
    fi
done

echo
echo "All projects processed"
read -p "Press enter to exit..."