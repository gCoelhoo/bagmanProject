## LDTS_T01G08 - Project Bagman
##### Simplified version

<br></br>
This project regards the text-based version of the game **Bagman**. 

The objective of the game is to control the main character, 
Bagman, throughout the mining site, making points from each horizontal step;
picking up coin bags scattered through the map and placing them in a wheelbarrow;
or even  stunning the guards around the map. <br>
A policeman will try to catch the Bagman at all times, and the player should try to avoid them at all cost (however, for the game not to be
so hardcore, we decided to give the Bagman 3 chances to escape).

This project was developed by Eduardo Sousa (up202103342@edu.fe.up.pt), Gabriel Coelho (up201902223@edu.fc.up.pt)
and Raphael Ferreira (up202201814@edu.fe.up.pt) for LDTS 2022.

### Planned Features

The main features that the game should have are the following:

- **Walk** - The basic movements, pressing the Right and Left Keys to walk horizontally.
- **Climb Up/Go Down** Ladders - The basic movements, pressing the Up and Down Buttons for the vertical movements.
- **Pick up/Drop** CoinBags or Pickaxe - The bagman will pick up/drop an item
when the button for Use Action (**Tab**) is pressed.
- **Place CoinBag in the Wheelbarrow** - Placing a CoinBag into the wheelbarrow counts
points for the Player. The bag should disappear and the points added to the Score.
- **Attack with Pickaxe** - When holding the pickaxe and pressing the button
for Use Action (**Tab**) near a Policeman, he should be stunned for a while.
- **Used Pickaxe** - The pickaxe has a 1 use lifespan and should disappear afterwards.

### UML Classes

![UMLclass](docs/UMLclass.png)
