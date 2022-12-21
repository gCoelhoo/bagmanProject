## LDTS_T01G08 - Project Bagman

This project regards the text-based version of the game **Bagman**.

The objective of the game is to control the main character,
Bagman, throughout the mining site, making points from each horizontal step;
picking up coin bags scattered through the map and placing them in a wheelbarrow;
and stunning the guards around the map. <br>
A policeman will try to catch the Bagman at all times, and the player should try to avoid them at all cost (however, for the game not to be
so hardcore, we decided to give the Bagman 3 chances to escape).

This project was developped by Eduardo Sousa (up202103342@edu.fe.up.pt), Gabriel Coelho (up201902223@edu.fc.up.pt)
and Raphael Ferreira (up202201814@edu.fe.up.pt) for LDTS 2022.

### Planned Features

The main features that the game should have, are the following:

- **Walk** - The basic movements, pressing the Right and Left Keys to walk horizontally.
- **Climb Up/Go Down** Ladders - The basic movements, pressing the Up and Down Buttons for the vertical movements.
- **Pick up/Drop** CoinBags or Pickaxe - The bagman will pick up/drop an item
  when the button for Use Action (**Tab**) is pressed.
- **Place CoinBag in the Wheelbarrow** - Placing a CoinBag into the wheelbarrow count
  points for the Player. The bag should disappear and the points added to the Score.
- **Attack with Pickaxe** - When holding the pickaxe and pressing the button
  for Use Action (**Tab**) near a Policeman, he should be stunned for a while.
- **Used Pickaxe** - The pickaxe has a 1 use lifespan and should disappear afterwards.

### Design
#### P1.
- **Motivation** - The starting point for the game to run is how it interacts
  with the user input and behaves with it. We expect that the game follows a loop,
  processing the logic behind the updates and reading the user inputs, and so on.
- **The Pattern** - To solve this, we decided to use the ***Game Loop*** pattern,
  which is responsible to handle the inputs and update the game status without slowing it or blocking it.
- **Implementation** - We have done this by implementing the loop method
  taking into account the real-time that an update in the game state occurs and
  calculating the difference with a fixed frame time. Doing this should give a value that
  the game should wait to run the next update, then controlling the frame updates for
  the game not to run too fast:

[Link to the relevant code for this pattern](https://github.com/FEUP-LDTS-2022/project-l01gr08/blob/0872e0d54db571f742cae99eb376e315412cd848/src/main/java/com/aor/bagman/Game.java#L28-L47)

- **Consequences** - This pattern has its pros and cons, for instance:
    * It is good to have the game running without have to wait for any input
      as the game is not turn-based.
    * On the other hand, it is very dependent on the hardware. Any PC with a very
      slow processor/memory would have to wait a long time for the updates and sometimes
      causing freezes in the game. <br>

----------------------

#### P2.
- **Motivation** - As we are using the GameLoop Pattern, it would be very convenient if we
  have a way to manage the behavior of the different classes in each update of the game state.
- **The Pattern** - For that purpose, we use the Update Method Game Pattern,
  that works closely with the Game Loop Pattern, allowing us to handle the changes on the game state
  by maintaining update methods in crucial objects like GameMapController and GameState.
- **Implementation** - As mentioned above, it was implemented the update methods
  on fundamental classes that manages the game logic:

[Link to the relevant code for this pattern](https://github.com/FEUP-LDTS-2022/project-l01gr08/blob/0872e0d54db571f742cae99eb376e315412cd848/src/main/java/com/aor/bagman/controllers/GameMapController.java#L6-L8)

[Link to the relevant code for this pattern](https://github.com/FEUP-LDTS-2022/project-l01gr08/blob/0872e0d54db571f742cae99eb376e315412cd848/src/main/java/com/aor/bagman/states/State.java#L11-L25)

----------------------

#### P3.
- **Motivation** - Furthermore, the classes that control the game updates,
  namely the GameState and GameMapController, need to know what step they have to take
  when faced with each input.
- **The Pattern** - To solve this, we are going to apply the State Pattern to switch the
  behavior of each object, according to the respective implementation.
- **Implementation** - As we do not have all the functionalities implemented by now,
  we only used this to test the QuitGame Action.

[Link to the relevant code for this pattern](https://github.com/FEUP-LDTS-2022/project-l01gr08/blob/0872e0d54db571f742cae99eb376e315412cd848/src/main/java/com/aor/bagman/gui/LanternaGUI.java#L45-L59)

[Link to the relevant code for this pattern](https://github.com/FEUP-LDTS-2022/project-l01gr08/blob/0872e0d54db571f742cae99eb376e315412cd848/src/main/java/com/aor/bagman/controllers/GameMapController.java#L6-L13)

- **Consequences** - Using this pattern allows us to have a cleaner code,
  instead of having to implement a state machine, full of conditionals statements for each state.

----------------------

#### P4.
- **Motivation** - Because of the large number of objects to initialize
  during the Map creation, we were afraid that the Map constructor may get too big and
  confusing.
- **The Pattern** - After all the behavioral patterns, we used the creational
  ***Builder*** Pattern to separate the construction of the Map class and move to
  the builder class MapCreator, responsible to instantiate those objects for the game map.
- **Implementation** - The instantiation process was separated into steps like PlayerCreator,
  CoinBagsCreator, and so on. Finally, the method createMap set all the objects.

[Link to the relevant code for this pattern](https://github.com/FEUP-LDTS-2022/project-l01gr08/blob/f970b31a4a9af14216a54901f3d8c2f03833ee99/src/main/java/com/aor/bagman/models/MapModels/MapCreator.java#L17-L128)

- **Consequences** - This pattern gives us more options if we would
  want to create different representation of maps just by defining an
  interface and making the MapCreator being one of the possible ConcreteBuilder/Creator.


### UML Classes

![UMLclass](UMLclass.png)
