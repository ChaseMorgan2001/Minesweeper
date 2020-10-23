# Minesweeper
This game is based on the Microsoft game "Minesweeper".

The game starts by placing a number of “mines” in unknown random locations in a 2-D grid of cells. The
player sees a grid of blank cells - the location of the mines is unknown at the start. Play proceeds by
allowing the player to click on any cell in the grid. The goal is to avoid clicking on a cell where a mine is
located. If the player clicks on a mine, the game ends (the timer stops) and the entire board is revealed.
There are two counters at the top of the game: an active timer, showing the number of seconds of game
play, and a counter showing the total number of mines that are on the board. Selecting a cell that is not a mine should cause the cell face to change color
and display a number that represents the number of its neighboring cells that contain mines. Selecting a
cell that does not border on any mines (i.e., would be displayed as a zero) should cause “clearing”, where
all cells that are neighbors with the selected one and also do not border any mines are automatically
revealed, because they are blank. Cells that border on zero mines can be displayed as “blank” rather than
with the number zero.

A cell has 8 neighbors: the north, south, east and west cells, together with the four diagonal ones. Cells on
the boundary of the grid have fewer neighbors (only five for a cell on an edge and corner cells only have
three). The player scores a victory by clicking to reveal all cells that are not mines without ever clicking
on a mine.

There are three pre-set levels of difficulty in the Settings panel. Beginner (4x4 grid with 4
mines); Intermediate (8x8 with 15 mines); and Expert (12x12 with 40 mines). The user can also choose to set custom dimensions. IMPORTANT: In order to properly select custum dimensions, the user must enter the dimensions and number of mines into the text field BEFORE selecting the custom button. If the user wants to change these custom dimensions, they must select a different option and reselect the custom button. The height and width dimensions must be the same. The minimum grid size is 3x3 and the maximum grid size is 12x12. There must be at least 2 mines and no more than half the total number of cells on the board.

The game starts when the user clicks the "Start" button. This will reveal the board as well as start the timer. The first cell the user clicks will never be a mine.
