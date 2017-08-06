# Canvas Application Documentation

This is a simple console version of a drawing program that works as follows:

1. creates a new canvas;
2. starts drawing on the canvas by issuing various commands;
3. quits.

The program supports the following commands:
- `C w h`: creates a new canvas of width w and height h. 
- `L x1 y1 x2 y2`: creates a new line from `(x1,y1)` to `(x2,y2)`. Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the `x` character. 
- `R x1 y1 x2 y2`: creates a new rectangle, whose upper left corner is `(x1,y1)` and lower right corner is `(x2,y2)`. Horizontal and vertical lines will be drawn using the `x` character. 
- `B x y c`: fills the entire area connected to `(x,y)` with colour `'c'`. The behaviour of this is the same as that of the "bucket fill" tool in paint programs. 
- `Q`: quits the program. 


## How to run
Navigate to the project directory and type:
```
sbt run
```

After you see the prompt, you can start typing commands.
```
enter command:
```

For each command typed, you will see immediately displayed an output which is the result of the command. For example,
you can have the following sequence of commands:

```
enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------
enter command: L 2 3 6 3
----------------------
|                    |
|                    |
| xxxxx              |
|                    |
----------------------
enter command: R 7 1 12 4
----------------------
|      xxxxxx        |
|      x    x        |
| xxxxxx    x        |
|      xxxxxx        |
----------------------
enter command: B 9 2 o
----------------------
|      xxxxxx        |
|      xoooox        |
| xxxxxxoooox        |
|      xxxxxx        |
----------------------
enter command: Q

```

## Notes

#### Some considerations on design
1. The drawing canvas is internally represented using a two-dimensional indexed sequence of characters. When it came to make the choice between an immutable data structure (`Vector`) or a mutable one (`Array`), the choice was made to go for an `Array`, as these show better performance. 
2. The state of the canvas (`CanvasState`) is maintained and updated by returning an updated state after running each user command (which is subsequently converted into an action / operation). 
3. In `CanvasApplication`, a tail recursive loop is in place, with the `CanvasState` being passed to each call.

#### Aspects that could be improved
1. Validation of input should check if the value types are correct for each input command (this could be done using regular expressions). For example, the case in which the user could input something like `L x1 2 3 4` is not covered by the checks, as the value `x1` cannot be converted into an `Int`.
2. Validation is not done to account for the case where, for example in the case of a `Line` command, the order of the respective points is swapped (end point before the beginning point). In this case the line would not be drawn in the canvas. This should be added, and an error message displayed, for example.
3. For handling the canvas state, the `State` monad (for example, as implemented by `cats`) could be used instead, to keep state and pass it along.
4. Instead of defining all the canvas operations in the trait `CanvasOperations`, we could also have defined them separately for each shape (for example, `LineShape` would hold the method that allows us to `drawLines`). It is also not an ideal solution to have all of these methods returning the canvas matrix that results from applying each command.
5. The algorithm for `BucketFill` is not optimal and lead to `StackOverflow` errors. It could be replaced by a tail recursive solution or something like the `QuickFill` algorithm described here: https://www.codeproject.com/Articles/6017/QuickFill-An-efficient-flood-fill-algorithm
6. Error handling is currently not done in a consistent way throughout the application (in some cases a trait is used, in others a simple error message is printed), and this could be improved. 
7. Test coverage is insufficient. Most important would be to add tests for handling error and to cover edge cases more extensively. In particular, tests for keeping track of the `CanvasState` between successive commands/ actions are needed.
8. It would be nice to have acceptance tests.
