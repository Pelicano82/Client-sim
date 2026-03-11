# Azure SQL Database Connection Plan

## Overview
Connect the existing Client-sim Spring Boot application to Azure SQL Database.

## Current State
- **Application Type**: Spring Boot 4.0.3 (Java 21)
- **Current Database**: H2 in-memory (development)
- **Mode**: MODIFY (adding Azure SQL connectivity to existing app)
- **Status**: Application already has SQL Server driver + azure.properties template

## Architecture Decision
- **Service**: Azure SQL Database (Single database, Basic tier recommended for dev)
- **Authentication**: SQL username/password
- **Framework**: Azure Developer CLI (azd) with Bicep Infrastructure as Code
- **Profile**: Azure Spring profile activation

## Tasks

### Phase 1: Setup & Planning
- [ ] Create Azure resource group
- [ ] Provision Azure SQL Server and database
- [ ] Generate connection string
- [ ] Collect credentials securely

### Phase 2: Application Configuration
- [ ] Update `application-azure.properties` with real connection details
- [ ] Verify Spring profile configuration
- [ ] Test local connectivity
- [ ] Initialize database schema

### Phase 3: Validation & Documentation
- [ ] Validate connectivity
- [ ] Document connection steps
- [ ] Update README with Azure setup instructions

## Prerequisites
- Azure subscription (required)
- Azure SQL Server admin credentials
- Application already has SQL Server JDBC driver (v12.6.1)

## COLLECTED USER PREFERENCES
- **Azure Subscription**: b41b904b-3178-4282-978e-da045b1cf220
- **Region**: France Central (francec)
- **SQL Tier**: General Purpose (GP_Gen5)
- **Admin Username**: GroupASimAdmin
- **Database Name**: client-simgroup-a

## COMPLETED
- [x] Plan created
- [x] Bicep infrastructure generated (`infra/main.bicep`)
- [x] Azure.yaml template created
- [x] Application properties updated with environment variable placeholders

## NEXT STEPS
1. Set secure password for SQL Admin (provide via environment variable)
2. Run: `azd up` to deploy infrastructure and app to Azure
3. Verify database connection
4. Test application endpoints
