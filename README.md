# my-transaction-service

Starting the Service:
  - Run TransactionService(.java)

Quick overview:
- TransactionService starts the the service
- Routes are Handeled in Restfulhandler
- Helper classes for communicating are: AbstractRequestHandler, RequestHandler, Answer Validable (interface)

Task understanding:
- Maybe I got it wrong, but I assumed, that transactions can be just set over the put call;
- so I designed it that you first make a empty /post/ call to transactions, where you recieve the ID
- then you can make a put call to /post/x to fill the transaction. I guess in a real system you could let the opened
transactions time out.

My solution:
- The return is not always 100% consistent which I'd like to have fixed
- I tested manually, (Postman), but I should have written more tests
- Didn't for example test, if two transactions can be each other parents (chrchr)

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


- Spark: Not the same as the "known" Spark - but seems to be nice.
- Lombok: Seems to be kinda handy but I got rid of it, for clarity.
- no .gitignore: Since I checked my trash in already - I thought I can also leave it there for now.
- git: push -f never a good choice :)