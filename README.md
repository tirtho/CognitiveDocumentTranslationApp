# The Architecture
The architecture below shows what you can execute with the code here. Upon setup, you can load your files to translate into the Input container of the Blob Storage, which will automatically trigger an Azure Function, fDocTranslate, to call the Azure Cognitive Document Translation APIs to translate the new document(s) and create an item entry for this job in the Azure Cosmos DB instance. The Cognitive Document Translation API will store the translated document(s) in the Output container. And storing this new translated document in Output container will trigger the other Azure Function, fDocTranslateStatus, which will then update the item in Cosmos DB with translation completion and other status information. The Azure Web App (written using Spring Boot) gets the list of items from the Cosmos DB instance and displays in the UI.

You can translate documents from any language to any language (supported by Azure Cognitive Document Translation API Service - [see list][SupportedLanguages]). Documents should be in [a supported format][SupportedFormat]. The translation from and to languages are derived by the code based on the assumption on the format of the source folder in the Blob Storage as \<source container name\>/\<2 char code for the from language\>-\<2 char code for the to language\>. For example, in \<source container name\>/en-es/ the code will translate all files in this folder from English to Spanish. Also, you can add dictionary items in a dictionary container you create in your Blob Storage. Sample dictionary files are in the [sample-glossaries][GlossaryFolder] folder.

![Architecture URL](/images/architecture.jpg)

# The Setup
1. Create an Azure Blob Store with 3 containers for input, output and glossary.
2. Create an Azure Function App
3. Create an Azure Cosmos DB SQL API database and a container inside it.
4. Create an Azure Spring Boot Web App in App Service
5. Create an Azure Cognitive Document Translation API Service instance

A few assumptions in the code are below - 

The source files in the Blob Store are in a subfolder. For example, the files could be kept in <source container name>/en-es (for documents to be translated from English to Spanish). This way you can keep different categories of files in different sub folders. The code will log errors if files are added to the source container in root or any folder that does not follow the above pattern like /en-es/.

The translated files are placed in a subfolder \<src language\>-\<target language\> folder. For example if you are translating a file named MyDocs.pdf from English to Spanish, the translated file will be placed in <target container name>/en-es/MyDocs.pdf. The target subfolder en-es must exist beforehand.

For each target language in the source folder, there must be a glossary-<target language>.tsv file in the glossary container in the Blob Storage.
 
### The Function App Setting
The code for the two Azure Functions are in the [translationFunctions][translationFunctionsFolder] folder. If you want to run it locally (from say VSCode), you have to create your own local.settings.json file in the [translationFunctions][translationFunctionsFolder] folder so it can connect with the Blob Store, the Azure Cognitive Translation API Service and Cosmos DB. Below is a sample local.settings.json.
```
{
  "IsEncrypted": false,
  "Values": {
    "AzureWebJobsStorage": "DefaultEndpointsProtocol=https;AccountName=trtransdemo;AccountKey=XXXXX==;EndpointSuffix=core.windows.net",
    "FUNCTIONS_WORKER_RUNTIME": "python",
    "theTriggeringBlob_STORAGE": "DefaultEndpointsProtocol=https;AccountName=trtransdemo;AccountKey=XXXXXX==;EndpointSuffix=core.windows.net",
    "DOCS_CONTAINER_HOST": "https://trtransdemo.blob.core.windows.net/",
    "DOCS_CONTAINER_SOURCE_KEY": "?sp=racwdl&st=2021-06-13T03:05:57Z&se=2021-12-13T12:05:57Z&spr=https&sv=2020-02-10&sr=c&sig=xxxxxxxx",
    "DOCS_TARGET_CONTAINER_NAME": "translation-target",
    "DOCS_CONTAINER_TARGET_KEY": "?sp=racwdl&st=2021-06-13T03:12:50Z&se=2021-12-13T12:12:50Z&spr=https&sv=2020-02-10&sr=c&sig=xxxxxxx",
    "TRANSLATOR_DOCS_ENDPOINT": "https://trtranslator.cognitiveservices.azure.com/translator/text/batch/v1.0",
    "TRANSLATOR_DOCS_SUBSCRIPTION_KEY": "00000000000000",
    "GLOSSARY_CONTAINER_KEY": "?sp=racwdl&st=2021-06-13T03:16:44Z&se=2021-12-13T12:16:44Z&spr=https&sv=2020-02-10&sr=c&sig=xxxxxxxxxxxxxx",
    "GLOSSARY_URI": "https://trtransdemo.blob.core.windows.net/translation-glossary",
    "theCosmosDB_DOCUMENTDB": "AccountEndpoint=https://trtransdemo.documents.azure.com:443/;AccountKey=xxxxxxxxx==;",
    "cosmosDatabaseName": "DocumentTranslationDB",
    "cosmosCollectionName": "TranslationJob"

  }
}
```
And when you run the function in Azure, you need to have the above variables set in App Setting for the Function App, as in the Portal screen below -
![FuncApp][FuncAppImage]

### The Spring Boot App Service Setting

The Web App reads data from Cosmos DB to render in the UI. The settings to connect to the Cosmos DB are in Configuration menu item as shown in the Portal screen below - 
![AppSetting][AppSettingImage]


# Command Line Cognitive Translation Programs in python

In order to run the command line translation tasks, set the following environment variables 

```
set TRANSLATOR_TEXT_SUBSCRIPTION_KEY=<Key1 or Key 2 from your Azure Cognitive Translation Services API>
set TRANSLATOR_TEXT_ENDPOINT=https://api.cognitive.microsofttranslator.com/
set TRANSLATOR_RESOURCE_LOCATION=<location where you instantiated your Azure Cognitive Translation Service, e.g. eastus2>

set TRANSLATOR_DOCS_SUBSCRIPTION_KEY=<Key from your Azure Cognitive Document Translator Service>
set TRANSLATOR_DOCS_HOST=<name of the translator instance, e.g. trtranslator>.cognitiveservices.azure.com
set TRANSLATOR_DOCS_ENDPOINT=https://<name of your document translator>.cognitiveservices.azure.com/translator/text/batch/v1.0
set TRANSLATOR_DOCS_SOURCE_CONTAINER_URL=<blob storage source container url, e.g. https://tbdemostoragev2.blob.core.windows.net/<name of source container>?sp=rl^&st=2021-05-31T01:02:12Z^&se=2041-06-01T09:02:12Z^&spr=https^&sv=2020-02-10^&sr=c^&sig=xxxxxxu%%2Fxxxxx%%3D>
set TRANSLATOR_DOCS_TARGET_CONTAINER_URL=<blob storage source url, e.g. https://tbdemostoragev2.blob.core.windows.net/tr-translator-target-docs?sp=rwl^&st=2021-05-31T01:13:36Z^&se=2041-06-01T09:13:36Z^&spr=https^&sv=2020-02-10^&sr=c^&sig=xxxxxx>
set TRANSLATOR_DOCS_GLOSSARY_CONTAINER_URL=<blob storage glossary container url, e.g. https://tbdemostoragev2.blob.core.windows.net/tr-translator-glossary-files?sp=rl^&st=2021-05-31T01:16:32Z^&se=2041-06-01T09:16:32Z^&spr=https^&sv=2020-02-10^&sr=c^&sig=xxxxxxxxxxxxxxx>
```
Notes: 
1. The keys above could be the same if you are running the document translation and text translation from the same instance of the Azure Cognitive Translation Service.
2. The examples above do not have real keys or secrets. These just show you how these string look like typically. 
3. If you are setting these environment variables from command line in Windows, some characters need escaping, like you need to prefix ^ before an &, or an additional % before a % character

Now you are ready to run the command line translation tasks.
Example: 
```
python TranslateText.py -f en -t de -q "My name is TR"
```
[translationFunctionsFolder]: <https://github.com/tirtho/CognitiveDocumentTranslationApp/blob/main/translationFunctions>
[GlossaryFolder]: <https://github.com/tirtho/CognitiveDocumentTranslationApp/blob/main/sample-glossaries>
[funcAppImage]: <https://github.com/tirtho/CognitiveDocumentTranslationApp/blob/main/images/FuncApp.jpg>
[AppSettingImage]: <https://github.com/tirtho/CognitiveDocumentTranslationApp/blob/main/images/AppSetting.jpg>
[SupportedLanguages]: <https://docs.microsoft.com/en-us/azure/cognitive-services/translator/language-support>
[SupportedFormat]: <https://docs.microsoft.com/en-us/azure/cognitive-services/translator/document-translation/overview#supported-document-formats>
