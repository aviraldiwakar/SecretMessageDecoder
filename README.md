# Secret Message Decoder

## The Challenge
Provide a script that reads a Google Document containing a table of coordinates and characters, and prints the hidden message to the console. The table has three columns: x-coordinate, character, and y-coordinate.

**Input:** A Google Docs URL containing the table.
**Output:** A 2D grid of characters printed to the console revealing a message.

## The Solution
This Java project uses **Maven** and the **JSoup** library to fetch the HTML content directly from the published Google Doc URL, parses the table elements, maps the characters to their respective `(x, y)` coordinates, and prints the final grid.

## How to Run
1. Clone the repository.
2. Ensure you have Maven installed.
3. Run the `main` method in `SecretMessageDecoder.java`.the repository.
2. Ensure
