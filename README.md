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
enter command: B 2 9 o
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
-  The drawing canvas is internally represented using a two-dimensional indexed sequence of characters. When it came to make the choice between an immutable data structure (`Vector`) or a mutable one (`Array`), the choice was made to go for an `Array`, as these show better performance. 


#### Aspects that could be improved
- Validation of input should check if the value types are correct for each input command. For example, the case in which the user could input something like `L x1 2 3 4` is not covered by the checks, as the value `x1` fails when trying to convert it to an `Int`.
- For keeping the canvas state, the `State` monad (for example, as implemented by `cats`) could be used instead.
- The algorithm for `BucketFill` is not optimal and could be cause for `StackOverflow`. It could be replaced for something like the `QuickFill` algorithm described here: https://www.codeproject.com/Articles/6017/QuickFill-An-efficient-flood-fill-algorithm
- Error handling is currently not done in a consistent way throughout the application, and this could be improved. Tests should be added for handling error and to cover edge cases more extensively.


 
