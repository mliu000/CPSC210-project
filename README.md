# My Personal Project
## Phase 0

**Task 1**

The application that I propose is an **auction market**. It lets you add items to the auction market, and set a minimum 
bidding price. You can also set a timer that counts down to when the bid ends. In the end, you give the item to the 
customer with the highest bid. Before your item starts bidding, you also have the option to remove it. After an item
has been auctioned off, you add it to a list of the recently auctioned items where the list shows both the name of the 
item and the price it was auctioned off for.

**Task 2: Description**

What does this application do?

The application is essentially an auction market where the user is basically in charge of taking control. They are
responsible for keeping track of auctioning items, starting and managing bids, as well as keeping track if items
recently auctioned off. The features and functions of this auction market is very similar to an in person one.

Who will use it? 

Any person who has plans to hold auctions in the future but are skeptical about it. So, they use my auction market
simulation to get a feel of how it goes. That way, the person will have some knowledge and experience and will 
plan ahead.

Why is this project of interest to you?

I really want to know how auctioning works. Also, I want to determine whether bidding for an item gives a
much better deal than buying it directly. Also, I want to get more insight of the competitiveness of bidding.

**Task 3: User Stories**

- As a user (admin), I want to be able to add and remove items from the auction market. 
- As a user, I want to be able to set a minimum bidding price to each item.
- As a user, I want to be able to give the item to the person who won the bid, as well as obtain their bidding money
- As a user (customer) , I want to keep track of all items I won
- As a user (customer), I would like to change my password, deposit and extract balance.
- As a user (customer), I want to be able to create multiple accounts
- As a user (customer), I want to be able to change passwords.

**Phase 2 user stories**

- As a user (customer), I want to be able to save the items ive bidded on, items won, as well as my bank account balance
- As a user (both admin and customer), I want to have the option to continue where I left off (items in auction market, 
- bids, etc) are unchanged when I close and restart the program.


Json code citation:

Source: JsonSerializationDemo - from edX
Coder: Paul carter.

Image Citations:

Startup menu background image: www.istockphoto.com\

**Phase 4: Task 2**

Event log:

Tue Apr 11 11:19:57 PDT 2023
Added new customer: "muye" to customer database
Tue Apr 11 11:20:06 PDT 2023
Added new customer: "muye1" to customer database
Tue Apr 11 11:20:11 PDT 2023
Changed the password of customer with username muye1 to: 1234
Tue Apr 11 11:20:29 PDT 2023
Added new item "muzan" to Auction Market
Tue Apr 11 11:20:39 PDT 2023
Added new item "hashira" to Auction Market
Tue Apr 11 11:20:49 PDT 2023
Auctioned Off (removed item from auction market): "muzan"

**Phase 4: Task 3**

In general, my phase 3 GUI functioned pretty smoothly with no noticeable issues, but the layout of the code is very 
rough. The way I did it was to create a new class (each representing a window) every time I wanted to open a
new page, where I would pass all fields represented in the uml diagram as parameters EVERY time, then discard the old
window. This method worked, but it lead to a bunch of unnecessary duplication because the every gui class had
pretty much the same setup, where the only difference was the individual components (location of buttons, labels, etc)
and functionality. The setup of the frame itself, as well as the fields were pretty much the same. Therefore, a lot of 
the code could be abstracted. If I were to make this exact project again, I would only create one class for my gui, and
add a few list fields (specifically list of panels) that stores pages so that once I want to open a new menu, the code 
only needs to look for that panel in the list, rather than create a new instance of that menu gui class every time. 
As for the common functionality such as constructing the buttons, and labels, I would make a separate tool class 
that contains the methods as a field in my main Gui class. That way, for example, if I want to make a new button, all I
would need to do is call that method, and pass in parameters such as button size, x pos, y pos, etc. This would increase
the overall cohesion of my program. 

