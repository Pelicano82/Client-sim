# Azure SQL Database Migration Guide

## Prerequisites
- Azure account with active subscription
- Azure SQL Server and Database created

## Step 1: Get Your Azure SQL Connection Details

1. Login to [Azure Portal](https://portal.azure.com)
2. Navigate to your SQL Database
3. Click "Connection strings" 
4. Copy the JDBC connection string
5. Note your:
   - **Server**: `yourserver.database.windows.net`
   - **Database**: `clientsimdb`
   - **Username**: `sqladmin@yourserver`
   - **Password**: Your database password

## Step 2: Configure application-azure.properties

Edit `src/main/resources/application-azure.properties` and replace:

```properties
spring.datasource.url=jdbc:sqlserver://YOUR_SERVER_NAME.database.windows.net:1433;database=clientsimdb;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.username=sqladmin@YOUR_SERVER_NAME
spring.datasource.password=YOUR_PASSWORD_HERE
```

### Replace:
- `YOUR_SERVER_NAME`: Your SQL Server name (e.g., `myserver`)
- `YOUR_PASSWORD_HERE`: Your database password

## Step 3: Switch Application Profile

### Option A: Update application.properties
Add this line to `application.properties`:
```properties
spring.profiles.active=azure
```

### Option B: Set Environment Variable (Recommended for Production)
```bash
# Windows PowerShell
$env:SPRING_PROFILES_ACTIVE="azure"

# Windows CMD
set SPRING_PROFILES_ACTIVE=azure

# Linux/Mac
export SPRING_PROFILES_ACTIVE=azure
```

### Option C: Command Line when Starting
```bash
java -jar clientSim-0.0.1-SNAPSHOT.jar --spring.profiles.active=azure
```

## Step 4: Rebuild & Deploy

```bash
# Navigate to project directory
cd clientSim

# Rebuild
mvn clean package -DskipTests

# Run with Azure profile
java -jar target/clientSim-0.0.1-SNAPSHOT.jar --spring.profiles.active=azure
```

## Step 5: Verify Connection

When the app starts, you should see:
- No H2 console warning
- Successful Spring Data JPA repository scanning
- Database connection established message
- Test data initialization (DataInitializer runs)

## Troubleshooting

### Connection Timeout
- Check Azure SQL firewall rules allow your IP
- In Azure Portal → SQL Server → Firewalls and virtual networks
- Add your public IP address

### Authentication Failed
- Verify username format: `sqladmin@yourserver` (not just `sqladmin`)
- Check password is correct
- Ensure user has database access permissions

### Test Data Not Initializing
- DataInitializer component runs automatically
- Check application logs for errors
- Verify database user has CREATE TABLE permissions

## Switching Back to Local H2

If you need to switch back to H2:

**Option A**: Update `application.properties`:
```properties
spring.profiles.active=default
```

**Option B**: Clear environment variable:
```bash
# PowerShell
$env:SPRING_PROFILES_ACTIVE=""

# CMD
set SPRING_PROFILES_ACTIVE=

# Linux/Mac
unset SPRING_PROFILES_ACTIVE
```

**Option C**: Run without profile argument (defaults to H2)

## Security Notes

⚠️ **Never commit `application-azure.properties` with actual credentials!**

Better approach - Use environment variables:

### Update application-azure.properties to use env vars:
```properties
spring.datasource.url=${AZURE_DATABASE_URL}
spring.datasource.username=${AZURE_DATABASE_USER}
spring.datasource.password=${AZURE_DATABASE_PASSWORD}
```

### Set environment variables:
```bash
# PowerShell
$env:AZURE_DATABASE_URL="jdbc:sqlserver://..."
$env:AZURE_DATABASE_USER="sqladmin@yourserver"
$env:AZURE_DATABASE_PASSWORD="your_password"

# Or in system environment variables (Windows Settings)
```

## Azure SQL Costs

- **Free Tier**: 5 DTU SQL Database (limited)
- **Standard**: Starts at ~$15/month
- **Premium**: Higher performance, higher cost

Check [Azure Pricing Calculator](https://azure.microsoft.com/en-us/pricing/calculator/) for current rates.

## Next Steps

1. ✅ Create Azure SQL Database
2. ✅ Configure application-azure.properties
3. ✅ Set spring.profiles.active=azure
4. ✅ Rebuild and test locally
5. ✅ Deploy to Azure App Service (optional)

For deployment to Azure App Service, see [Azure deployment guide](https://learn.microsoft.com/en-us/azure/app-service/quickstart-java).
