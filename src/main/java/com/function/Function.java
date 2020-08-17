package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.CosmosDBTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it
     * using "curl" command in bash: 1. curl -d "HTTP Body" {your
     * host}/api/HttpExample 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */

    protected static final Logger log = LoggerFactory.getLogger(Function.class);

    @FunctionName("cosmosDBMonitor")
    public void cosmosDBMonitor(
            @CosmosDBTrigger(name = "items", databaseName = "TMUsers", collectionName = "tmusers", createLeaseCollectionIfNotExists = true, connectionStringSetting = "AzureCosmosDBConnection") String[] items,
            final ExecutionContext context) {
        for (String string : items) {
            log.info(string);
            context.getLogger().info("Object : " + string);
        }
        context.getLogger().info(items.length + "item(s) changed.");
    }
}
