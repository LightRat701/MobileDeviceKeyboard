# Mobile Device Keyboard Autocomplete

This project provides and tests the autocomplete functionality for a mobile device keyboard. It uses the interface defined at the [Asymmetrik website](https://www.asymmetrik.com/programming-challenges/) and provides an implementation for use in the larger Mobile Device Keyboard project. Additional automatic training examples and sample I/O are provided for testing purposes.

## Getting Started

The project is built in Java using Eclipse.

### Prerequisites

* Java Runtime Environment (JRE) - tested using 1.8.0_121 release
* Eclipse - tested using Mars.2 release

### Installing

1. Download the project from GitHub
2. Open the project in Eclipse
3. Run the application

## Usage

Start the program through a standard debug or runtime configuration. Several examples uses will appear on the console. After the examples are complete, several automatic training threads will be started and the user will be prompted for a word fragment. Enter the word fragment, and the console will display the autocomplete suggestions in order of their likelihood of use based on their previous usages. Leave the console blank and press enter to exit the application.

## Implementation Notes

* The implementations submitted for use reside in a separate unique package to reduce the likelihood of conflicts with other similar implementations.
* The implementations submitted implement the Serializable interface. This allows the application to have a basic storage method that can be saved to a file or other persisting unit. Persisting the data structure allows faster training upon loading versus parsing every single word of every single document on every startup. Alternative methods of persisting such as database storage, XML, or JSON could be implemented if requested or required.
* An additional modification was considered that involved saving all words under a given node to a map within the node for quick access and avoiding having to traverse the entire tree. While this provides better time efficiency, it causes disproportionately worse space efficiency. It was deemed that the provided solution was better due to this tradeoff. 