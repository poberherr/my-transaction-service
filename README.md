# my-transaction-service

Starting the Service:
  - Run TransactionService(.java)

Quick overview:
    - TransactionService starts the the service
    - Routes are Handeled in Restfulhandler
    - Helper classes for communicating are: AbstractRequestHandler, RequestHandler, Answer Validable (interface)

Folder Model:
    - Model (interface)
    - Transaction (actually the only model used here)

Folder Store:
    - (I am DB guy so for me there would go the PostGreSQL or any DB adapter which would have eased the life a bit :) )
    - StoreManager: Is instantiated once and "simulates" the DB

Handlers:
    - Each call to the REST Service has it's own handler
    - class Name Schema:
        For root /resource call Index is used
        Create for Post, Update for Put, Single for get...

Personal comments:
    I really liked the task, it's kinda nice and simple with (at least for me) a twist in it because of no SQL.
    It basically took me the first two days to get used to Java again (last time I was still in University);
    Most time I needed was to find out which frameworks to use, which tools and to adapt my coding style back to Java.

    I did't have the time to code more test - which I really would have liked. Also some functional tests instead of
    just simpler unit tests.


    Spark: Not the same as the "known" Spark - but seems to be nice.
    Lombok: Seems to be kinda handy but I got rid of it, for clarity.
    no .gitignore: Since I checked my trash in already - I thought I can also leave it there for now.
    git: push -f never a good choice :)