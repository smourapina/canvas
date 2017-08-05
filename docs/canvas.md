# Canvas Application Documentation

### Assumptions
- Input to canvas commands is always of type `Int`.
- If input values provided by the user are invalid then he needs to be again prompted to enter correct values.

### Some considerations on design
-  The drawing canvas is internally represented using a two-dimensional indexed sequence of characters. When it came to make the choice between an immutable data structure (Vector) or a mutable one (Array), the choice was made to go for an Array. 
