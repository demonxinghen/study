```shell
#!/bin/bash

# Redis connection details
REDIS_HOST="localhost"
REDIS_PORT="6379"
REDIS_PASSWORD="password"
REDIS_DB="6"
KEY_PATTERN="importing:orders:*"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}Redis Key Deletion Script${NC}"
echo "================================"
echo "Host: $REDIS_HOST:$REDIS_PORT"
echo "Database: $REDIS_DB"
echo "Pattern: $KEY_PATTERN"
echo "================================"

# Check if redis-cli is available
if ! command -v redis-cli &> /dev/null; then
    echo -e "${RED}Error: redis-cli is not installed${NC}"
    exit 1
fi

# Count keys matching the pattern
echo -e "\n${YELLOW}Counting keys...${NC}"
KEY_COUNT=$(redis-cli -h "$REDIS_HOST" -p "$REDIS_PORT" -a "$REDIS_PASSWORD" -n "$REDIS_DB" --scan --pattern "$KEY_PATTERN" | wc -l)

if [ "$KEY_COUNT" -eq 0 ]; then
    echo -e "${YELLOW}No keys found matching pattern: $KEY_PATTERN${NC}"
    exit 0
fi

echo -e "${GREEN}Found $KEY_COUNT keys${NC}"

# Ask for confirmation
read -p "Do you want to delete these $KEY_COUNT keys? (yes/no): " CONFIRM

if [ "$CONFIRM" != "yes" ]; then
    echo -e "${YELLOW}Operation cancelled${NC}"
    exit 0
fi

# Delete keys
echo -e "\n${YELLOW}Deleting keys...${NC}"

DELETED=0
redis-cli -h "$REDIS_HOST" -p "$REDIS_PORT" -a "$REDIS_PASSWORD" -n "$REDIS_DB" --scan --pattern "$KEY_PATTERN" | while read key; do
    redis-cli -h "$REDIS_HOST" -p "$REDIS_PORT" -a "$REDIS_PASSWORD" -n "$REDIS_DB" DEL "$key" > /dev/null
    DELETED=$((DELETED + 1))
    if [ $((DELETED % 100)) -eq 0 ]; then
        echo -e "Deleted $DELETED keys..."
    fi
done

echo -e "\n${GREEN}✓ Successfully deleted all keys matching pattern: $KEY_PATTERN${NC}"
```