# Azure SQL Database Setup Guide for Client-sim

## ✅ What Has Been Configured

Your Spring Boot application is now ready for Azure SQL Database:

- **SQL Server JDBC Driver**: Already included in `pom.xml` (v12.6.1)
- **Azure Profile**: `application-azure.properties` configured with environment variables
- **Infrastructure as Code**: Bicep templates created for Azure SQL Server, Database, and App Service
- **Azure Configuration**: `azure.yaml` ready for Azure Developer CLI

## 📋 Prerequisites

Install required tools:

```bash
# Azure CLI
https://docs.microsoft.com/en-us/cli/azure/install-azure-cli-windows

# Azure Developer CLI (azd)
https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd

# Maven (for building Java)
# Should already be available via ./mvnw in your project
```

## 🚀 Deployment Steps

### Step 1: Authenticate with Azure

```powershell
# Login to your Azure account
az login

# Set your subscription
az account set --subscription "b41b904b-3178-4282-978e-da045b1cf220"
```

### Step 2: Initialize Azure Developer CLI

```powershell
cd c:\Users\Student\Desktop\Client-sim

# Initialize azd project (interactive)
azd init

# When prompted:
# - Project name: clientSim
# - Subscription: b41b904b-3178-4282-978e-da045b1cf220
# - Region: France Central
```

### Step 3: Deploy Infrastructure & Application

```powershell
# This will create:
# - Azure SQL Server
# - Database (client-simgroup-a)
# - App Service Plan
# - App Service with Spring Boot app
# 
# When prompted, provide the SQL admin password (choose secure password)

azd up
```

After `azd up` completes, you'll see outputs like:
```
SQL Server Name: clientsim-xxxxx
Database: client-simgroup-a
Connection String: jdbc:sqlserver://clientsim-xxxxx.database.windows.net:1433;...
```

## 🔐 Database Password Management

### Option 1: Environment Variables (Recommended)
Set these before running the app:

```powershell
$env:SPRING_DATASOURCE_PASSWORD="YourSecurePassword123!"
$env:SPRING_DATASOURCE_URL="jdbc:sqlserver://clientsim-xxxxx.database.windows.net:1433;database=client-simgroup-a;encrypt=true;trustServerCertificate=false;loginTimeout=30;"
$env:SPRING_DATASOURCE_USERNAME="GroupASimAdmin"
$env:SPRING_PROFILES_ACTIVE="azure"
```

### Option 2: Direct Configuration
Update [application-azure.properties](clientSim/src/main/resources/application-azure.properties):
```properties
spring.datasource.username=GroupASimAdmin
spring.datasource.password=your-password-here
spring.datasource.url=jdbc:sqlserver://clientsim-xxxxx.database.windows.net:1433;...
```

## 🧪 Testing Locally

### Run against Azure SQL from your local machine:

```powershell
cd c:\Users\Student\Desktop\Client-sim\clientSim

# Set environment variables for Azure SQL
$env:SPRING_PROFILES_ACTIVE="azure"
$env:SPRING_DATASOURCE_URL="jdbc:sqlserver://clientsim-xxxxx.database.windows.net:1433;database=client-simgroup-a;encrypt=true;trustServerCertificate=false;loginTimeout=30;"
$env:SPRING_DATASOURCE_USERNAME="GroupASimAdmin"
$env:SPRING_DATASOURCE_PASSWORD="your-actual-password"

# Build and run
./mvnw clean package
java -jar target/clientSim-0.0.1-SNAPSHOT.jar

# Access the app
# http://localhost:8080
```

### Using Spring Boot Maven plugin:

```powershell
$env:SPRING_PROFILES_ACTIVE="azure"
$env:SPRING_DATASOURCE_URL="jdbc:sqlserver://clientsim-xxxxx.database.windows.net:1433;database=client-simgroup-a;encrypt=true;trustServerCertificate=false;loginTimeout=30;"
$env:SPRING_DATASOURCE_USERNAME="GroupASimAdmin"
$env:SPRING_DATASOURCE_PASSWORD="your-actual-password"

./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=azure"
```

## 📊 Troubleshooting Connection Issues

### Test SQL Server Connectivity

```powershell
# Install SQL Server Command Line Tools if needed
# https://learn.microsoft.com/en-us/sql/tools/sqlcmd-utility

# Test connection
sqlcmd -S clientsim-xxxxx.database.windows.net -U GroupASimAdmin -P "your-password" -d client-simgroup-a -Q "SELECT 1;"
```

### Common Issues

**Issue**: `Cannot open server (Cannot connect to Azure SQL Server)`
- **Solution**: Check firewall rules. Bicep template includes rule to allow all IPs. Verify in Azure Portal > SQL Server > Firewalls and virtual networks

**Issue**: `Login failed for user 'GroupASimAdmin'`
- **Solution**: Verify password is set correctly; special characters may need escaping in environment variables

**Issue**: `Database does not exist`
- **Solution**: Verify database name is `client-simgroup-a` in connection string

## 📈 Database Initialization

Your `data.sql` and `schema.sql` files will:
- Automatically execute when `spring.jpa.hibernate.ddl-auto=update` is set
- Create tables defined in your entity classes
- Populate initial data as configured

## 🔗 Useful Links

- [Azure SQL Connection Strings](https://learn.microsoft.com/en-us/azure/azure-sql/database/connect-query-java)
- [Azure Developer CLI Documentation](https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/)
- [Spring Boot Azure Integration](https://learn.microsoft.com/en-us/java/azure/spring-framework/)
- [Azure SQL Security Best Practices](https://learn.microsoft.com/en-us/azure/azure-sql/database/security-best-practices)

## 📝 Next Steps

1. **Deploy infrastructure**: Run `azd up` from workspace root
2. **Test locally**: Set environment variables and run app locally against Azure SQL
3. **Monitor**: Check Azure Portal for SQL Server, Database, and App Service
4. **Update code**: Modify controllers/entities as needed; schema changes are automatic (ddl-auto=update)
5. **Deploy app code**: Run `azd deploy` to push new code to App Service

## ⚠️ Important Notes

- **Password Security**: Never commit passwords to git. Use environment variables or Azure Key Vault
- **Connection Timeout**: First connection may be slow as Azure SQL spins up (2-5 seconds)
- **Pricing**: Monitor Azure Portal for costs. Basic tier is ~$5/month, General Purpose ~$20-100/month
- **Backups**: Azure SQL automatically creates backups; verify retention policy meets your needs
